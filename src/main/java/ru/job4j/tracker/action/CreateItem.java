package ru.job4j.tracker.action;

import ru.job4j.tracker.*;

public class CreateItem implements UserAction {
    private final Output out;

    public CreateItem(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new Item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Create a new Item ===");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        item = tracker.add(item);
        out.println("Добавленная заявка: " + item);
        return true;
    }
}
