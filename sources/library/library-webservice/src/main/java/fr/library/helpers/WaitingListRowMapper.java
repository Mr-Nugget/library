package fr.library.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.library.model.Document;
import org.library.model.WaitingList;
import org.springframework.jdbc.core.RowMapper;

public class WaitingListRowMapper implements RowMapper<WaitingList> {

	@Override
	public WaitingList mapRow(ResultSet rs, int rowNum) throws SQLException {
		WaitingList wl = new WaitingList();
		wl.setId(rs.getLong("id"));
		
		Document doc = new Document();
		doc.setId(rs.getLong("document_id"));
		wl.setDoc(doc);
		
		return wl;
	}

}
