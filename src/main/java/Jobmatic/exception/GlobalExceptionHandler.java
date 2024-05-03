package Jobmatic.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import Jobmatic.helper.ApiResponse;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<ApiResponse> imageNotException(FileNotFoundException e){	
		return new ResponseEntity<>(new ApiResponse("Error: "+e.getMessage(), false),HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ApiResponse> ioException(IOException e){	
		return new ResponseEntity<>(new ApiResponse("Uploading Error: "+e.getMessage(), false),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiResponse> illegalHandler(IllegalStateException e){
		String message=e.getMessage();
		ApiResponse response=new ApiResponse(message,false);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
	
		
	}
	
	@ExceptionHandler(SchedulerException.class)
	public ResponseEntity<ApiResponse> schedularHandler(SchedulerException e){
		String message="Error while Scheduling";
		ApiResponse response=new ApiResponse(message,false);
		
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	
		
	}
	
	@ExceptionHandler(JobExecutionException.class)
	public ResponseEntity<ApiResponse> excutionHandler(JobExecutionException e){
		String message="Error while Excution";
		ApiResponse response=new ApiResponse(message,false);
		
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	
		
	}
	
	@ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ApiResponse> duplicateError(java.sql.SQLIntegrityConstraintViolationException e){
		String message=e.getMessage();
		ApiResponse response=new ApiResponse(message,false);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
	}

	
	
	
	@ExceptionHandler(InterruptedException.class)
	public ResponseEntity<ApiResponse> threadHandler(InterruptedException e){
		String message=e.getMessage();
		ApiResponse response=new ApiResponse(message,false);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
	}

}
