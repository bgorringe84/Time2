package com.example.time2;

public class GoalModel {
    private String title;
    private String cost;

    private GoalModel() {}

    private GoalModel(String title, String cost) {
        this.title = title;
        this.cost = cost;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
