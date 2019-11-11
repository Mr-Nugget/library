package test.sql;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.library.model.Category;
import org.library.model.Document;
import org.library.model.Type;

import fr.library.sql.DaoFactory;
import fr.library.sql.IDocumentDao;

/**
 * Test class for DocumentDaoImpl
 * @author Titouan
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DocumentDaoImplTest {

	
	static IDocumentDao documentDao = DaoFactory.getInstance().getDocumentDao();
	static Document documentTest;
	static int nbDocuments;
	static Long categoryId;
	static Long typeId;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nbDocuments = documentDao.findAll().size();
	}
	@Test
	public final void test6SearchByCriteria() {
		assertEquals(1, documentDao.searchByCriteria(IDocumentDao.AUTHOR, documentTest.getAuthor()).size());
		assertEquals(1, documentDao.searchByCriteria(IDocumentDao.TITLE, documentTest.getTitle()).size());
		assertEquals(documentTest.getRef(), documentDao.searchByCriteria(IDocumentDao.REFERENCE, documentTest.getRef()).get(0).getRef());
	}

	@Test
	public final void test3GetById() {
	
		Document documentGetById = documentDao.getById(documentTest.getId());
		// Check if the document returned is the same as documentTest
		assertEquals(documentTest.getAuthor(), documentGetById.getAuthor());
		assertEquals(documentTest.getTitle(), documentGetById.getTitle());
		assertEquals(documentTest.getRef(), documentGetById.getRef());
		assertEquals(categoryId, documentGetById.getCategory().getId());
		assertEquals(typeId, documentGetById.getType().getId());
	}

	@Test
	public final void test7DeleteItem() {
		// Delete documentTest
		documentDao.deleteItem(documentTest);
		assertEquals(--nbDocuments, documentDao.findAll().size());
				
	}

	@Test
	public final void test4UpdateItem() {
		
		documentTest.setCurrentstock(new Integer(2));
		documentDao.updateItem(documentTest);
		Document documentUpdate = documentDao.getById(documentTest.getId());
		assertEquals(documentUpdate.getCurrentstock(), new Integer(2));
	}

	@Test
	public final void test2FindAll() {
		assertEquals(nbDocuments, documentDao.findAll().size());
	}

	@Test
	public final void test1CreateDocument() {
		assertEquals(nbDocuments, documentDao.findAll().size());
		
		documentTest = new Document();
		documentTest.setAuthor("AuthorTest");
		documentTest.setTitle("TitleTest");
		documentTest.setCurrentstock(new Integer(1));
		documentTest.setTotalstock(new Integer(5));
		documentTest.setRef("0000");
		Category cat = documentDao.findAll().get(0).getCategory();
		categoryId = cat.getId();
		Type type = documentDao.findAll().get(0).getType();
		typeId = type.getId();
		documentTest.setCategory(cat);
		documentTest.setType(type);
		Long id = documentDao.createDocument(documentTest);
		documentTest.setId(id);
		
		assertEquals(++nbDocuments, documentDao.findAll().size());
	}
	
	@Test
	public final void test5DocumentExists() {
		assertTrue(documentDao.documentExists(documentTest));
		//test an unknown document
		Document documentExist = new Document();
		documentExist.setRef("123456");
		documentExist.setAuthor("NUUUUL");
		documentExist.setTitle("NOTHING");
		assertFalse(documentDao.documentExists(documentExist));
	}

}
