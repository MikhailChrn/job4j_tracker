package ru.job4j.cast;

public class Vehicles {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[] {
                new Plane("Первый"),
                new Plane("Второй"),
                new Train("Первый"),
                new Train("Второй"),
                new Bus("Первый"),
                new Bus("Второй")
        };
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
        }
        for (Vehicle vehicle : vehicles) {
            vehicle.stop();
        }
    }
}
