package fr.library.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.library.model.Position;
import org.library.model.User;
import org.springframework.jdbc.core.RowMapper;

public class PositionRowMapper implements RowMapper<Position> {

	@Override
	public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		user.setId(rs.getLong("user_id"));
		Position pos = new Position(rs.getInt("position"), user);
		
		return pos;
	}

}
