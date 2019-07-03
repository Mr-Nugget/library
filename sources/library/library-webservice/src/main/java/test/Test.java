package test;

import java.util.List;
import java.util.Map;

import org.library.model.Loan;
import org.library.model.User;

import fr.library.sql.DaoFactory;
import fr.library.sql.ILoanDao;
import fr.library.webservices.services.LoanService;

public class Test {

	public static void main(String[] args) {
		Map<User, List<Loan>> res = LoanService.mailRecall();
		
		System.out.println(res.size());
		System.out.println(res.toString());
	}
}	
