package com.nuc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nuc.dao.CountPeopleDao;
import com.nuc.dto.VideoCount;
import com.nuc.service.CountPeopleService;


@Service("video_count")
public class CountPeopleServcieImpl implements CountPeopleService {

	
	@Resource
	private CountPeopleDao countpeopledao;
	
	@Override
	public List<VideoCount> getAllCounts() {
		return countpeopledao.getAllCounts();
	}

}
