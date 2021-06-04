package com.kiselev.wiselyapp.ListView;

import android.graphics.Color;

public class StateTwo {
    private int id;
    private String numberOfList;
    private String spendIncomeDay;
    private String type;
    private String comment;
    private int flagImage;
    private int color;

    public StateTwo(int id, String numberOfList, String spendIncomeDay, String type, String comment, int flagImage) {
        this.id = id;
        this.numberOfList = numberOfList;
        this.spendIncomeDay = spendIncomeDay;
        this.type = type;
        this.comment = comment;
        this.flagImage = flagImage;
        this.color = Color.WHITE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberOfList() {
        return numberOfList;
    }

    public void setNumberOfList(String numberOfList) {
        this.numberOfList = numberOfList;
    }

    public String getSpendIncomeDay() {
        return spendIncomeDay;
    }

    public void setSpendIncomeDay(String spendIncomeDay) {
        this.spendIncomeDay = spendIncomeDay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
            color = Color.parseColor("#cee4ce");
        }else{
            color = Color.WHITE;
        }
    }
}
