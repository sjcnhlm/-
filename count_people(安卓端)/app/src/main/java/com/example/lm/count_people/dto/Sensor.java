package com.example.lm.count_people.dto;

public class Sensor {

    private int id;
    private float humi;
    private float temp;
    private float illu;

    public Sensor( float humi, float temp, float illu) {
        this.humi = humi;
        this.temp = temp;
        this.illu = illu;

    }

    public Sensor(int id, float humi, float temp, float illu) {
        this.id = id;
        this.humi = humi;
        this.temp = temp;
        this.illu = illu;
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
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getHumi() {
        return humi;
    }

    public void setHumi(float humi) {
        this.humi = humi;
    }

    public float getIllu() {
        return illu;
    }

    public void setIllu(float illu) {
        this.illu = illu;
    }



    @Override
    public String toString() {
        return "Sensor [id=" + id + ",  temperature=" + temp
                + ", humidity=" + humi + ", illumination=" + illu + "]";
    }


}


