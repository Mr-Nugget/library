package fr.library.webservices.entrypoint;

import java.util.List;


import javax.jws.WebMethod;
import javax.jws.WebService;

import org.library.model.Loan;


import fr.library.exceptions.JWTCheckingException;

/**
 * Interface of the Manage Loans Webservice, methods to get a list of current and expired loans and to borrow a new document or extend a loan
 * @author Titouan
 *
 */
@WebService
public interface IManage {
	
	// Get the list of current loans of a user thanks to the id of jwt token
	@WebMethod
	public List<Loan> getCurrentLoans(String jwt) throws JWTCheckingException;
	
	// Get the list of archived loans of a user thanks to the id of jwt token
	@WebMethod
	public List<Loan> getArchivedLoans(String jwt) throws JWTCheckingException;
	
	// Add 4 more week to endDate of a loan if it is not already extended
	@WebMethod
	public boolean extendLoan(String jwt, Long loanId) throws JWTCheckingException;

	// Get the list of expired loans -> endDate < Today
	@WebMethod
	public List<Loan> loanExpired();
	
	// Get a map of user/list of almost expired loan for recall
	@WebMethod
	public List<Loan> mailRecall();
	
}
