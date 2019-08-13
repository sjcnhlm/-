package com.nuc.dao;

import com.nuc.dto.Sensor;

import java.util.List;


public interface SensorDao {
	
	 public void insertSensorInfo(Sensor sensor);
	 
	 public List<Sensor> getLatestSensor();

	 public Sensor getLastSensorInfo();



}
