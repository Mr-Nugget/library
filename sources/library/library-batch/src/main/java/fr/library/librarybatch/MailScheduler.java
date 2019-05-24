package fr.library.librarybatch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.library.wsdl.manage.IManage;
import fr.library.wsdl.manage.Loan;
import fr.library.wsdl.manage.Status;




/**
 * Scheduler, this job will be launched by Srping batch every week
 * @author Titouan
 *
 */
@Component
public class MailScheduler {
	@Autowired
	SimpleMailMessage simpleMail;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	IManage service;

	private static Logger logger = Logger.getLogger(MailScheduler.class);
	//cron="0 10 * * 1,5" ? -> Every Mondays and Fridays at 10AM
	@Scheduled(cron="* * * * * 0")
	public void task() {
		StringBuilder message = new StringBuilder();
		DateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");

		// Get expired loans list from webservice ManageLoans
		List<Loan> loansList = null;
		loansList = service.loanExpired();
		// For every user who have a loan in progress
		for(Loan loan : loansList) {
			System.out.println(loan.toString());
			message.append("Bonjour ");
			message.append(loan.getUser().getFirstName());
			message.append(" ");
			message.append(loan.getUser().getLastName());
			message.append(",\n\n");
			message.append("Vous avez emprunté ");
			message.append(loan.getDoc().getTitle());
			message.append(" à la date du ");
			message.append(simpleFormat.format(loan.getBeginDate()));
			message.append(".\n");
			message.append("Vous deviez nous retourner cet emprunt au plus tard le ");
			message.append(simpleFormat.format(loan.getEndDate()));
			message.append(".\n");

			// Message change if the user has already extended its loan
			if(loan.getStatus() == Status.IN_PROGRESS) {
				message.append("Si vous estimez avoir besoin d'un délais supplémentaire,");
				message.append(" vous pouvez augmenter la durée de votre prêt de 4 semaines");
				message.append(" en vous rendant sur votre espace personnel.");
			}else {
				message.append("Vous avez déjà augmenter la duréé initiale de votre prêt, ");
				message.append("il est donc impératif de nous retourner votre emprunt le plus rapidemment possible, sous peine d'être sanctionné. ");
			}
			message.append("\n\nEn vous souhaitant une agréable journée,\n\nL'équipe de la bibliothèque municipale");
			simpleMail.setText(message.toString());
			simpleMail.setTo(loan.getUser().getMail());
			javaMailSender.send(simpleMail);
			message = new StringBuilder();

		}
		logger.info("Mails envoyés");
	}
}
