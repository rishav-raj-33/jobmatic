package Jobmatic.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

@Component
public class Parser {

	
	
	public LocalDateTime dateTimeParser(String date) {
			LocalDateTime dateTime=null;
		  String pattern = "yyyy-MM-dd'T'HH:mm";
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	        
	        try {
	        	 dateTime = LocalDateTime.parse(date, formatter);
				
			} catch (DateTimeParseException e) {
			   e.printStackTrace();
			}
	        
	        
		return dateTime;
		
	}
	
	
	public ZoneId timeZoneParser(String timeZone) {
		return ZoneId.of(timeZone);
	}
	
	
}
