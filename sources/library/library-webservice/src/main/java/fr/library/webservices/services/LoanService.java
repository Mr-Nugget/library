package fr.library.webservices.services;

import java.util.List;

import org.library.model.Loan;

import fr.library.exceptions.LoanStatusException;
import fr.library.sql.DaoFactory;
import fr.library.sql.ILoanDao;

public class LoanService {
	
	private static ILoanDao dao = DaoFactory.getInstance().getLoanDao();
	
	public static List<Loan> getCurrent(Long id){
		return dao.getListCurrent(id);
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
}
