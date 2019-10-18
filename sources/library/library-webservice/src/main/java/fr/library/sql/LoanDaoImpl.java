package fr.library.sql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.library.model.Document;
import org.library.model.Loan;
import org.library.model.Status;
import org.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.library.exceptions.DocumentNotAvailableException;
import fr.library.exceptions.LoanStatusException;
import fr.library.helpers.LoanRowMapper;

@Repository
public class LoanDaoImpl implements ILoanDao {

	private final static Logger logger =  Logger.getLogger(LoanDaoImpl.class);

	@Autowired
	private DataSource dataSource;
	
	public LoanDaoImpl() {}
	
	
	@Override
	public List<Loan> getListArchived(Long userId) {
		List<Loan> list_loans = new ArrayList<>();
		Connection connect = null;
		PreparedStatement prepared = null;
		ResultSet res = null;
		String query = "SELECT * FROM loans AS l, documents AS d WHERE l.document_id = d.id AND l.user_id=? AND l.status = 0"; //status = 0 -> status.CLOTURED
		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setLong(1, userId);

			res = prepared.executeQuery();
			
			
			User user = DaoFactory.getInstance().getUserDao().getById(userId);
			// Create the document list with the resultSet
			while(res.next()) {
				Document doc = DaoFactory.getInstance().getDocumentDao().getById(res.getLong("document_id"));
				list_loans.add(new Loan(res.getLong("id"), user, doc, res.getDate("start_date"), res.getDate("end_date"), Status.getEnumByInt(res.getInt("status"))));
			}

		} catch (SQLException e) {
			logger.error("getListBorrow : ",e);
		}finally {
			try {
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}
			}catch(SQLException e) {
				logger.error("getListBorrow/Close connection : ",e);
			}
		}
		return list_loans; //return empty list if there is no borrow
	}


	@Override
	public void extendLoan(Long loanId) throws LoanStatusException {
		Loan loan = getById(loanId);
		Date date = loan.getEndDate();
		Status status = loan.getStatus();
		Date today = new Date();
		
		if(status.equals(Status.IN_PROGRESS) && loan.getEndDate().after(today)) {

			Calendar cal = Calendar.getInstance(); // creates calendar
			cal.setTime(date); // sets calendar time/date
			cal.add(Calendar.WEEK_OF_YEAR, 4); // adds 4 weeks
			date.setTime(cal.getTimeInMillis());//change the end_date +4weeks
			loan.setStatus(Status.EXTENDED);

			updateItem(loan);
		}else {
			throw new LoanStatusException("Error extendLoan");
		}
	}

	
	@Override
	public void returnDocument(Loan loan) throws LoanStatusException {
		Status status = loan.getStatus();
		Connection connect = null;
		PreparedStatement prepared = null, psUpdateDoc = null;
		String query = "UPDATE loans SET end_date=TO_DATE(?,'YYYY/MM/DD'), status=? WHERE id=?;";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); //to get the right format for the DB
		
		if(status.equals(Status.IN_PROGRESS) || status.equals(Status.EXTENDED)) {
			try {
				connect = DaoConnection.getInstance().getConnection();
				connect.setAutoCommit(false); //using transactions
				prepared = connect.prepareStatement(query);
				prepared.setString(1, dateFormat.format(new Date())); //set the end date to return's date
				prepared.setInt(2, Status.CLOTURED.getId());
				prepared.setLong(3, loan.getId());

				prepared.executeUpdate();
				
				//update document stock
				query = "UPDATE documents SET current_stock=? WHERE id = ?;";
				psUpdateDoc = connect.prepareStatement(query);
				int newCurrentStock = loan.getDoc().getCurrentstock().intValue();
				psUpdateDoc.setInt(1, newCurrentStock);
				psUpdateDoc.setLong(2, loan.getDoc().getId());
				psUpdateDoc.executeUpdate();
				
				connect.commit();

			}catch(SQLException e) {
				try {
					connect.rollback();
				} catch (SQLException e1) {
					logger.error("Rollback error ", e);
				}
				logger.error("updateItem : ", e);
			}finally {
				try {
					if(psUpdateDoc != null) {
						psUpdateDoc.close();
					}
					if(prepared!=null) {
						prepared.close();
					}
					if(connect!=null) {
						connect.close();
					}
				}catch(SQLException e) {
					logger.error("updateItem/Close Connnection : ",e);
				}
			}
			
		}else {
			throw new LoanStatusException("You can't return a document more than once");
		}
	}

	
	@Override
	public List<Loan> getListCurrent(Long userId) {
		List<Loan> list_loans = new ArrayList<>();
		Connection connect = null;
		PreparedStatement prepared = null;
		ResultSet res = null;
		//select only document with EXTENDED or IN PROGRESS status
		String query = "SELECT * FROM loans AS l, documents AS d WHERE l.document_id=d.id AND l.user_id= ? AND (l.status=1 OR l.status=2);";

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setLong(1, userId);
			res = prepared.executeQuery();

			User user = DaoFactory.getInstance().getUserDao().getById(userId);
			// Create the document list with the resultSet
			while(res.next()) {					
				Document doc =  DaoFactory.getInstance().getDocumentDao().getById(res.getLong("document_id"));
				list_loans.add(new Loan(res.getLong("id"), user, doc, res.getDate("start_date"), res.getDate("end_date"), Status.getEnumByInt(res.getInt("status"))));
			}

		} catch (SQLException e) {
			logger.error("getListCurrent : ",e);
		}finally{
			try {
				if(res != null) {
					res.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}
			}catch(SQLException e) {
				logger.error("getListCurrent/Close Connection : ",e);
			}
		}
		return list_loans;
	}

	@Override
	public Long createLoan(Document doc, User user) throws DocumentNotAvailableException {
		Connection connect = null;
		PreparedStatement prepared = null, ps2 = null, psId=null;
		ResultSet res = null;
		Long idReturn = null;
		if(doc.getCurrentstock()>0) {

			String query ="INSERT INTO loans(document_id, user_id, start_date, end_date, status) VALUES(?,?,TO_DATE(?, 'YYYY/MM/DD'),TO_DATE(?, 'YYYY/MM/DD'),?);";
			try {
				connect = DaoConnection.getInstance().getConnection();
				connect.setAutoCommit(false); //use transaction 
				// Create the loan
				prepared = connect.prepareStatement(query);

				// Get the current date
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); //to get the right format for the DB
				Date today_date = new Date();
				// Add 4 weeks to set the end_date
				Date end_date = new Date();
				Calendar cal = Calendar.getInstance(); // creates calendar
				cal.setTime(end_date); // sets calendar time/date
				cal.add(Calendar.WEEK_OF_YEAR, 4); // adds 4 weeks
				end_date.setTime(cal.getTimeInMillis());//change the end_date +4weeks

				prepared.setLong(1, doc.getId());
				prepared.setLong(2, user.getId());
				prepared.setString(3, dateFormat.format(today_date));
				prepared.setString(4, dateFormat.format(end_date));
				prepared.setInt(5, Status.IN_PROGRESS.getId());
				prepared.executeUpdate();
				
				//Get the id of the new loan
				query = "SELECT id FROM loans WHERE document_id=? AND user_id=?";
				psId = connect.prepareStatement(query);
				psId.setLong(1, doc.getId());
				psId.setLong(2, user.getId());
				res = psId.executeQuery();
				if(res.next()) {
					idReturn = res.getLong("id");
				}
				
				
				// Update document status
				query = "UPDATE documents SET current_stock=? WHERE id=?";
				ps2 = connect.prepareStatement(query);
				ps2.setInt(1, doc.getCurrentstock() - 1);
				ps2.setLong(2, doc.getId());
				ps2.executeUpdate();
				
				connect.commit(); //commit the 2 request at the same time

			}catch(SQLException e) {
				logger.error("createLoan : ",e);
				try {
					connect.rollback(); //cancel changes if there is a connection problem
				} catch (SQLException e1) {
					logger.error("createLoan/RollBack Error : ",e);
				}
			}finally {
				try {
					if(res!= null) {
						res.close();
					}
					if(psId != null) {
						psId.close();
					}
					if(ps2 != null) {
						ps2.close();
					}
					if(prepared!=null) {
						prepared.close();
					}
					if(connect!=null) {
						connect.close();
					}
				}catch(SQLException e) {
					logger.error("createLoan/Close Connection : ",e);
				}
			}
		}else {
			DocumentNotAvailableException e = new DocumentNotAvailableException("No more stock for this document");
			throw e;
		}
		return idReturn;
	}
	
	@Override
	public Loan getById(Long id) {
		Connection connect = null;
		PreparedStatement prepared = null;
		Loan l_return = null;
		ResultSet res = null;
		String query = "SELECT * FROM loans WHERE id=?;";

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setLong(1, id);
			res = prepared.executeQuery();

			if(res.next()) {
				//Create Document and User thanks to Document and User methods to get by id
				Document doc = DaoFactory.getInstance().getDocumentDao().getById(res.getLong("document_id"));
				User user = DaoFactory.getInstance().getUserDao().getById(res.getLong("user_id"));

				l_return = new Loan(res.getLong("id"), user, doc, res.getDate("start_date"), res.getDate("end_date"), Status.getEnumByInt(res.getInt("status")));
			}
		}catch(SQLException e) {
			logger.error("GetId : ",e);
		}finally {
			try {
				if(res!=null) {
					res.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}

			}catch(SQLException e) {
				logger.error("GetId/Close Connection : ",e);
			}
		}

		return l_return; //return null if there is no loan corresponding or a SQLException
	}
	@Override
	public void deleteItem(Loan item) {
		Connection connect = null;
		PreparedStatement prepared = null;
		String query = "DELETE FROM loans WHERE id=?;";

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setLong(1, item.getId());
			prepared.executeUpdate();
		}catch(SQLException e) {
			logger.error("DeleteItem : ",e);
		}finally {
			try {
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}
			}catch(SQLException e) {
				logger.error("DeleteItem/Close Connection : ",e);
			}
		}

	}
	@Override
	public void updateItem(Loan item) {
		Connection connect = null;
		PreparedStatement prepared = null;
		String query = "UPDATE loans SET document_id=?, user_id=?, start_date=TO_DATE(?,'YYYY/MM/DD'), end_date=TO_DATE(?,'YYYY/MM/DD'), status=? WHERE id=?;";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); //to get the right format for the DB

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setLong(1, item.getDoc().getId());
			prepared.setLong(2, item.getUser().getId());
			prepared.setString(3, dateFormat.format(item.getBeginDate()));
			prepared.setString(4, dateFormat.format(item.getEndDate()));
			prepared.setInt(5, item.getStatus().getId());
			prepared.setLong(6, item.getId());

			prepared.executeUpdate();

		}catch(SQLException e) {
			logger.error("updateItem : ",e);
		}finally {
			try {
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}
			}catch(SQLException e) {
				logger.error("updateItem/Close Connnection : ",e);
			}
		}
	}


	@Override
	public List<Loan> findAll() {
		Connection connect = null;
		PreparedStatement prepared = null;
		List<Loan> list_loans = new ArrayList<>();
		ResultSet res = null;
		String query = "SELECT * FROM loans;";

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			res = prepared.executeQuery();

			while(res.next()) {
				//Create Document and User thanks to Document and User methods to get by id
				Document doc = DaoFactory.getInstance().getDocumentDao().getById(res.getLong("document_id"));
				User user = DaoFactory.getInstance().getUserDao().getById(res.getLong("user_id"));

				list_loans.add(new Loan(res.getLong("id"), user, doc, res.getDate("start_date"), res.getDate("end_date"), Status.getEnumByInt(res.getInt("status"))));
			}
		}catch(SQLException e) {
			logger.error("findAll : ",e);
		}finally {
			try {
				if(res!=null) {
					res.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}

			}catch(SQLException e) {
				logger.error("findAll/CloseConnection", e);
			}
		}
		// Return null if there is no loan corresponding or a SQLException
		return list_loans; 
	}


	@Override
	public List<Loan> getExpiredLoans() {
		Connection connection = null;
		PreparedStatement prepared = null;
		ResultSet res = null;
		List<Loan> listRes = new ArrayList<>();
		String query = "SELECT document_id, user_id, l.id, lastname, firstname, mail, title, author, start_date, end_date, status, ref FROM users u, loans l, documents d WHERE u.id = l.user_id AND d.id = l.document_id AND (l.status = 1 OR l.status = 2) AND l.end_date <= NOW();";
		
		try{
			connection = DaoConnection.getInstance().getConnection();
			prepared = connection.prepareStatement(query);
			res = prepared.executeQuery();
			while(res.next()) {
				User user = new User();
				user.setConnected(false);
				user.setId(res.getLong("user_id"));
				user.setFirstName(res.getString("firstname"));
				user.setLastName(res.getString("lastname"));
				user.setMail(res.getString("mail"));
				user.setPassword(null);
				
				Document document = new Document();
				document.setAuthor(res.getString("author"));
				document.setTitle(res.getString("title"));
				document.setRef(res.getString("ref"));
				document.setCategory(null);
				document.setType(null);
				document.setId(res.getLong("document_id"));				
				
				Loan loan = new Loan();
				loan.setId(res.getLong("id"));
				loan.setBeginDate(res.getDate("start_date"));
				loan.setEndDate(res.getDate("end_date"));
				loan.setStatus(Status.getEnumByInt(res.getInt("status")));
				loan.setDoc(document);
				loan.setUser(user);
				listRes.add(loan);
			}
		} catch (SQLException e) {
			logger.error("getExpiredLoans", e);
		}finally {
			try {
				if(res!=null) {
					res.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connection!=null) {
					connection.close();
				}
			}catch(SQLException e) {
				logger.error("Close closeable Get expired", e);
			}
		}
		return listRes;
	}


	@Override

	public List<Loan> forMailRecall() {
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		java.sql.Date todayPlus5 = new java.sql.Date(calendar.getTime().getTime());
		
		Connection connection = null;
		PreparedStatement prepared = null;
		ResultSet res = null;
		
		String query = "SELECT * FROM users u, loans l, documents d WHERE l.user_id = u.id AND l.document_id = d.id AND l.end_date <= ? AND l.end_date > NOW() AND u.mailrecall=true AND l.status = 1;";
		
		List<Loan> listReturn = new ArrayList<>();
		
		try {
			connection = DaoConnection.getInstance().getConnection();
			prepared = connection.prepareStatement(query);
			prepared.setDate(1, todayPlus5);
			res = prepared.executeQuery();
			
			while(res.next()) {
				
				User user = new User();
				user.setId(res.getLong("user_id"));
				user.setFirstName(res.getString("firstname"));
				user.setLastName(res.getString("lastname"));
				user.setPassword(res.getString("password"));
				user.setConnected(res.getBoolean("connected"));
				user.setMailRecall(true);
				user.setMail(res.getString("mail"));
				
				Document doc = new Document();
				doc.setId(res.getLong("document_id"));
				doc.setAuthor(res.getString("author"));
				doc.setTotalstock(res.getInt("total_stock"));
				doc.setCurrentstock(res.getInt("current_stock"));
				doc.setRef(res.getString("ref"));
				doc.setTitle(res.getString("title"));
				
				Loan loan = new Loan();
				loan.setBeginDate(res.getDate("start_date"));
				loan.setEndDate(res.getDate("end_date"));
				loan.setId(res.getLong("id"));
				loan.setUser(user);
				loan.setDoc(doc);

				listReturn.add(loan);
			}
		} catch (SQLException e) {
			logger.error("forMailRecall", e);
		}finally {
			try {
				if(res!=null) {
					res.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connection!=null) {
					connection.close();
				}
			}catch(SQLException e) {
				logger.error("Close closeable mailRecall", e);
			}
		}
		return listReturn;
	}

	public List<Loan> getLoansByDocument(Document doc) {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		// Add parameters
		List<Loan> ls = jdbc.query("SELECT * FROM loans WHERE document_id=?", new Object[] {doc.getId()}, new LoanRowMapper());
		return ls;
	}


	@Override
	public Boolean alreadyHaveTheDocument(User user, Document doc) {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String query = "SELECT * FROM loans WHERE document_id=? AND user_id=? AND (status=1 OR status=2);";
		Loan loanExist = jdbc.queryForObject(query, new Object[] {doc.getId(), user.getId()}, new LoanRowMapper());
		
		return loanExist != null;
	}


}
