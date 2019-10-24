package org.library.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.library.wsdl.waiting.IWaitingList;
import fr.library.wsdl.waiting.WaitingList;
import fr.library.wsdl.waiting.WaitingListImplService;

public class WaitingListServiceIT {
	
	
	IWaitingList service = new WaitingListImplService().getWaitingListImplPort();
	
	@Test
	public final void test0getAll() {
		List<WaitingList> wlL = service.getAllWaiting(new Long(9));
		
		assertFalse(wlL.isEmpty());
	}

}
