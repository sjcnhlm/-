package com.nuc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nuc.dto.User;
import com.nuc.dao.UserDao;
import com.nuc.service.UserService;


@Service("user")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userdao;
	
	@Override
	public List<User> getAllUser() {
		
		return userdao.getAllUser();

	}

	@Override
	public Integer Login(User user) {
		User userLocal = userdao.getUserByName(user.getUsername());

		if (userLocal != null) {
			if(userLocal.getPassword().equals(user.getPassword()) ){
				return 1;
			}else{
				return 2;
			}

		}
		else{
			return 0;
		}



	}


	//注册功能实现
	public void register(User user)
	{
		userdao.register(user);
	}


	public void updateByName(User user)
	{
		userdao.updateByName(user);
	}


	public User getUserByName(String name)
	{
		return userdao.getUserByName(name);
	}
}
