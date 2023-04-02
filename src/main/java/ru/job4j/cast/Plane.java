package ru.job4j.cast;

public class Plane implements Vehicle {
    private String name;
    private final String str = System.lineSeparator();

    public Plane(String name) {
        this.name = name;
    }

    @Override
    public void move() {
        System.out.printf("%s самолёт вылетает." + str, name);
    }

    @Override
    public void stop() {
        System.out.printf("%s самолёт приземляется." + str, name);
    }
}
