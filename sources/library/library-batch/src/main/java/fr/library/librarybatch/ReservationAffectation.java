package fr.library.librarybatch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.library.wsdl.waiting.IWaitingList;
import fr.library.wsdl.waiting.Loan;


@Component
public class ReservationAffectation {

	@Autowired
	SimpleMailMessage simpleMail;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	IWaitingList service;
	
	@Scheduled(cron="*/5 * * * * ?")
	public void task() {
		List<Loan> lnewLoan = service.updateListAfterTwoDays();
		StringBuilder message = new StringBuilder();
		
		for(Loan loan : lnewLoan) {
			message.append("Bonjour ");
			message.append(loan.getUser().getFirstName());
			message.append(" ");
			message.append(loan.getUser().getLastName());
			message.append(",\n\n");
			message.append("Vous vous êtes inscrit sur la liste d'attente pour l'ouvrage suivant : ");
			message.append(loan.getDoc().getTitle());
			message.append(". L'exemplaire vous attend a la bibliothèque, merci de venir le récupérer sous 48h. Le délais expiré, la réservation ne sera plus effective.\n\nCordialement,\n\nL'équipe de la bibliothèque.");
			
			simpleMail.setText(message.toString());
			simpleMail.setTo(loan.getUser().getMail());
			simpleMail.setSubject("[Bibliothèque Municipale] Votre réservation vous attend");
			javaMailSender.send(simpleMail);
			message = new StringBuilder();
		}
		
	}
}
