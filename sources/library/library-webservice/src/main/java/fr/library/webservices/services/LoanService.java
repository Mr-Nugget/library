package fr.library.webservices.services;


import java.util.Date;

import java.util.List;


import org.library.model.Loan;
import org.library.model.Status;


import fr.library.exceptions.LoanStatusException;
import fr.library.sql.DaoFactory;
import fr.library.sql.ILoanDao;

public class LoanService {
	
	private static ILoanDao dao = DaoFactory.getInstance().getLoanDao();
	
	public static List<Loan> getCurrent(Long id){
		List<Loan> listCurrent = dao.getListCurrent(id);
		Date today = new Date();
		
		for(Loan l : listCurrent) {
			if(l.getEndDate().before(today)) {
				l.setStatus(Status.LATE);
			}
		}
		return listCurrent;
	}
	
	public static List<Loan> getArchived(Long id){
		return dao.getListArchived(id);
	}
	
	public static void extend(Long id) throws LoanStatusException {
		dao.extendLoan(id);
	}
	
	public static List<Loan> expired(){
		return dao.getExpiredLoans();
	}
	
	public static List<Loan> mailRecall(){
		return dao.forMailRecall();
	}
}
