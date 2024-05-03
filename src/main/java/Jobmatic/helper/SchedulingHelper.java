package Jobmatic.helper;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;




@Component
public class SchedulingHelper {	
	
	public JobDetail jobDetail() {
		return JobBuilder.newJob(SendEmail.class).withIdentity(UUID.randomUUID().toString(),"Email Job")
				.withDescription("Send Email")
				.storeDurably().build();	
	}
	
   public Trigger buildTrigger(JobDetail jobDetail,ZonedDateTime startAt) {
		
		return TriggerBuilder.newTrigger().withIdentity(jobDetail.getKey().getName(),"Trigger")
				.withDescription("Send Email Trigger")
				.startAt(Date.from(startAt.toInstant()))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
				.build();
	}

}
