package com.nuc.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import com.nuc.dto.Sensor;
import com.nuc.port.SensorPortUtils;
import com.nuc.service.SensorService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;






public class SensorController extends Thread{

	
	
	@Resource
	private SensorService sensorservice;
	

	
	public static String EXECUTEB2 = null;
	public static String EXECUTEB1 = null;
	public float humi;
	public float temp;
	public int light;
    public static SensorPortUtils serialPort;


    //拆分数据
    
    public SensorController() {
		super();
	}
    
    
    public SensorController(String temp, String humi, String light) {

		ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		 
	
    	ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
    	this.sensorservice = (SensorService) applicationContext.getBean("sensorServiceImpl");
        String[] humis = humi.split(" ");
        int humiCal = Integer.parseInt(humis[1] + humis[0], 16);
        this.humi = humiCal * 0.01f;

        
        String[] temps = temp.split(" ");
        int tempCal = Integer.parseInt(temps[1] + temps[0], 16);
        this.temp = tempCal * 0.01f;

        String[] lights = light.split(" ");
        int lightCal = Integer.parseInt(lights[1] + lights[0], 16);
        this.light = lightCal;
    }
    
	public static void setSerialPort(SensorPortUtils serialPort1){
        serialPort=serialPort1;
    }
    public static String getTemp(){
        return serialPort.dataAll.get(serialPort.sensor.get("TEMP"));
    }
    public static String getHumi(){
        return serialPort.dataAll.get(serialPort.sensor.get("HUMI"));
    }
    public static String getIllu(){
        return serialPort.dataAll.get(serialPort.sensor.get("ILLU"));
    }
    public static String getExecuteb(){
        return serialPort.dataAll.get(serialPort.sensor.get("EXECUTEB"));
    }
    
    
    
    @Override
    public void run() {
    	SensorController sd = new SensorController(
                getTemp(),
                getHumi(),
                getIllu());
        
    	
    	
        float tempd = sd.temp;
        float humid = sd.humi;
        int illu = sd.light;
        
        System.out.println("nininini");
        
//        String request = "00";        
//        if (illu < 43000) {
//        	EXECUTEB1 = getExecuteb().replace(" ", "");//ȥ���ո�
//        	EXECUTEB2=serialPort.sensor.get("EXECUTEB").replace(" ", "");
//        	System.out.println(EXECUTEB1);
//        	
//        	
//        	
//        	System.out.println("���չ��߹ص�");
//            if (!EXECUTEB1.equals(request)) {
//                serialPort.sendToPort(EXECUTEB2,"00");
//            } 
//            else {
//            System.out.println("2345667");
//            serialPort.sendToPort(EXECUTEB2,"01");
//            }
//        }
//        else{
//        	//serialPort.sendToPort(EXECUTEB2,"00");
//        }



        //System.out.println("湿度：" + humid + "。温度：" + tempd + "。 + "光照度" + illu);
        Sensor sensor = new Sensor(tempd, humid, illu);
       
        System.out.println("=======================================");
        sensorservice.insertSensorInfo(sensor);
    }
    
    


	
	
	
	
}
