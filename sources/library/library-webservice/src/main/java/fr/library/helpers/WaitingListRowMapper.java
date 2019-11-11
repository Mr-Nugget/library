package fr.library.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.library.model.Category;
import org.library.model.Document;
import org.library.model.Type;
import org.library.model.WaitingList;
import org.springframework.jdbc.core.RowMapper;

public class WaitingListRowMapper implements RowMapper<WaitingList> {

	@Override
	public WaitingList mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Document doc = new Document();
		doc.setId(rs.getLong("document_id"));
		doc.setAuthor(rs.getString("author"));
		doc.setTitle(rs.getString("title"));
		doc.setRef(rs.getString("ref"));
		doc.setTotalstock(rs.getInt("total_stock"));
		doc.setCurrentstock(rs.getInt("current_stock"));
		
		Category cat = new Category();
		Type type = new Type();
		
		cat.setId(rs.getLong("category_id"));
		type.setId(rs.getLong("type_id"));
		
		doc.setCategory(cat);
		doc.setType(type);
		
		WaitingList wl = new WaitingList(doc);
		wl.setId(rs.getLong("id"));
		
		return wl;
	}

}
