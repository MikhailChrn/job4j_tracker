package ru.job4j.cast;

public class Bus implements Vehicle {
    private String name;
    private static final String STR = System.lineSeparator();

    public Bus(String name) {
        this.name = name;
    }

    @Override
    public void move() {
        System.out.printf("%s автобус выезжает." + STR, name);
    }

    @Override
    public void stop() {
        System.out.printf("%s автобус останавливается." + STR, name);
    }
}
