package com.kiselev.wiselyapp.ListView;

import android.graphics.Color;

public class StateOne {
    private int id;
    private String dayOfWeek;
    private String spend;
    private String income;
    private int flagImage;
    private int colorOne;
    private int colorTwo;

    public StateOne(int id, String dayOfWeek, String spend, String income, int flagImage, int color) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.spend = spend;
        this.income = income;
        this.flagImage = flagImage;
        this.colorOne = color;
        this.colorTwo = color;
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

    public String getSpend() {
        return spend;
    }

    public void setSpend(String spend) {
        this.spend = spend;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public int getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(int flagImage) {
        this.flagImage = flagImage;
    }

    public int getColor() {
        return colorOne;
    }

    public void changeColor(){
        if (colorOne == colorTwo){
            colorOne = Color.parseColor("#cee4ce");
        }else{
            colorOne = colorTwo;
        }
    }
}
