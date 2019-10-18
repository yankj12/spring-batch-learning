package com.yan.spring.batch.learning.writer;

import java.util.List;
import java.util.Random;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yan.spring.batch.learning.mapper.ScoreMapper;
import com.yan.spring.batch.learning.mapper.UserMapper;
import com.yan.spring.batch.learning.schema.User;

@Service
public class MyWriterAdapterService implements ItemWriter<User> {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ScoreMapper scoreMapper;
	
	@Override
	public void write(List<? extends User> items) throws Exception {
		
		for(User user:items) {
			//TODO 更新每一个用户的分数
			Long id = user.getId();

			// 这里随机一个整数作为分数
			Random random = new Random();
			int score = random.nextInt(101);
			
			System.out.println("更新"+ id + "用户的分数为" + score);
			// 将分数更新到t_score表
			// 先根据user_id查一下score表是否存在记录，不存在新增，存在则更新
			
			
		}
	}

}
