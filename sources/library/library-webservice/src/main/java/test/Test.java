package test;

import java.util.List;

import org.library.model.Loan;

import fr.library.webservices.services.LoanService;

public class Test {

	public static void main(String[] args) {
		List<Loan>  ll = LoanService.getCurrent(new Long(199));
		
		for(Loan l : ll) {
			System.out.println(l.getStatus());
		}
	}
}	
