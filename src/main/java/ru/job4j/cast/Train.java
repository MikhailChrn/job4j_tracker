package ru.job4j.cast;

public class Train implements Vehicle {
    private String name;
    private static final String STR = System.lineSeparator();

    public Train(String name) {
        this.name = name;
    }

    @Override
    public void move() {
        System.out.printf("%s поезд выходит." + STR, name);
    }

    @Override
    public void stop() {
        System.out.printf("%s поезд останавливается." + STR, name);
    }
}
