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

    @Test
    public void whenEditItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                String.valueOf(item.getId()), /* id сохраненной заявки в объект tracker. */
                "edited item"
        };
        StartUI.editItem(new StubInput(answers), tracker);
        Item edited = tracker.findById(item.getId());
        assertThat(edited.getName()).isEqualTo("edited item");
    }

    @Test
    public void whenDeletedItem() {
        String[] addItemsAnswers = new String[]
                {"Сходить к Марии", "Сходить к Анастасии", "Сходить к Елене"};
        Input input = new StubInput(addItemsAnswers);
        Tracker tracker = new Tracker();
        for (String str : addItemsAnswers) {
            StartUI.createItem(input, tracker);
        }
        String[] deleteItemAnswers = {"1"};
        StartUI.deleteItem(new StubInput(deleteItemAnswers), tracker);
        Item[] result = tracker.findByName("Сходить к Марии");
        assertThat(result.length).isEqualTo(0);
    }
}