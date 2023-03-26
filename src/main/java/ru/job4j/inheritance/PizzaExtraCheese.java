package ru.job4j.inheritance;

public class PizzaExtraCheese extends Pizza {
    @Override
    public String name() {
        String totalName = super.name() + " using extra cheese";
        return totalName;
    }
}
