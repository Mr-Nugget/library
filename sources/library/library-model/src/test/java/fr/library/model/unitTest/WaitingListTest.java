package fr.library.model.unitTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.library.model.User;
import org.library.model.WaitingList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WaitingListTest {

	public static WaitingList wl;
	
	@BeforeClass
	public static void setList() {
		wl = new WaitingList();
	}
	

	@Test
	public final void test1AddUserInWaitingList() {
		wl.addUserInWaitingList(new User(new Long(1), "user1", "user1", "test@test.com", "1234", false));
		wl.addUserInWaitingList(new User(new Long(2), "user2", "user2", "test@test.com", "1234", false));
		wl.addUserInWaitingList(new User(new Long(3), "user3", "user3", "test@test.com", "1234", false));
		wl.addUserInWaitingList(new User(new Long(4), "user4", "user4", "test@test.com", "1234", false));
		
		assertEquals(new Integer(4), wl.lastPosition);
		
		assertEquals(4, wl.getUsersPositions().size());
		
		assertEquals("user3", wl.getUsersPositions().get(3).getFirstName());
	}

	@Test
	public final void test2RemoveTheFirstUser() {
		wl.removeTheFirstUser();
		assertEquals(new Integer(3), wl.lastPosition);
		assertEquals(3, wl.getUsersPositions().size());
		
		assertEquals("user2", wl.getUsersPositions().get(1).getFirstName());
		// Remove all users
		wl.removeTheFirstUser();
		assertEquals("user3", wl.getUsersPositions().get(1).getFirstName());
		wl.removeTheFirstUser();
		assertEquals("user4", wl.getUsersPositions().get(1).getFirstName());
		wl.removeTheFirstUser();
		
		assertNull(wl.removeTheFirstUser());
	}

}
