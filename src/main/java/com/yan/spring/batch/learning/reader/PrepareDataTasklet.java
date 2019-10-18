package com.yan.spring.batch.learning.reader;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yan.spring.batch.learning.mapper.UserMapper;
import com.yan.spring.batch.learning.schema.User;

@Service
public class PrepareDataTasklet implements Tasklet {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MyAdapterService myAdapterService;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		List<User> users = userMapper.findUsersByFlag("0");
		myAdapterService.getUserList_center().addAll(users);
		
		return RepeatStatus.FINISHED;
	}

}
