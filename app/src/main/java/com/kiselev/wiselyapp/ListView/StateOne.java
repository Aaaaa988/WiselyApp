package com.kiselev.wiselyapp.ListView;

public class StateOne {
    private int id;
    private String dayOfWeek;
    private int spend;
    private int income;
    private int flagImage;

    public StateOne(int id, String dayOfWeek, int spend, int income, int flagImage) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.spend = spend;
        this.income = income;
        this.flagImage = flagImage;
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
}
