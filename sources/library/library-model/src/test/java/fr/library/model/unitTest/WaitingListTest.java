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
	
	public static User user1;
	public static User user2;
	public static User user3;
	public static User user4;
	
	@BeforeClass
	public static void setList() {
		Document doc = new Document();
		doc.setId(new Long(-1));
		doc.setTotalstock(5);
		
		wl = new WaitingList(doc);
		
		user1 = new User(new Long(1), "user1", "user1", "test@test.com", "1234", false);
		user2 = new User(new Long(2), "user2", "user2", "test@test.com", "1234", false);
		user3 = new User(new Long(3), "user3", "user3", "test@test.com", "1234", false);
		user4 = new User(new Long(4), "user4", "user4", "test@test.com", "1234", false);
	}
	

	@Test
	public final void test1AddUserInWaitingList() {
		// usersPostions[0]
		wl.addUserInWaitingList(user1);
		// usersPostions[1]
		wl.addUserInWaitingList(user2);
		// usersPostions[2]
		wl.addUserInWaitingList(user3);
		// usersPostions[3]
		wl.addUserInWaitingList(user4);
		
		assertEquals(new Integer(4), wl.getLastPosition());
		
		// The user at index 2 should be user3
		assertEquals("user3", wl.getUsersPositions()[2].getFirstName());
	}

	@Test
	public final void test2getUserPostion() {
		Integer pos = wl.userPosition(user1);
		assertEquals(new Integer(0), pos);
		pos = wl.userPosition(user2);
		assertEquals(new Integer(1), pos);
		pos = wl.userPosition(user3);
		assertEquals(new Integer(2), pos);
		pos = wl.userPosition(user4);
		assertEquals(new Integer(3), pos);
	}
	
	@Test
	public final void test3RemoveTheFirstUser() {
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
