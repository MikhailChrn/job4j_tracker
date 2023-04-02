package ru.job4j.cast;

public class Bus implements Vehicle {
    private String name;
    private final String str = System.lineSeparator();

    public Bus(String name) {
        this.name = name;
    }

    @Override
    public void move() {
        System.out.printf("%s автобус выезжает." + str, name);
    }

    @Override
    public void stop() {
        System.out.printf("%s автобус останавливается." + str, name);
    }
}
