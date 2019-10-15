package fr.library.librarybatch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.library.wsdl.manage.User;
import fr.library.wsdl.manage.IManage;
import fr.library.wsdl.manage.Loan;

import fr.library.tools.DateConverter;

/**
 * Mail batch for alost expired loans recall
 * @author Titouan
 *
 */
@Component
public class MailRecall {
	@Autowired
	SimpleMailMessage simpleMail;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	IManage service;
	
	public MailRecall() {
		
	}
	
	//cron="0 10 * * 1,5" ? -> Every Mondays and Fridays at 10AM
	@Scheduled(cron="*/5 * * * * ?")
	public void task() {
		StringBuilder message = new StringBuilder();
		DateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Map<String, List<Loan>> mapForMail = sortByUser();
		
		for(String userMail : mapForMail.keySet()) {	
			message.append("Bonjour,\n\nUn ou plusieurs de vos prêts arrivent à leur terme.\nMerci de bien vouloir les retourner avant la date indiquée :\n\n");
			
			List<Loan> listCurrentLoan = mapForMail.get(userMail);
			
			// List all the loan in one mail for each user
			for(Loan currentLoan : listCurrentLoan) {
				message.append("- ");
				message.append(currentLoan.getDoc().getTitle());
				message.append(" de ");
				message.append(currentLoan.getDoc().getAuthor());
				message.append(" : à rendre au plus tard le ");
				message.append(simpleFormat.format(DateConverter.xmlGregorianToDate(currentLoan.getEndDate())));
				message.append("\n\n");
			}
			
			message.append("En vous souhaitant une bonne journée,\n\nL'équipe de la bibliothèque.");
			
			simpleMail.setText(message.toString());
			simpleMail.setTo(userMail);
			simpleMail.setSubject("[Bibliothèque Municipale] Certains de vos prêts arrivent à expiration");
			javaMailSender.send(simpleMail);
			message = new StringBuilder();
		}
	}
	
	// Return a map of email associated with the list of almost expired loans
	public Map<String, List<Loan>>sortByUser() {
		List<Loan> list = service.mailRecall();		
		Map<String, List<Loan>> res = new HashMap<>();
		
		for(Loan loan : list) {

			if(res.containsKey(loan.getUser().getMail())) {
				// Add the current loan to the user list if he is already in the map
				res.get(loan.getUser().getMail()).add(loan);
			}else {
				// If the current user is not present in the map, add a new row
				List<Loan> lTemp = new ArrayList<Loan>();
				lTemp.add(loan);
				res.put(loan.getUser().getMail(), lTemp);
			}
		}
		return res;
	}
}
