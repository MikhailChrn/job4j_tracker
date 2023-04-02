package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StartUITest {
    @Test
    public void whenAddItem() {
        String[] answers = {"Fix PC"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI.createItem(input, tracker);
        Item created = tracker.findAll()[0];
        Item expected = new Item("Fix PC");
        assertThat(created.getName()).isEqualTo(expected.getName());
    }

    @Test
    public void whenCreateItem() {
        String[] answers = new String[]
                {"Сходить к Марии", "Сходить к Анастасии", "Сходить к Елене"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        for (String str : answers) {
            StartUI.createItem(input, tracker);
        }
        Item created = tracker.findById(2);
        Item expected = new Item("Сходить к Анастасии");
        assertThat(created.getName()).isEqualTo(expected.getName());
    }
}