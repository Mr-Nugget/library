package test.sql;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.library.model.Category;
import org.library.model.Document;
import org.library.model.Type;
import org.library.model.User;
import org.library.model.WaitingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.library.config.AppConfig;
import fr.library.sql.DaoFactory;
import fr.library.sql.IDocumentDao;
import fr.library.sql.IUserDao;
import fr.library.sql.IWaitingListDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WaitingListDaoIT {

	@Autowired
	private IWaitingListDao dao;
	
	private static IUserDao userDao = DaoFactory.getInstance().getUserDao();
	private static IDocumentDao documentDao = DaoFactory.getInstance().getDocumentDao();
	private static User userTest, userTestAdd;
	private static Document docTest;
	private static int nbWL;
	private static WaitingList waitingList;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userTest = new User(new Long(-1), "test", "test", "test@test.fr", "1234", false);
		userTestAdd = new User(new Long(-2), "test", "test", "test@test.fr", "1234", false);
		Category category = new Category();
		category.setId(new Long(0));
		Type type = new Type();
		type.setId(new Long(0));
		docTest = new Document(new Long(-1), "TEST1", "Document test", "tester", category, type, 3, 0);
		
		// Add a 2 users and a document test into the DB
		userTest.setId(userDao.createUser(userTest));
		userTestAdd.setId(userDao.createUser(userTestAdd));
		docTest.setId(documentDao.createDocument(docTest));		
		
		waitingList = new WaitingList(docTest);
		
	}
	
	
	/**
	 * Create a waiting list for a document with currentStock = 0
	 * Add the user who want to book the document at the top of the waitingList
	 */
	
	
	@Test
	public final void test1createWaitingList() {
		
		nbWL = dao.findAll().size();
		Long id = dao.createWaitingList(docTest, userTest);
		waitingList.setId(id);
		
		assertNotNull(id);
	}
	
	
	@Test
	public final void test2FindAll() {
		assertEquals(nbWL + 1, dao.findAll().size());
	}
	
	@Test
	public final void test3GetById() {
		waitingList = dao.getById(waitingList.getId());
		assertEquals(new Integer(1), waitingList.getLastPosition());
		assertEquals(userTest.getId(), waitingList.getUsersPositions()[0].getId());
	}

	@Test
	public final void test4getByDocument() {
		WaitingList wlByDoc = dao.getByDocument(docTest);
		assertEquals(waitingList.getId(), wlByDoc.getId());
		Document docWithNoWL = new Document();
		docWithNoWL.setId(new Long(-42));
		assertNull(dao.getByDocument(docWithNoWL));
	}

	@Test
	public final void test5AlreadyInTheList() {
		assertTrue(dao.alreadyInTheList(docTest, userTest));
		User userNotInTheList = new User();
		userNotInTheList.setId(new Long(-5));
		assertFalse(dao.alreadyInTheList(docTest, userNotInTheList));
	}
	@Test
	public final void test6AddUserToList() {
		dao.addUserToList(waitingList, userTestAdd);
		assertEquals(new Integer(2), dao.getById(waitingList.getId()).getLastPosition());		
	}
	
	@Test
	public final void test7GetAllWaiting() {
		List<WaitingList> wlL = dao.getUserReservations(userTestAdd);
		assertEquals(1, wlL.size());
		WaitingList wl = wlL.get(0);
		assertEquals(userTestAdd.getId(), wl.getUsersPositions()[1].getId());
	}
	
	@Test
	public final void test8removeAnUserFromTheList() {
		WaitingList wl = dao.getById(waitingList.getId());
		
		Integer position = wl.userPosition(userTestAdd);
		wl.ejectUserByPosition(position);
		dao.removeAnUserFromList(wl, userTestAdd);
		
		 wl = dao.getById(waitingList.getId());

		 assertNull(wl.userPosition(userTestAdd));
		
	}
	
	@Test
	public final void test9DeleteItem() {
		dao.deleteItem(waitingList);
		assertEquals(nbWL, dao.findAll().size());
	}
	
	 @AfterClass
	 public static void setUpAfter() {
         userDao.deleteItem(userTest);
         userDao.deleteItem(userTestAdd);
         documentDao.deleteItem(docTest);
   }

}
