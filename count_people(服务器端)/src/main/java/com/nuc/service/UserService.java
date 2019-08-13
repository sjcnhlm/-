package com.nuc.service;

import java.util.List;

import com.nuc.dto.User;

public interface UserService {

	public List<User> getAllUser();
	public Integer Login(User user);

	public void register(User user);
	public void updateByName(User user);

	public User getUserByName(String name);
}
