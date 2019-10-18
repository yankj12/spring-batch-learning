package com.yan.spring.batch.learning.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yan.spring.batch.learning.schema.Score;

@Mapper
public interface ScoreMapper {

	void updateUserScore(Map<String, Object> map);
	
	void insertScore(Score score);
	
	Long countScoreByUserId(String userId);
	
}
