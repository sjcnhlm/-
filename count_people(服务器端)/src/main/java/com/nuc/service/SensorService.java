package com.nuc.service;

import java.util.List;

import com.nuc.dto.Sensor;


public interface SensorService {
	 public void insertSensorInfo(Sensor sensor);
	 
	 
	 void insertInfo(Sensor sensor);
	 
	 public List<Sensor> getLatestSensor();
	 
	 public void controlLight(String state);

	public Sensor getLastSensorInfo();
	 
}
