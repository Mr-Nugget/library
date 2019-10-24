package fr.library.model.unitTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.library.model.Document;
import org.library.model.User;
import org.library.model.WaitingList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WaitingListTest {

	public static WaitingList wl;
	
	@BeforeClass
	public static void setList() {
		Document doc = new Document();
		doc.setId(new Long(-1));
		doc.setTotalstock(5);
		
		wl = new WaitingList(doc);

	}
	

	@Test
	public final void test1AddUserInWaitingList() {
		// usersPostions[0]
		wl.addUserInWaitingList(new User(new Long(1), "user1", "user1", "test@test.com", "1234", false));
		// usersPostions[1]
		wl.addUserInWaitingList(new User(new Long(2), "user2", "user2", "test@test.com", "1234", false));
		// usersPostions[2]
		wl.addUserInWaitingList(new User(new Long(3), "user3", "user3", "test@test.com", "1234", false));
		// usersPostions[3]
		wl.addUserInWaitingList(new User(new Long(4), "user4", "user4", "test@test.com", "1234", false));
		
		assertEquals(new Integer(4), wl.getLastPosition());
		
		// The user at index 2 should be user3
		assertEquals("user3", wl.getUsersPositions()[2].getFirstName());
	}

	@Test
	public final void test2RemoveTheFirstUser() {
		wl.removeTheFirstUser();
		assertEquals(new Integer(3), wl.getLastPosition());
		
		assertEquals("user2", wl.getUsersPositions()[0].getFirstName());
		// Remove all users
		wl.removeTheFirstUser();
		assertEquals("user3", wl.getUsersPositions()[0].getFirstName());
		wl.removeTheFirstUser();
		assertEquals("user4", wl.getUsersPositions()[0].getFirstName());
		wl.removeTheFirstUser();
		
		assertNull(wl.removeTheFirstUser());
	}

}
