package com.nuc.port;

public class Control implements Runnable {
    /*实现Runnable接口,这里用来实现给执行器传递信息*/



    private  String sc;
    public static SensorPortUtils serialPort;

    public Control(){}
    public Control(String sc){
        this.sc=sc;
    }
    public static void setSerialPort(SensorPortUtils serialPort1){
        serialPort=serialPort1;
    }
    public static String getEx(){
        return serialPort.sensor.get("EXECUTEB").replace(" ","");
    }
    public  void send(String i_c){
        serialPort.sendToPort(getEx(),i_c);
    }

    public String getSc() {
        return sc;
    }
    public void setSc(String a) {
        sc = a;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            send(getSc());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
