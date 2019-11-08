package test.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.library.model.Category;
import org.library.model.Document;
import org.library.model.Loan;
import org.library.model.Status;
import org.library.model.Type;
import org.library.model.User;
import org.library.model.WaitingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.library.config.AppConfig;
import fr.library.exceptions.DocumentNotAvailableException;
import fr.library.sql.DaoFactory;
import fr.library.sql.IDocumentDao;
import fr.library.sql.ILoanDao;
import fr.library.sql.IUserDao;
import fr.library.sql.IWaitingListDao;
import fr.library.webservices.services.WaitingListService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CancelAfterTwoDaysBatchTest {

	@Autowired
	private IWaitingListDao waitingDao;
	
	@Autowired
	private ILoanDao loanDao;
	
	@Autowired
	private WaitingListService wlService;
	
	private static IDocumentDao documentDao = DaoFactory.getInstance().getDocumentDao();
	
	private static IUserDao userDao = DaoFactory.getInstance().getUserDao();
	
	private static Document docTest;
	private static User userExpired, userAwaiting;
	private static Loan loanExpired;
	private static List<Loan> newReservations;
	private static WaitingList waitingListTest;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// A document only one total stock already borrowed by userExpired
		
		docTest = new Document();
		docTest.setTitle("TestForBatch");
		docTest.setAuthor("TestForBatch");
		docTest.setRef("TEST01");
		docTest.setTotalstock(1);
		docTest.setCurrentstock(1);
		docTest.setCategory(new Category(new Long(0), "test", "a test document category"));
		docTest.setType(new Type(new Long(0), "test", "a test document type"));
		
		docTest.setId(documentDao.createDocument(docTest));
		
		// A user who reserve the document but did not come take it after 48h
		userExpired = new User();
		userExpired.setFirstName("testFirstname");
		userExpired.setLastName("testLastname");
		userExpired.setMail("test@test.com");
		userExpired.setPassword("test");
		
		userExpired.setId(userDao.createUser(userExpired));
		
		// A user in the waitingList for the document
		userAwaiting = new User();
		userAwaiting.setFirstName("testFirstnameAwaiting");
		userAwaiting.setLastName("testLastnameAwaiting");
		userAwaiting.setMail("testAwaiting@test.com");
		userAwaiting.setPassword("testAwaiting");
		
		userAwaiting.setId(userDao.createUser(userAwaiting));
	}
	
	@Test
	public final void test0() throws DocumentNotAvailableException {
		// Creating the expired loans
		loanExpired = new Loan();
				
		Long loanId = loanDao.createLoan(docTest, userExpired, Status.AWAITING);
		loanExpired.setId(loanId);
		
		LocalDate date = LocalDate.now().minusDays(2);
		Date twoDaysAgo = java.sql.Date.valueOf(date);
				
		loanExpired.setBeginDate(twoDaysAgo);
				
		// Add userAwaiting in the waitingList of the same document
		waitingListTest = new WaitingList(docTest);
		waitingListTest.setId(waitingDao.createWaitingList(docTest, userAwaiting));
	}
	
	@Test
	public final void test1Service() {
		newReservations = wlService.removeUserAfterTowDays();
		assertFalse(newReservations.isEmpty());
	}
	
	@Test
	public final void test99() {
		loanDao.deleteItem(loanExpired);
		if(newReservations != null) {
			for(Loan loan : newReservations) {
				loanDao.deleteItem(loan);
			}
		}
		waitingDao.deleteItem(waitingListTest);
	}
	
	 @AfterClass
	 public static void setUpAfter() {
        documentDao.deleteItem(docTest);
        userDao.deleteItem(userExpired);
        userDao.deleteItem(userAwaiting);
	 }

}
