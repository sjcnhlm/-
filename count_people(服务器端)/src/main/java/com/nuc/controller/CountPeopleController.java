package com.nuc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.dto.VideoCount;
import com.nuc.service.CountPeopleService;

@Controller//注入到spring—mvc.xml
@RequestMapping("/countpeople")//访问的地址
public class CountPeopleController {

	
	@Resource
	private CountPeopleService countpeopleservice;
	
	
	@ResponseBody
	@RequestMapping("/countList")
	public List<VideoCount> counts(HttpServletRequest request,Model model){
		List<VideoCount> counts = countpeopleservice.getAllCounts();
		
//		for(VideoCount vc:counts)
//		{
//			System.out.println(vc);
//		}
		
		//model.addAttribute("counts", counts);
		return counts;
	}
}
