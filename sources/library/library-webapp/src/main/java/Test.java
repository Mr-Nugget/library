import fr.library.wsdl.connect.ConnectionImplService;
import fr.library.wsdl.connect.IConnection;

public class Test {

	public static void main(String[] args) {
		
		IConnection service = new ConnectionImplService().getConnectionImplPort();
		service.sendMail("bonjour", "bonjour", "t_raimbault@yahoo.fr");

	}

}
