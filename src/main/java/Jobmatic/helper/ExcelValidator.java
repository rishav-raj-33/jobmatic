package Jobmatic.helper;


import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExcelValidator {
	
	private  final String pattern="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
	
	public void validate(MultipartFile file) {
		  String fileName = file.getOriginalFilename();
		  if (!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx")) {
		    throw new IllegalStateException("Only Excel files allowed!");
		  }
		}
	
	public void emailValidate(String email) {                     //Old Method of Regex because Validation Dependency gives error in pom.xml file
		Pattern emailPattern=Pattern.compile(this.pattern);
		Matcher emailMatcher=emailPattern.matcher(email);
		if(!emailMatcher.matches()) {
			throw new IllegalStateException("Invalid Email");	
		}
		
	}
	

	}


