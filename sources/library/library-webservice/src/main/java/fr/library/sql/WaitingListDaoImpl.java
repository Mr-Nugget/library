package fr.library.sql;

import java.util.List;

import javax.sql.DataSource;

import org.library.model.Position;
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
		String queryPosition = "SELECT * FROM position WHERE document_id=?;";
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
			
		WaitingList wl = (WaitingList) jdbc.queryForObject(query, new Object[] {id}, new WaitingListRowMapper());
		
		List<Position> usersPositions = jdbc.query(queryPosition, new Object[] {wl.getDoc().getId()}, new PositionRowMapper());
		
		for(Position pos : usersPositions) {
			wl.getUsersPositions().put(pos.getPosition(), pos.getUser());
		}
		
		return wl;
	}

	@Override
	public void deleteItem(WaitingList item) {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String queryPosition ="DELETE FROM position WHERE document_id=?;";
		String query = "DELETE FROM waitingList WHERE id=?;";
		// Delete positions associated to the list
		jdbc.update(queryPosition, item.getDoc().getId());
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
		String query = "SELECT * FROM waintingList;";
		String queryPosition = "SELECT * FROM position WHERE list_id=?;";
		List<WaitingList> allWL = jdbc.query(query, new WaitingListRowMapper());
		for(WaitingList wl : allWL) {
			Position pos = (Position) jdbc.query(queryPosition, new Object[] {wl.getId()}, new PositionRowMapper());
			wl.getUsersPositions().put(pos.getPosition(), pos.getUser());
		}
		
		return allWL;
	}

}
