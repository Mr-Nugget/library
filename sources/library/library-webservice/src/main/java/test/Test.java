package test;

import fr.library.webservices.entrypoint.ConnectionImpl;
import fr.library.webservices.entrypoint.IConnection;

public class Test {

	public static void main(String[] args) {
		IConnection connection = new ConnectionImpl();
		connection.sendMail("bonjour", "bonjour", "t_raimbault@yahoo.fr");
	}
}	
