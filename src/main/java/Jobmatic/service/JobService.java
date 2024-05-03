package Jobmatic.service;

import java.io.IOException;

import java.time.ZonedDateTime;

import org.quartz.SchedulerException;
import org.springframework.web.multipart.MultipartFile;


import Jobmatic.payloads.ScheduleResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

public interface JobService {
	
	void DataExtractor(MultipartFile file)throws IOException;
	void scheduleEmail()throws AddressException, MessagingException;
	public ScheduleResponse scheduleTask(ZonedDateTime dateTime) throws SchedulerException;
	

}
