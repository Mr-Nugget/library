package fr.library.sql;


/*
 * DAO Factory, to get access to a unique instance of the tables DAO (user, document, loan)
 */

public class DaoFactory {

	private  IDocumentDao documentDao = null;
	private  ILoanDao loanDao = null;
	private  IUserDao userDao = null;
	private  IWaitingListDao waitingListDao = null;
	private static DaoFactory instance = null;

	
	/**
	 * 
	 * @return
	 */
	public static DaoFactory getInstance() {
		if(instance != null) {
			return instance;
		}else {
			instance = new DaoFactory();
			return instance;
		}
	}

	// Get DAO Interfaces of the table Loans, Documents and Users

	/**
	 * 
	 * @return IDocumentDao
	 */
	public IDocumentDao getDocumentDao() {
		if (documentDao != null) {
			return documentDao;
		} else {
			documentDao = new DocumentDaoImpl();
			return documentDao;
		}
	}

	public ILoanDao getLoanDao() {
		if (loanDao != null) {
			return loanDao;
		} else {
			loanDao = new LoanDaoImpl();
			return loanDao;
		}
	}
	
	public IWaitingListDao getWaitingListDao() {
		if (waitingListDao != null) {
			return waitingListDao;
		} else {
			waitingListDao = new WaitingListDaoImpl();
			return waitingListDao;
		}
	}

	public  IUserDao getUserDao() {
		if (userDao != null) {
			return userDao;
		} else {
			userDao = new UserDaoImpl();
			return userDao;
		}
	}
}
