package com.nuc.controller;

import java.util.List;

import javax.annotation.Resource;

import com.nuc.dto.Sensor;
import com.nuc.service.SensorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/sensor")
public class SensorInfoController {
	
	@Resource
	private SensorService sensorservice;
	
	
	
	 @ResponseBody
	 @RequestMapping("/sensorInfo")
	  public List<Sensor> getLatestSensor(){
	    	
		List<Sensor> sensorList = sensorservice.getLatestSensor();
		return sensorList;
	}


	@ResponseBody
	@RequestMapping("/lastsensorInfo")
	public Sensor getLastSensorInfo()
	{
		return sensorservice.getLastSensorInfo();

	}


}
