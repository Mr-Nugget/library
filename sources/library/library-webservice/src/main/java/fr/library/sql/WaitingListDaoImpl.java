package fr.library.sql;

import java.util.List;

import javax.sql.DataSource;

import org.library.model.Document;
import org.library.model.Position;
import org.library.model.User;
import org.library.model.WaitingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.library.helpers.PositionRowMapper;
import fr.library.helpers.WaitingListRowMapper;

@Repository
@Qualifier("WaitingListDao")
public class WaitingListDaoImpl implements IWaitingListDao {
	
	@Autowired
	DataSource dataSource;

	@Override
	public WaitingList getById(Long id) {
		String query = "SELECT * FROM waitingList WHERE id=?;";
		String queryPosition = "SELECT * FROM position WHERE list_id=?;";
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
			
		WaitingList wl = (WaitingList) jdbc.queryForObject(query, new Object[] {id}, new WaitingListRowMapper());
		
		List<Position> usersPositions = jdbc.query(queryPosition, new Object[] {wl.getId()}, new PositionRowMapper());
		
		for(Position pos : usersPositions) {
			wl.getUsersPositions().put(pos.getPosition(), pos.getUser());
		}
		
		return wl;
	}

	@Override
	public void deleteItem(WaitingList item) {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String queryPosition ="DELETE FROM position WHERE list_id=?;";
		String query = "DELETE FROM waitingList WHERE id=?;";
		// Delete positions associated to the list
		jdbc.update(queryPosition, item.getId());
		// Delete the waiting list
		jdbc.update(query, item.getId());

	}

	@Override
	public void updateItem(WaitingList item) {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String queryPosition = "UPDATE position SET user_id=?, position=?;";
		
		item.getUsersPositions().forEach(
				(k,v) -> jdbc.update(queryPosition,v,k));
	}

	@Override
	public List<WaitingList> findAll() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String query = "SELECT * FROM waitingList;";
		String queryPosition = "SELECT * FROM position WHERE list_id=?;";
		List<WaitingList> allWL = jdbc.query(query, new WaitingListRowMapper());
		for(WaitingList wl : allWL) {
			Position pos = (Position) jdbc.queryForObject(queryPosition, new Object[] {wl.getId()}, new PositionRowMapper());
			wl.getUsersPositions().put(pos.getPosition(), pos.getUser());
		}
		
		return allWL;
	}

	@Override
	public Long createWaitingList(Document doc, User user) {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String query = "INSERT INTO waitingList (document_id) VALUES(?);";
		jdbc.update(query, doc.getId());
		query = "SELECT * FROM waitingList WHERE document_id=?;";
		WaitingList wl = jdbc.queryForObject(query, new Object[] {doc.getId()}, new WaitingListRowMapper());
		query = "INSERT INTO position (user_id, position, list_id) VALUES(?,?,?);";
		jdbc.update(query, user.getId(), 1, wl.getId());
		
		return wl.getId();
		
	}

	@Override
	public Boolean alreadyInTheList(Document doc, User user) {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String query = "SELECT COUNT(*) FROM waitingList AS l, position AS p WHERE l.id=p.list_id AND l.document_id=? AND user_id=?;";
		Integer count = jdbc.queryForObject(query, new Object[] {doc.getId(), user.getId()}, Integer.class);
		return count == 1;
	}

}
