package fr.library.sql;

import java.util.List;


import org.library.model.Document;
import org.library.model.Loan;
import org.library.model.Status;
import org.library.model.User;

import fr.library.exceptions.DocumentNotAvailableException;
import fr.library.exceptions.LoanStatusException;

/**
 * Methods to get access to the table loans of library
 * @author Titouan
 *
 */
public interface ILoanDao extends ICrudDao<Loan>{
	
	/**
	 * Return the list of all the archived loans of a user
	 * @param userId
	 * @return
	 */
	public List<Loan> getListArchived(Long userId); 
	
	/**
	 * Return the list of the loans in_progress of a user
	 * @param userId
	 * @return
	 */
	public List<Loan> getListCurrent(Long userId); 
	
	/**
	 *  Add 4 weeks to the end date of a loan and change the status to EXTENDED
	 * @param loanId
	 * @throws LoanStatusException
	 */
	public void extendLoan(Long loanId) throws LoanStatusException;
	
	/**
	 * Update the loans with endate < today
	 */
	public void updateLateLoans();
	
	/**
	 * Set the status to CLOTURED and change the end_date
	 * @param loan
	 * @throws LoanStatusException
	 */
	public void returnDocument(Loan loan) throws LoanStatusException; 
	
	/**
	 * Create a new Loan depending on a user and a document and return the id of the new loan, throw an exception if the document is not available
	 * @param doc
	 * @param user
	 * @param status
	 * @return
	 * @throws DocumentNotAvailableException
	 */
	public Long createLoan(Document doc, User user, Status status) throws DocumentNotAvailableException;
	
	/**
	 * Get a list of expired loans -> endDate < today
	 * @return
	 */
	public List<Loan> getExpiredLoans();
	

	/**
	 * Get a list of soon expired current loans 
	 * @return list of loans
	 */
	public List<Loan> forMailRecall();

	/**
	 * Return when the document will be available if there is no more stock
	 * @param doc
	 * @return
	 */
	public List<Loan> getLoansByDocument(Document doc);
	
	/**
	 * Check if a user already rent the document in param 
	 * @param user
	 * @param doc
	 * @return
	 */
	public Boolean alreadyHaveTheDocument(User user, Document doc);
	
	/**
	 * Clotured loans awaiting expired
	 * @return 
	 */
	public List<Loan> cloturedAfterTwoDays();
}
