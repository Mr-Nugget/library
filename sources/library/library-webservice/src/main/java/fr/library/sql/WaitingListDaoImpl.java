package fr.library.sql;

import java.util.List;

import org.apache.log4j.Logger;
import org.library.model.Document;
import org.library.model.Position;
import org.library.model.User;
import org.library.model.WaitingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.library.helpers.PositionRowMapper;
import fr.library.helpers.WaitingListRowMapper;

@Repository
public class WaitingListDaoImpl implements IWaitingListDao {

	private final static Logger logger = Logger.getLogger(IWaitingListDao.class);
	
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public WaitingList getById(Long id) {
		String query = "SELECT * FROM waitingList wl, documents d WHERE wl.document_id = d.id AND wl.id=?;";
		String queryPosition = "SELECT * FROM position WHERE list_id=?;";
		

		WaitingList wl = (WaitingList) jdbc.queryForObject(query, new Object[] {id}, new WaitingListRowMapper());

		List<Position> usersPositions = jdbc.query(queryPosition, new Object[] {wl.getId()}, new PositionRowMapper());

		for(Position pos : usersPositions) {
			wl.addUserWithPosition(pos.getUser(), pos.getPosition());
		}

		return wl;
	}

	@Override
	public void deleteItem(WaitingList item) {
		
		String queryPosition ="DELETE FROM position WHERE list_id=?;";
		String query = "DELETE FROM waitingList WHERE id=?;";
		// Delete positions associated to the list
		jdbc.update(queryPosition, item.getId());
		// Delete the waiting list
		jdbc.update(query, item.getId());

	}

	@Override
	public void updateItem(WaitingList item) {
		
		String queryPosition = "UPDATE position SET user_id=?, position=?;";

		for (int i = 0; i < item.getLastPosition(); i++) {
			jdbc.update(queryPosition,item.getUsersPositions()[i].getId(), i);
		}
	}

	@Override
	public List<WaitingList> findAll() {
		
		String query = "SELECT * FROM waitingList wl, documents d WHERE wl.document_id = d.id;";
		String queryPosition = "SELECT * FROM position WHERE list_id=?;";
		List<WaitingList> allWL = jdbc.query(query, new WaitingListRowMapper());
		for(WaitingList wl : allWL) {
			Position pos = (Position) jdbc.queryForObject(queryPosition, new Object[] {wl.getId()}, new PositionRowMapper());
			wl.addUserWithPosition(pos.getUser(), pos.getPosition());
		}

		return allWL;
	}

	@Override
	public Long createWaitingList(Document doc, User user) {
		
		String query = "INSERT INTO waitingList (document_id) VALUES(?);";
		jdbc.update(query, doc.getId());
		query = "SELECT * FROM waitingList wl, documents d WHERE wl.document_id = d.id AND document_id=?;";
		WaitingList wl = jdbc.queryForObject(query, new Object[] {doc.getId()}, new WaitingListRowMapper());
		query = "INSERT INTO position (user_id, position, list_id) VALUES(?,?,?);";
		jdbc.update(query, user.getId(), 0, wl.getId());

		return wl.getId();

	}

	@Override
	public Boolean alreadyInTheList(Document doc, User user) {
		
		String query = "SELECT COUNT(*) FROM waitingList AS l, position AS p WHERE l.id=p.list_id AND l.document_id=? AND user_id=?;";
		Integer count = jdbc.queryForObject(query, new Object[] {doc.getId(), user.getId()}, Integer.class);
		return count == 1;
	}

	@Override
	public WaitingList getByDocument(Document doc) {
		
		String query = "SELECT * FROM waitingList wl, documents d WHERE wl.document_id = d.id AND document_id=?";
		String queryPosition = "SELECT * FROM position WHERE list_id=?;";
		WaitingList wl = null;
		try {
			wl = jdbc.queryForObject(query, new Object[] {doc.getId()}, new WaitingListRowMapper());
			List<Position> usersPositions = jdbc.query(queryPosition, new Object[] {wl.getId()}, new PositionRowMapper());

			for(Position pos : usersPositions) {
				wl.addUserWithPosition(pos.getUser(), pos.getPosition());
			}
		} catch (EmptyResultDataAccessException e) {

		}

		return wl;
	}

	@Override
	public void addUserToList(WaitingList wl, User user) {
		
		String query = "INSERT INTO position (user_id, position, list_id) VALUES(?,?,?);";

		jdbc.update(query, user.getId(), wl.getLastPosition(), wl.getId());
		
		wl.addUserInWaitingList(user);

	}

	@Override
	public List<WaitingList> getUserReservations(User user) {
		
		String query = "SELECT * FROM waitingList wl, position p, documents d WHERE wl.id = p.list_id AND wl.document_id = d.id AND p.user_id = ?;";
		List<WaitingList> lWL = null;
		try {
			lWL = jdbc.query(query, new Object[] {user.getId()}, new WaitingListRowMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error("GetUserReservation", e);
		}
		return lWL;
	}

}
