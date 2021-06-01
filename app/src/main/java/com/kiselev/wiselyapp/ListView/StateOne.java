package com.kiselev.wiselyapp.ListView;

import android.graphics.Color;

public class StateOne {
    private int id;
    private String dayOfWeek;
    private int spend;
    private int income;
    private int flagImage;
    private int color;

    public StateOne(int id, String dayOfWeek, int spend, int income, int flagImage) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.spend = spend;
        this.income = income;
        this.flagImage = flagImage;
        this.color = Color.WHITE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getSpend() {
        return spend;
    }

    public void setSpend(int spend) {
        this.spend = spend;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(int flagImage) {
        this.flagImage = flagImage;
    }

    public int getColor() {
        return color;
    }

    public void changeColor(){
        if (color == Color.WHITE){
            color = Color.parseColor("#8abf8c");
        }else{
            color = Color.WHITE;
        }
    }
}
