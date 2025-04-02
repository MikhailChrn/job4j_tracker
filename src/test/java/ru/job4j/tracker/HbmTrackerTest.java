package ru.job4j.tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HbmTrackerTest {

    public static Store store;

    @BeforeAll
    public static void initTracker() {
        store = new HbmTracker();
        store.findAll().forEach(
                item -> store.delete(item.getId())
        );
    }

    @AfterEach
    public void clearItems() {
        store.findAll().forEach(
                item -> store.delete(item.getId())
        );
    }

    @Test
    public void whenSaveThenGetSame() {
        Item item = new Item("test");
        store.add(item);
        Item result = store.findById(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenSaveSeveralThenGetAll() {
        Item item1 = new Item("name1");
        Item item2 = new Item("name2");
        Item item3 = new Item("name3");

        List.of(item1, item2, item3)
                .forEach(item -> store.add(item));

        Collection<Item> expected = List.of(item3, item2, item1);

        Collection<Item> storeResponse = store.findAll();

        assertTrue(expected.size() == storeResponse.size());
        assertTrue(expected.containsAll(storeResponse));
    }

    @Test
    public void whenDeleteThenNothingFoundAndGetRepositoryException() {
        Item item = new Item("test");

        store.add(item);

        assertThat(store.findById(item.getId())).isEqualTo(item);

        store.delete(item.getId());

        assertThat(store.findById(item.getId())).isEqualTo(null);

    }
}