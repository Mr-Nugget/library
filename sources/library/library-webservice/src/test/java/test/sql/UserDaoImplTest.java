package test.sql;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.library.model.User;

import fr.library.sql.DaoFactory;
import fr.library.sql.IUserDao;

/**
 * Test class for UserDaoImpl
 * Order of test : createUser, getById, userExist, findAll, updateItem, login, logout and delete
 * @author Titouan
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //tests will be launch in name order
public class UserDaoImplTest {

	static IUserDao userDao = DaoFactory.getInstance().getUserDao();
	static User userTest = new User();
	static int nbUsers;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nbUsers = userDao.findAll().size();
	}
	
	@Test
	public void test1CreateUser() {
		//initializing the userTest
		userTest.setFirstName("Toto");
		userTest.setLastName("Test");
		userTest.setMail("toto@test.com");
		userTest.setPassword("123456");
		userTest.setConnected(false);
		//use the method createUser
		Long id = userDao.createUser(userTest);
		userTest.setId(id);
		nbUsers++;
		assertEquals(nbUsers, userDao.findAll().size());
	}

	@Test
	public void test2GetById() {
		User userGetById = userDao.getById(userTest.getId());
		//test if we got the right user
		assertEquals("toto@test.com", userGetById.getMail());
		assertEquals("Toto", userGetById.getFirstName());
		assertEquals("Test", userGetById.getLastName());
		assertEquals("123456", userGetById.getPassword());
	}

	@Test
	public void test8DeleteItem() {
		assertEquals(nbUsers, userDao.findAll().size());
		//delete the new user
		userDao.deleteItem(userTest);
		//check the number of user
		assertEquals(--nbUsers, userDao.findAll().size());
	}

	@Test
	public void test5UpdateItem() {
		String newmail = "changed@changed.changed";
		userTest.setMail(newmail);
		userDao.updateItem(userTest);
		User userUpdate = userDao.getById(userTest.getId());
		//testing if the mail has changed
		assertEquals(newmail, userUpdate.getMail());
		//testing if it's still the same user
		assertEquals(userTest.getId(), userUpdate.getId());
	}
	
	@Test
	public void test4FindAll() {
		assertEquals(nbUsers, userDao.findAll().size());
	}

	@Test
	public void test3UserExists() {
		//trying with an existing mail into the table
		User userExist = userDao.userExists("toto@test.com");
		assertEquals(userTest.getId(), userExist.getId());
		assertEquals(userTest.getPassword(), userExist.getPassword());
		//trying with a wrong mail
		User userNotExist = userDao.userExists("osidjsfdiojsd@fdsoijdsoifj.com");
		assertEquals(null, userNotExist);
	}
	
	@Test
	public void test6Login() {
		//first the userTest should'nt be connected
		assertFalse(userDao.isConnected(userTest.getId()));
		userDao.login(userTest.getMail(), userTest.getPassword());
		//now it should be connected
		assertTrue(userDao.isConnected(userTest.getId()));
	}
	
	@Test
	public void test7logout() {
		//reverse of login
		assertTrue(userDao.isConnected(userTest.getId()));
		userDao.logout(userTest.getId());
		assertFalse(userDao.isConnected(userTest.getId()));
	}
	
}
