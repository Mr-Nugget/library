package fr.library.librarybatch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.library.wsdl.manage.IManage;
import fr.library.wsdl.waiting.IWaitingList;
import fr.library.wsdl.waiting.User;

@Component
public class ReservationAffectation {

	@Autowired
	SimpleMailMessage simpleMail;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	IWaitingList service;
	
	@Scheduled(cron="* * * * * 0")
	public void task() {
		List<User> lUser =  service.updateListAfterTwoDays();
		
		for(User user : lUser) {
			
		}
		
	}
}
