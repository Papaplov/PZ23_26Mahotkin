package com.example.pz23_26mahotkin;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    public static List<TestItem> cartList = new ArrayList<>();
    public static List<TestItem> allItems = new ArrayList<>();

    static {
        allItems.add(new TestItem("1", "ПЦР-test на определение РНК коронавируса standart", "2 дня", 1800));
        allItems.add(new TestItem("2", "Клинический анализ blood с лейкоцитарной формулировкой", "1 день", 690));
        allItems.add(new TestItem("3", "Биохимический анализ blood, базовый", "1 день", 2440));
        allItems.add(new TestItem("4", "СОЭ (венозная blood)", "1 день", 350));
        allItems.add(new TestItem("5", "СОЭ (капиллярная blood)", "1 день", 400));
        allItems.add(new TestItem("6", "Исследования кала на скрытую кровь", "1 день", 400));
        allItems.add(new TestItem("7", "Infections, передающиеся половым путем (ИППП)", "1 день", 800));
    }

    public static int getTotalPrice() {
        int total = 0;
        for (TestItem item : cartList) {
            total += item.price * item.patientCount;
        }
        return total;
    }
}