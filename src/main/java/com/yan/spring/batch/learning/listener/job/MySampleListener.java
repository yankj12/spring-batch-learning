package com.yan.spring.batch.learning.listener.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MySampleListener 
 * @Description: job生命周期监控器 
 * @author CC  
 * @date 2019年10月9日 下午5:54:14 
 * @version V1.0 
 */
@Component
public class MySampleListener implements JobExecutionListener {

	private static final Logger log = LoggerFactory.getLogger("SpringBatchDemo");
	 
	@Override
	public void afterJob(JobExecution jobExecution) {
		if( jobExecution.getStatus() == BatchStatus.COMPLETED ){
			log.info("Job execute status == " + BatchStatus.COMPLETED);
	    }
	    else if(jobExecution.getStatus() == BatchStatus.FAILED){
	    	log.info("Job execute status == " + BatchStatus.FAILED);
	    }
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("job start ......");
	}
}
