package Jobmatic.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Jobmatic.helper.ApiResponse;


@RestController
@RequestMapping("/api/auth")
public class SecurityController {
	
	private String oUserName="Rishav";
	private String oPassword="Rishav@123";
	
    @CrossOrigin
	@GetMapping("/user")
	public ResponseEntity<ApiResponse> user(@RequestParam(required = true) String user,@RequestParam(required = true) String password){
		if(user.equals(oUserName)&& password.equals(oPassword)) 
			return new ResponseEntity<ApiResponse>(new ApiResponse("Identified", true),HttpStatus.OK);
		 else {
			return new ResponseEntity<ApiResponse>(new ApiResponse("Access Denied", false),HttpStatus.NON_AUTHORITATIVE_INFORMATION);
			
		}
	}
    

    
    

	
	
	
	

}
