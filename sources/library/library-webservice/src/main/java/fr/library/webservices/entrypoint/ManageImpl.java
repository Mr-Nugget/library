package fr.library.webservices.entrypoint;


import java.util.List;


import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.library.model.Loan;


import fr.library.exceptions.JWTCheckingException;
import fr.library.exceptions.LoanStatusException;
import fr.library.helpers.JWTHelper;
import fr.library.webservices.services.LoanService;


/**
 * Web service to manage loans, follow current loans, check old loans, and extend current loans
 * @author Titouan
 *
 */
@WebService(endpointInterface = "fr.library.webservices.entrypoint.IManage")
public class ManageImpl implements IManage{
	
	Logger logger = Logger.getLogger(ManageImpl.class);

	/**
	 * Get the list of the current loan (extended or in progress)
	 * @param id
	 * @return
	 * @throws JWTCheckingException 
	 */
	@Override
	public List<Loan> getCurrentLoans(String jwt) throws JWTCheckingException {
		Long id = JWTHelper.checkJwt(jwt);
		if(id!=null) {
			return LoanService.getCurrent(id);
		}else {
			return null;
		}
	}
	/**
	 * Get the list of archived loans (already clotured)
	 * @param id
	 * @return
	 * @throws JWTCheckingException 
	 */
	@Override
	public List<Loan> getArchivedLoans(String jwt) throws JWTCheckingException{
		Long id = JWTHelper.checkJwt(jwt);
		if(id!=null) {
			return LoanService.getArchived(id);
		}else {
			return null;
		}
	}

	/**
	 * Add 4 weeks to the endDate of a loan
	 * @param loan
	 * @return
	 * @throws JWTCheckingException 
	 */
	@Override
	public boolean extendLoan(String jwt, Long loanId) throws  JWTCheckingException {
		Long id = JWTHelper.checkJwt(jwt);
		
		if(id!=null) {
			try {
				LoanService.extend(loanId);
			} catch (LoanStatusException e) {
				logger.error("Erreur lors de l'extension d'un prêt : le prêt a peut-être déjà été prolongé ou sa date de retour est expirée.");
			}
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Loan> loanExpired() {
		return LoanService.expired();
	}
	@Override
	public List<Loan> mailRecall() {
		return LoanService.mailRecall();
	}
}
