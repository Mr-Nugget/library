package fr.library.webservices.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.library.model.Loan;
import org.library.model.Status;
import org.library.model.User;

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
	
	public static Map<User, List<Loan>> mailRecall(){
		List<Loan> almostExpired = dao.forMailRecall();
		
		Map<User, List<Loan>> res = new HashMap<>();
		
		for(Loan l : almostExpired) {
			if(res.containsKey(l.getUser())) {
				res.get(l.getUser()).add(l);
			}else {
				List<Loan> temp = new ArrayList<>();
				temp.add(l);
				res.put(l.getUser(), temp);
			}
		}
		return res;
	}
}
