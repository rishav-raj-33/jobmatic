package Jobmatic.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse {
	
	private String msg;
    private boolean success;
    private String jobId;
    private String jobGroup;
	
}
