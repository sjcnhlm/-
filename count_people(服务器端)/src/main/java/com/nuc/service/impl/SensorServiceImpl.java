package com.nuc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.nuc.controller.SensorController;
import com.nuc.dao.SensorDao;
import com.nuc.dto.Sensor;
import com.nuc.service.SensorService;
import org.springframework.stereotype.Service;




@Service
public class SensorServiceImpl implements SensorService {
	@Resource
	private SensorDao sensordao;

	@Override
	public void insertSensorInfo(Sensor sensor) {
		
		sensordao.insertSensorInfo(sensor);
		
	}

	@Override
	public void insertInfo(Sensor sensor) {
		// TODO Auto-generated method stub
		sensordao.insertSensorInfo(sensor);
	}

	@Override
	public List<Sensor> getLatestSensor() {
		// TODO Auto-generated method stub
		return sensordao.getLatestSensor();
	}

	@Override
	public Sensor getLastSensorInfo()
	{
		return sensordao.getLastSensorInfo();
	}


	@Override
	public void controlLight(String state) {
		// TODO Auto-generated method stub   
		
		SensorController.EXECUTEB1 = SensorController.getExecuteb().replace(" ", "");//去掉空格
		SensorController.EXECUTEB2 = SensorController.serialPort.sensor.get("EXECUTEB").replace(" ", "");
      	
    	System.out.println("----修改之前的状态-----"+SensorController.EXECUTEB1);
    	
        if (!SensorController.EXECUTEB1.equals(state)) {
        	System.out.println("修改状态");
        	SensorController.serialPort.sendToPort(SensorController.EXECUTEB2,state);
        } 
        SensorController.EXECUTEB1 = SensorController.getExecuteb().replace(" ", "");
        System.out.println("--修改之后的状态------"+SensorController.EXECUTEB1);
	}
	
}
