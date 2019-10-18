package com.yan.spring.batch.learning.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yan.spring.batch.learning.schema.User;

@Service
public class MyAdapterService {

	/*
	 * 数据：之前写法只能获取一次数据，已被抛弃！
	 * 
	 * step间传递数据：
	 *   1.可以通过step_execution 或者 job_execution来在不同step中传递数据.但是如果数据量大的话,
	 * 这将不是一种好的方式.因为spring batch默认会通过job repository将 setp_execution和job_execution进行持久化。 
	 * 如果把要传递的数据放在setp_execution或者job_execution中进行传递，job repository会把数据持久化到数据库的日志表中，对于大量数据不适合。
	 *   2.这里采用bean配置注入方式来传递数据
	 */
	private List<User> userList_center = new ArrayList<>();

	public List<User> getUserList_center() {
		return userList_center;
	}

	public void setUserList_center(List<User> userList_center) {
		this.userList_center = userList_center;
	}

	/*
	 * 适配read功能 
	 */
	public User nextUser() {
		if(userList_center.size() > 0) {
			return userList_center.remove(0);
		} else {
			return null;
		}
	}
	
}
