package com.example.leaderboard.retrofitApi;

import android.os.Message;

public class DataSetModel {

     private String live;
     private String name;
     private String massage;

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    @Override
    public String toString() {
        return "DataSetModel{" +
                "live='" + live + '\'' +
                ", name='" + name + '\'' +
                ", massage='" + massage + '\'' +
                '}';
    }
}
