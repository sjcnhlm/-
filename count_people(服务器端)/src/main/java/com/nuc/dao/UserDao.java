package com.nuc.dao;

import java.util.List;
import java.util.Map;

import com.nuc.dto.User;


public interface UserDao {
	public List<User> getAllUser();
	
	public User getUserByName(String name);

	public void register(User user);

	public void updateByName(User user);
}
