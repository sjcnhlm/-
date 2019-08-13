package com.nuc.controller;

import javax.annotation.Resource;

import com.nuc.service.SensorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/actator")//∑√Œ µƒµÿ÷∑ 
public class ActuatorController {

	@Resource
	private SensorService sensorService;
	
	@ResponseBody
		@RequestMapping("/controlLight")
	public void controlLight(@RequestParam int state){
		String strState = null;
		if(state < 10){
			strState = "0" + Integer.toString(state);
		}else{
			switch(state){
				case 10:
					strState = "0A";
					break;
				case 11:
					strState = "0B";
					break;
				case 12:
					strState = "0C";
					break;
				case 13:
					strState = "0D";
					break;
				case 14:
					strState = "0E";
					break;
				case 15:
					strState = "0F";
					break;
			}
			
		}
		System.out.println(strState);
		sensorService.controlLight(strState);
	}
}
