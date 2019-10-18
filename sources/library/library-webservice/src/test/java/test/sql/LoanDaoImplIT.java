package test.sql;

import static org.junit.Assert.*;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.library.config.AppConfig;
import fr.library.exceptions.DocumentNotAvailableException;
import fr.library.exceptions.LoanStatusException;
import fr.library.sql.DaoFactory;
import fr.library.sql.IDocumentDao;
import fr.library.sql.ILoanDao;
import fr.library.sql.IUserDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoanDaoImplIT {

	private static IUserDao userDao = DaoFactory.getInstance().getUserDao();
	private static IDocumentDao documentDao = DaoFactory.getInstance().getDocumentDao();
	
	@Autowired
	private ILoanDao loanDao;
	
	private static Long loanId;
	private static Loan loanTest;
	private static User userTest;
	private static Document documentTest;
	
	private static Integer loanSize;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userTest = new User();
		userTest.setFirstName("Test");
		userTest.setLastName("Test");
		userTest.setMail("test@test.com");
		userTest.setConnected(false);
		userTest.setMailRecall(true);
		userTest.setPassword("1234");
		
		userTest.setId(userDao.createUser(userTest));
		
		documentTest = new Document();
		documentTest.setAuthor("AuthorTest");
		documentTest.setTitle("TitleTest");
		documentTest.setCurrentstock(1);
		documentTest.setTotalstock(5);
		documentTest.setRef("0000");
		Category cat = documentDao.findAll().get(0).getCategory();
		Type type = documentDao.findAll().get(0).getType();
		documentTest.setCategory(cat);
		documentTest.setType(type);
		Long id = documentDao.createDocument(documentTest);
		documentTest.setId(id);
		
	}
	
	@Test
	public final void test0CreateLoan() throws DocumentNotAvailableException {
		Integer loanSizeBefore = loanDao.findAll().size();
		loanId = loanDao.createLoan(documentTest, userTest);
		loanSize = loanDao.findAll().size();
		assertEquals(loanSizeBefore.intValue()+1, loanSize.intValue());
	}
	
	@Test
	public final void test1GetById() {
		loanTest = loanDao.getById(loanId);
		
		assertEquals(userTest.getId(), loanTest.getUser().getId());
	}
	
	@Test
	public final void test2ExtendLoan() throws LoanStatusException {
		// The default status of a loan is 'IN_PROGRESS'
		assertEquals(Status.IN_PROGRESS, loanTest.getStatus());
		Date beforeExtend = loanTest.getEndDate();
		// Extends the loan and get the updated loanTest
		loanDao.extendLoan(loanId);
		loanTest = loanDao.getById(loanId);
		
		// The loan is now extended
		assertEquals(Status.EXTENDED, loanTest.getStatus());
		
		// The new endDate should be 4 weeks later than the previous date
		assertTrue(beforeExtend.before(loanTest.getEndDate()));
	}
	
	@Test
	public final void test3GetLoansByDocument() {
		List<Loan> loansByDoc = loanDao.getLoansByDocument(documentTest);
		assertEquals(1, loansByDoc.size());
		assertEquals(loanTest.getId(), loansByDoc.get(0).getId());
	}
	
	@Test
	public final void test4AlreadyHaveTheDocument() {
		assertTrue(loanDao.alreadyHaveTheDocument(userTest, documentTest));
	}
	
	@Test
	public final void test5ReturnDocument() throws LoanStatusException {
		//return the document, the status should be Clotured and the current_stock of documentTest should have +1
		loanDao.returnDocument(loanTest);
		
		loanTest = loanDao.getById(loanId);
		assertEquals(Status.CLOTURED, loanTest.getStatus());
		
		assertEquals(documentTest.getCurrentstock().intValue() + 1, loanTest.getDoc().getCurrentstock().intValue());
	}
	
	@Test
	public final void test99DeleteLoan() {
		loanDao.deleteItem(loanTest);
		
		assertEquals(loanSize - 1, loanDao.findAll().size());
	}

	@AfterClass
	public static void setUpAfterClass() {
		//documentDao.deleteItem(documentTest);
		userDao.deleteItem(userTest);
	}

}
