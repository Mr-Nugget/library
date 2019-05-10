import java.util.List;

import org.library.model.Document;
import org.library.model.Loan;

import fr.library.exceptions.JWTCheckingException;
import fr.library.sql.IDocumentDao;
import fr.library.webservices.entrypoint.ConnectionImpl;
import fr.library.webservices.entrypoint.IConnection;
import fr.library.webservices.entrypoint.IManage;
import fr.library.webservices.entrypoint.ISearch;
import fr.library.webservices.entrypoint.ManageImpl;
import fr.library.webservices.entrypoint.SearchImpl;

public class Test {

	public static void main(String[] args) {
		IConnection connection = new ConnectionImpl();
		IManage manage = new ManageImpl();
		ISearch search = new SearchImpl();
		String jwt = connection.login("test@openclassrooms.fr", "1234");
		try {
			List<Loan> larch = manage.getArchivedLoans(jwt);
			for(Loan l : larch) {
				System.out.println(l.toString());
			}
			System.out.println("----------------------");
			List<Loan> lcurr = manage.getCurrentLoans(jwt);
			
			for(Loan l : lcurr) {
				System.out.println(l.toString());
			}
			List<Document> ldoc = search.search(jwt, "Openclassrooms", "AUTHOR");
			System.out.println("----------------------");
			for(Document d : ldoc) {
				System.out.println(d.toString());
			}
			
			connection.logout(jwt);
		} catch (JWTCheckingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}	
