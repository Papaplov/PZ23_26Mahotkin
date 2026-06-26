package com.example.pz23_26mahotkin;

public class TestItem {
    public String id;
    public String name;
    public String time;
    public int price;
    public int patientCount = 1;

    public TestItem(String id, String name, String time, int price) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.price = price;
    }
}