package fr.library.librarybatch;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.library.wsdl.manage.IManage;

@Component
public class UpdateLateLoans {
	@Autowired
	IManage service;

	private static Logger logger = Logger.getLogger(UpdateLateLoans.class);
	//cron="0 0 * * *" ? -> Everyday at midnight
	@Scheduled(cron="0 0 0 * * ?")
	public void task() {
		service.updateLateLoans();
		logger.info("Update late loans OK");
	}
}
