package com.nuc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nuc.dto.User;
import com.nuc.service.UserService;


@Controller//注入到spring—mvc.xml
@RequestMapping("/user")//访问的地址
public class UserController {
	
	
	@Resource
	private UserService userService;
	
	
	//@ModelAttribute 
	@RequestMapping("/userList")
	public String userList(HttpServletRequest request,Model model){
		List<User> uList = userService.getAllUser();

		
		model.addAttribute("uList", uList);
		return "userList";
	}


	//登录
	@ResponseBody
	@RequestMapping("/loginUser")
	public int login(@RequestBody User user){
		Integer res = userService.Login(user);
		System.out.println(user);
		System.out.println(res);
		//return "userList";
		return res;
	}

	@ResponseBody
	@RequestMapping("/getUserByName/{username}/{password}")
	public int login(@PathVariable("username") String name, @PathVariable("password") String password){
		User user = new User(name, password);
		System.out.println(user);
		return 1;
	}


	@ResponseBody
	@RequestMapping("/regist")
	public void register(@RequestBody User user)
	{
		userService.register(user);
	}

	@ResponseBody
	@RequestMapping("/forget_password")
	public void updateByName(@RequestBody User user)
	{
		userService.updateByName(user);
	}



	@ResponseBody
	@RequestMapping("/getUserByName")
	public  User getUserByName(@RequestParam("username") String name)
	{
		User user = userService.getUserByName(name);
		return user;
	}
	
}
