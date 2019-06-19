package fr.library.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.library.model.Category;
import org.library.model.Document;
import org.library.model.Type;


public class DocumentDaoImpl implements IDocumentDao {
	
	private final static Logger logger = Logger.getLogger(DocumentDaoImpl.class); // For the logs
	
	public DocumentDaoImpl() {}


	@Override
	public List<Document> searchByCriteria(String crit, String search) {
		List<Document> l_docs = new ArrayList<>();
		Connection connect = null;
		PreparedStatement prepared = null, ps_type = null, ps_cat = null;
		ResultSet res = null , res_cat = null, res_type = null;
		String query = null;
		//select witch request use depending on the criteria
		try {
			switch(crit) {
			case AUTHOR :
				query = "SELECT * FROM documents WHERE author=?";
				break;
			case TITLE:
				query = "SELECT * FROM documents WHERE title=?;";
				break;
			case REFERENCE:
				query = "SELECT * FROM documents WHERE ref=?;";
				break;
			default:
				query = "SELECT * FROM documents WHERE title=?;";
				break;
			}

			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setString(1, search);
			res = prepared.executeQuery();
			
			while(res.next()) {

				//Execute query to create the objects category and type of document
				Category cat = null;
				Type type = null;
				String query_cat = "SELECT * FROM categories WHERE id=?;";
				String query_type = "SELECT * FROM types WHERE id=?;";
				ps_cat = connect.prepareStatement(query_cat);
				ps_type = connect.prepareStatement(query_type);
				ps_cat.setLong(1, res.getLong("category_id"));
				ps_type.setLong(1, res.getLong("type_id"));
				//execute the query
				res_cat = ps_cat.executeQuery();
				res_type = ps_type.executeQuery();

				if(res_cat.next() && res_type.next()) {
					cat = new Category(res_cat.getLong("id"), res_cat.getString("label"), res_cat.getString("description"));
					type = new Type(res_cat.getLong("id"), res_type.getString("label"), res_type.getString("description"));
				}
				Document doc_stock = new Document(res.getLong("id"),
						res.getString("ref"),
						res.getString("title"),
						res.getString("author"),
						cat,
						type,
						res.getInt("total_stock"),
						res.getInt("current_stock"));

				l_docs.add(doc_stock);

			}

		} catch (SQLException e) {
			logger.error("SearchByCriteria : ",e);
		}
		finally {
			//Close the connection
			try {
				if(res_cat!=null) {
					res_cat.close();
				}
				if(res_type!=null) {
					res_type.close();
				}
				if(res!=null) {
					res.close();
				}
				if(ps_cat!=null) {
					ps_cat.close();
				}
				if(ps_type!=null) {
					ps_type.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connect != null) {
					connect.close();
				}

			}catch(SQLException e) {
				logger.error("SearchByCriteria/Close Connection : ",e);
			}
		}
		return l_docs;
	}
	
	
	@Override
	public Document getById(Long id) {

		Document doc_return = null;
		Connection connect = null;
		PreparedStatement prepared = null, ps_cat = null, ps_type = null;
		ResultSet res = null, res_type = null , res_cat = null;
		String query = "SELECT * FROM documents WHERE id=?;";

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setLong(1, id);
			res = prepared.executeQuery();
			// find the document associated to the id
			if(res.next()) {

				//Execute query to create the objects category and type of document
				Category cat = null;
				Type type = null;
				String query_cat = "SELECT * FROM categories WHERE id=?;";
				String query_type = "SELECT * FROM types WHERE id=?;";
				ps_cat = connect.prepareStatement(query_cat);
				ps_type = connect.prepareStatement(query_type);
				ps_cat.setLong(1, res.getLong("category_id"));
				ps_type.setLong(1, res.getLong("type_id"));
				//execute the query
				res_cat = ps_cat.executeQuery();
				res_type = ps_type.executeQuery();

				if(res_cat.next() && res_type.next()) {
					cat = new Category(res_cat.getLong("id"), res_cat.getString("label"), res_cat.getString("description"));
					type = new Type(res_cat.getLong("id"), res_type.getString("label"), res_type.getString("description"));
				}

				doc_return = new Document(res.getLong("id"),
						res.getString("ref"),
						res.getString("title"),
						res.getString("author"),
						cat,
						type,
						res.getInt("total_stock"),
						res.getInt("current_stock"));

			}

		} catch (SQLException e) {
			logger.error("GetById : ",e);
		}finally {
			//Close the connection
			try {
				if(res_cat!=null) {
					res_cat.close();
				}
				if(res_type!=null) {
					res_type.close();
				}
				if(res!=null) {
					res.close();
				}
				if(ps_cat!=null) {
					ps_cat.close();
				}
				if(ps_type!=null) {
					ps_type.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connect != null) {
					connect.close();
				}

			}catch(SQLException e) {
				logger.error("GetById/Close Connection : ",e);
			}
		}
		return doc_return;
	}


	@Override
	public void deleteItem(Document item) {
		Connection connect = null;
		PreparedStatement prepared = null;
		String query = "DELETE FROM documents WHERE id=?;";

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
	public void updateItem(Document item) {
		Connection connect = null;
		PreparedStatement prepared = null;
		String query = "UPDATE documents SET title=?, author=?, ref=?, category_id=?, type_id=?, current_stock=?, total_stock=? WHERE id=?;"; //update query

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setString(1, item.getTitle());
			prepared.setString(2, item.getAuthor());
			prepared.setString(3, item.getRef());
			prepared.setLong(4, item.getCategory().getId());
			prepared.setLong(5, item.getType().getId());
			prepared.setInt(6, item.getCurrentStock());
			prepared.setInt(7,  item.getTotalStock());
			prepared.setLong(8, item.getId());

			prepared.executeUpdate();

		}catch(SQLException e) {
			logger.error("UpdateItem : ",e);
		}
		finally {
			try {
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}
			}catch(SQLException e) {
				logger.error("UpdateItem/Close Connection : ",e);
			}
		}

	}

	@Override
	public List<Document> findAll() {
		List<Document> list_docs = new ArrayList<>();
		Connection connect = null;
		PreparedStatement prepared = null, ps_cat = null, ps_type = null;
		ResultSet res = null, res_type = null , res_cat = null;
		String query = "SELECT * FROM documents;";

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			res = prepared.executeQuery();
			// find the document associated to the id
			while(res.next()) {

				//Execute query to create the objects category and type of document
				Category cat = null;
				Type type = null;
				String query_cat = "SELECT * FROM categories WHERE id=?;";
				String query_type = "SELECT * FROM types WHERE id=?;";
				ps_cat = connect.prepareStatement(query_cat);
				ps_type = connect.prepareStatement(query_type);
				ps_cat.setLong(1, res.getLong("category_id"));
				ps_type.setLong(1, res.getLong("type_id"));
				
				//execute the query
				res_cat = ps_cat.executeQuery();
				res_type = ps_type.executeQuery();

				if(res_cat.next() && res_type.next()) {
					cat = new Category(res_cat.getLong("id"), res_cat.getString("label"), res_cat.getString("description"));
					type = new Type(res_cat.getLong("id"), res_type.getString("label"), res_type.getString("description"));
				}

				Document doc_stock = new Document(res.getLong("id"),
						res.getString("ref"),
						res.getString("title"),
						res.getString("author"),
						cat,
						type,
						res.getInt("total_stock"),
						res.getInt("current_stock"));


				list_docs.add(doc_stock);

			}

		} catch (SQLException e) {
			logger.error("FindAll : ",e);
		}
		finally {
			//Close the connection
			try {
				if(res_cat!=null) {
					res_cat.close();
				}
				if(res_type!=null) {
					res_type.close();
				}
				if(res!=null) {
					res.close();
				}
				if(ps_cat!=null) {
					ps_cat.close();
				}
				if(ps_type!=null) {
					ps_type.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connect != null) {
					connect.close();
				}

			}catch(SQLException e) {
				logger.error("FindAll/Close Connection : ",e);

			}
		}
		return list_docs;
	}


	@Override
	public Long createDocument(Document doc) {
		Connection connect = null;
		PreparedStatement prepared = null, ps_id = null;
		ResultSet res = null;
		String query = "INSERT INTO documents(author, title, ref, category_id, type_id, current_stock, total_stock) VALUES(?,?,?,?,?,?,?)";
		Long id_doc = null;
		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setString(1, doc.getAuthor());
			prepared.setString(2, doc.getTitle());
			prepared.setString(3, doc.getRef());
			prepared.setLong(4, doc.getCategory().getId());
			prepared.setLong(5, doc.getType().getId());
			prepared.setInt(6, doc.getCurrentStock());
			prepared.setInt(7, doc.getTotalStock());
			prepared.executeUpdate();

			//get the id of the new document
			query = "SELECT id FROM documents WHERE title=? AND ref=?;";
			ps_id = connect.prepareStatement(query);
			ps_id.setString(1, doc.getTitle());
			ps_id.setString(2, doc.getRef());
			res = ps_id.executeQuery();
			while(res.next()) {
				id_doc = res.getLong("id");
			}			
		}catch(SQLException e) {
			logger.error("createDocument : ",e);

		}
		//Close the connection
		finally {
			try {
				if(res!=null) {
					res.close();
				}
				if(ps_id!=null) {
					ps_id.close();
				}
				if(prepared!=null) {
					prepared.close();
				}
				if(connect!=null) {
					connect.close();
				}
			}catch(SQLException e) {
				logger.error("createDocument/Close Connection : ",e);
			}
		}
		return id_doc; //return the id of the new user or null if there is a problem
	}


	@Override
	public Boolean documentExists(Document document) {
		Connection connect = null;
		PreparedStatement prepared = null;
		ResultSet res = null;
		String query = "SELECT id FROM documents WHERE title=? AND ref=? AND author=?;";

		try {
			connect = DaoConnection.getInstance().getConnection();
			prepared = connect.prepareStatement(query);
			prepared.setString(1, document.getTitle());
			prepared.setString(2, document.getRef());
			prepared.setString(3, document.getAuthor());
			res = prepared.executeQuery();
			
			return res.next();
			
		} catch (SQLException e) {
			logger.error("DocumentExists",e);
		}
		return null; //If an exception is catched
	}

}
