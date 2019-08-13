package com.nuc.thread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nuc.controller.SensorController;
import com.nuc.port.Control;
import com.nuc.port.InsertInfo;
import com.nuc.port.SensorPortUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class PortMain implements ServletContextListener {
	
	 public static SensorPortUtils serialPort;
	 
	    private static String s;

	    
	    public static void open(){  
	    	getSp();
	    	System.out.println(getSp());
	    	
	        serialPort.openSerialPort();
	        System.out.println("打开端口");
	    }
	    public static SensorPortUtils getSp(){
	        serialPort = SensorPortUtils.getIsSerialPort();
	        return serialPort;
	    }
	    public static String setS(String s1){
	        return s= s1;
	    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		
		System.out.println("打开端口");
        
    	open();
    	
    	
        SensorController.setSerialPort(serialPort);
        
        Control.setSerialPort(serialPort);
        
        String a=setS("00");
        
        new Thread(new InsertInfo()).start();
        new Thread(new Control(a)).start();
		
	}
	


}
