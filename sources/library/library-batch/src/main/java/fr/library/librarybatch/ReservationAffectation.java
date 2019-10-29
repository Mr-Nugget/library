package fr.library.librarybatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.library.wsdl.manage.IManage;

@Component
public class ReservationAffectation {

	@Autowired
	SimpleMailMessage simpleMail;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	IManage service;
	
	@Scheduled(cron="* * * * * 0")
	public void task() {
		// SELECT * FROM documents d, loans l, waitingList wl WHERE wl.document_id = d.id AND d.id = l.document_id AND l.status = 4 AND l.begindate > ?;
	}
}
