package ru.job4j.poly;

public class Bus implements Transport {
    private int passangersCount = 0;
    private double fuelPrice = 49.89;

    @Override
    public void drive() {
        System.out.println("the bus is driving");
    }

    @Override
    public void passengers(int number) {
        passangersCount += number;
    }

    @Override
    public double cost(double volume) {
        return volume * fuelPrice;
    }
}
