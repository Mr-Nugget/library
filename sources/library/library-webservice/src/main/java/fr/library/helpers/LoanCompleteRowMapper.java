package fr.library.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.library.model.Category;
import org.library.model.Document;
import org.library.model.Loan;
import org.library.model.Status;
import org.library.model.Type;
import org.library.model.User;
import org.springframework.jdbc.core.RowMapper;

public class LoanCompleteRowMapper implements RowMapper<Loan> {

	@Override
	public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
		Loan loan = new Loan();
		loan.setBeginDate(rs.getDate("start_date"));
		loan.setEndDate(rs.getDate("end_date"));
		loan.setId(rs.getLong("id"));
		loan.setStatus(Status.getEnumByInt(rs.getInt("status")));
		
		Document doc = new Document();
		User user = new User();
		
		doc.setId(rs.getLong("document_id"));
		doc.setAuthor(rs.getString("author"));
		doc.setTitle(rs.getString("title"));
		doc.setRef(rs.getString("ref"));
		doc.setCurrentstock(rs.getInt("current_stock"));
		doc.setTotalstock(rs.getInt("total_stock"));
		
		Type type = new Type();
		Category cat = new Category();
		
		type.setId(rs.getLong("type_id"));
		cat.setId(rs.getLong("category_id"));
		
		doc.setCategory(cat);
		doc.setType(type);
		
		user.setId(rs.getLong("user_id"));
		
		loan.setDoc(doc);
		loan.setUser(user);
		
		return loan;
	}
}