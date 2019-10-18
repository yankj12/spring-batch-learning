package com.yan.spring.batch.learning.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yan.spring.batch.learning.schema.User;

@Mapper
public interface UserMapper {

	void insertUser(User user);
	
	List<User> findUsersByFlag(String flag);
	
}
