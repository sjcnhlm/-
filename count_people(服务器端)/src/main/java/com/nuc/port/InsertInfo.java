package com.nuc.port;


import com.nuc.controller.SensorController;

public class InsertInfo implements Runnable{

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                new SensorController(SensorController.getTemp(), SensorController.getHumi(), SensorController.getIllu()).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
