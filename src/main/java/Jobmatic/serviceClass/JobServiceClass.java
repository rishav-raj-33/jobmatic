package Jobmatic.serviceClass;


import java.io.IOException;

import java.time.ZonedDateTime;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import Jobmatic.entity.JobEntity;
import Jobmatic.helper.AppConstants;
import Jobmatic.helper.EmailFormat;
import Jobmatic.helper.EmailSender;
import Jobmatic.helper.ExcelValidator;
import Jobmatic.helper.SchedulingHelper;
import Jobmatic.payloads.ScheduleResponse;
import Jobmatic.repository.JobRepo;
import Jobmatic.service.JobService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.transaction.Transactional;
@Service
public class JobServiceClass implements JobService {
	
	@Autowired
	private ExcelValidator validator;
	@Autowired
	private JobRepo jobRepo;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private EmailFormat emailFormat;
	@Autowired
	private SchedulingHelper helper;
	@Autowired
	private Scheduler schedular;

	

	@Override
	@Transactional
	public void scheduleEmail() throws AddressException, MessagingException {
		
       List<JobEntity> getJob=this.jobRepo.findAll();
       
       getJob.stream().forEach(
    		   (obj)->{
    			String text=emailFormat.emailFormat(obj.getClient(), obj.getCompanyName());
    			String to=obj.getEmail();
    			try {
					emailSender.sendEmail(to,AppConstants.FROM , AppConstants.SUBJECT, text);
					this.jobRepo.delete(obj);
						Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
    		   });
		
		
	}

	@Override
	@Transactional
	public void DataExtractor(MultipartFile file) throws IOException {
		validator.validate(file);
		Workbook workbook = XSSFWorkbookFactory.create(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		
		for(int i=1;i<=sheet.getLastRowNum();i++) {
			 Row row = sheet.getRow(i);
			 Cell name=row.getCell(0);
			 Cell companyName=row.getCell(1);
			 Cell email=row.getCell(2);
			 JobEntity entity=new JobEntity();
			 try {
				 validator.emailValidate(email.getStringCellValue());
				 entity.setClient(name.getStringCellValue());
				 entity.setCompanyName(companyName.getStringCellValue());
				 entity.setEmail(email.getStringCellValue());
				 this.jobRepo.save(entity);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		}		
		
	}

	@Override
	public ScheduleResponse scheduleTask(ZonedDateTime dateTime) throws SchedulerException {
		JobDetail jobDetail=helper.jobDetail();
		Trigger trigger=helper.buildTrigger(jobDetail, dateTime);
		
		schedular.scheduleJob(jobDetail, trigger);
		
		ScheduleResponse response=new ScheduleResponse("Scheduled", true, jobDetail.getKey().getName(), jobDetail.getKey().getGroup());
		return response;
	}
	

}
