package com.nuc.dto;

import java.util.Date;
import java.sql.Timestamp;



public class Sensor {
	
	    private int id;
	    private float temperature;
	    private float humidity;
	    private float illumination;

	public Sensor( float temp, float humi, float illu) {
		this.temperature = temp;
		this.humidity = humi;
		this.illumination = illu;

	}

	public Sensor(int id, float temp, float humi, float illu) {
		this.id = id;
		this.temperature = temp;
		this.humidity = humi;
		this.illumination = illu;

	}

	    public Sensor() {
			super();
		}



		public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }
	    


	    public float getTemp() {
	        return temperature;
	    }

	    public void setTemp(float temp) {
	        this.temperature = temp;
	    }

	    public float getHumi() {
	        return humidity;
	    }

	    public void setHumi(float humi) {
	        this.humidity = humi;
	    }

	    public float getIllu() {
	        return illumination;
	    }

	    public void setIllu(float illu) {
	        this.illumination = illu;
	    }



		@Override
		public String toString() {
			return "Sensor [id=" + id + ",  temperature=" + temperature
					+ ", humidity=" + humidity + ", illumination=" + illumination + "]";
		}
	    
	    
	    
	}

