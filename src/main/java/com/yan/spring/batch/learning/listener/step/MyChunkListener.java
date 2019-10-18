package com.yan.spring.batch.learning.listener.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class MyChunkListener implements StepExecutionListener{

	private static final Logger log = LoggerFactory.getLogger("SpringBatchDemo");

	@Override
	public void beforeStep(StepExecution stepExecution) {
		log.info("step start ......");
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if( stepExecution.getStatus() == BatchStatus.COMPLETED ){
			log.info("Job execute status == " + BatchStatus.COMPLETED);
	    }
	    else if(stepExecution.getStatus() == BatchStatus.FAILED){
	    	log.info("Job execute status == " + BatchStatus.FAILED);
	    }
		return new ExitStatus(stepExecution.getStatus().toString());
	}

}
