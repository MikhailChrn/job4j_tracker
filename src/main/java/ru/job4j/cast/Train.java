package ru.job4j.cast;

public class Train implements Vehicle {
    private String name;
    String str = System.lineSeparator();

    public Train(String name) {
        this.name = name;
    }

    @Override
    public void move() {
        System.out.printf("%s поезд выходит." + str, name);
    }

    @Override
    public void stop() {
        System.out.printf("%s поезд останавливается." + str, name);
    }
}
