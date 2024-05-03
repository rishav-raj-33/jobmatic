package Jobmatic.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Jobmatic.helper.AppConstants;
import Jobmatic.helper.Parser;
import Jobmatic.payloads.ScheduleResponse;
import Jobmatic.serviceClass.JobServiceClass;

@RestController
@RequestMapping("/api/data")
public class TaskController {
	@Autowired
	private JobServiceClass serviceClass;
	@Autowired
	private Parser pasParser;
	
	@CrossOrigin
	@PostMapping("/extract/schedule")
	public ResponseEntity<ScheduleResponse> extractData(@RequestPart("excel") MultipartFile excel,@RequestPart String dateTime,@RequestPart String timeZone,@RequestParam(required = true) String token) throws IOException, SchedulerException{
		if(!token.equalsIgnoreCase(AppConstants.ORIGINAL_TOKEN)) return new ResponseEntity<>(new ScheduleResponse("Token Invalid",false,null,null),HttpStatus.UNAUTHORIZED);
		LocalDateTime scheduleTime=pasParser.dateTimeParser(dateTime);
		ZoneId zoneId=pasParser.timeZoneParser(timeZone);
		ZonedDateTime fullScheduleTime=ZonedDateTime.of(scheduleTime, zoneId);
		if(fullScheduleTime.isBefore(ZonedDateTime.now())) {
		 throw new IllegalStateException("The time has already passed, so cannot schedule it");
		}
		serviceClass.DataExtractor(excel);
	  ScheduleResponse response=serviceClass.scheduleTask(fullScheduleTime);
	  return new ResponseEntity<>(response,HttpStatus.ACCEPTED);	
	}
	
	
	

	
	
	
	

}
