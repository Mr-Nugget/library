package test.sql;

import static org.junit.Assert.*;

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
		docTest = new Document(new Long(-1), "TEST1", "Document test", "tester", category, type, 0, 3);
		
		// Add a 2 users and a document test into the DB
		userTest.setId(userDao.createUser(userTest));
		userTestAdd.setId(userDao.createUser(userTestAdd));
		docTest.setId(documentDao.createDocument(docTest));		
		waitingList = new WaitingList();
		
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
		waitingList = dao.getById(waitingList.id);
		assertEquals(1, waitingList.getUsersPositions().size());
		assertEquals(userTest.getId(), waitingList.getUsersPositions().get(1).getId());
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
		assertEquals(2, dao.getById(waitingList.getId()).getUsersPositions().size());		
	}
	
	@Test
	public final void test7DeleteItem() {
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
