package ru.job4j.inheritance;

public class PizzaExtraCheeseExtraTomato extends PizzaExtraCheese {
    @Override
    public String name() {
        String totalName = super.name() + " with extra tomato";
        return totalName;
    }
}
