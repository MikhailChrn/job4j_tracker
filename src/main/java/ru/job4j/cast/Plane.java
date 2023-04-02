package ru.job4j.cast;

public class Plane implements Vehicle {
    private String name;
    private static final String STR = System.lineSeparator();

    public Plane(String name) {
        this.name = name;
    }

    @Override
    public void move() {
        System.out.printf("%s самолёт вылетает." + STR, name);
    }

    @Override
    public void stop() {
        System.out.printf("%s самолёт приземляется." + STR, name);
    }
}
