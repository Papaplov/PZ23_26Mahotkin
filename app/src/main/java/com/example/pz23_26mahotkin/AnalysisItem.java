package com.example.pz23_26mahotkin;

public class AnalysisItem {
    private String name;
    private String category;
    private String price;
    private String duration;

    public AnalysisItem(String name, String category, String price, String duration) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.duration = duration;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getPrice() { return price; }
    public String getDuration() { return duration; }
}