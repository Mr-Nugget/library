import fr.library.wsdl.connect.ConnectionImplService;
import fr.library.wsdl.connect.IConnection;
import fr.library.wsdl.waiting.IWaitingList;
import fr.library.wsdl.waiting.WaitingListImplService;

public class Test {

	public static void main(String[] args) {
		
		
		IWaitingList service = new WaitingListImplService().getWaitingListImplPort();
		
		service.getAllWaiting(new Long(9));
	}

}
