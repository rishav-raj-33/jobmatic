package Jobmatic.helper;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import Jobmatic.serviceClass.JobServiceClass;
import jakarta.mail.MessagingException;

@Component
public class SendEmail extends QuartzJobBean {
	
	@Autowired
	private JobServiceClass job;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			this.job.scheduleEmail();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	

}
