package ru.job4j.tracker;

import java.time.format.DateTimeFormatter;

public class StartUI {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        Item item1 = new Item(1, "Obj1");
        System.out.println(item1.getCreated().format(formatter));
        Item item2 = new Item(2, "Obj2");
        System.out.println(item2);
    }
}
