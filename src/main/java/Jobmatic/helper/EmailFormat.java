package Jobmatic.helper;

import org.springframework.stereotype.Component;

@Component
public class EmailFormat {
	public String emailFormat(String client,String CompanyName) {
		 return String.format("Greetings %s, \n My name is Rishav Raj and I'm a 4th year Student at Punjab Technical Unversity majoring in B.tech. I'm writing to express my strong interest in interning at %s.\n While I haven't seen any advertised internship positions of Java Developer(Spring Boot), I was curious if %s offers internship programs that potentially lead to full-time job opportunities upon successful completion.\nI've attached my resume for your review, which further details my academic background and relevant experience. I'm eager to learn more about potential internship opportunities and how I can contribute to %s's continued success.\r\n Thank you for your time and consideration.\nSincerely,\n Rishav Raj\n Here is my Resume for your reference\n"
		 		,client,CompanyName,CompanyName,CompanyName);
	 }
	

}
