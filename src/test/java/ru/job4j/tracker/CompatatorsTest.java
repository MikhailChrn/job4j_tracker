package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class CompatatorsTest {
    @Test
    public void itemAscByNameComporatorsTest() {
        ArrayList<Item> items = new ArrayList<>(
                Arrays.asList(
                        new Item("де"),
                        new Item("вг"),
                        new Item("жз"),
                        new Item("аб"))
                );
        ArrayList<Item> expected = new ArrayList<>(
                Arrays.asList(
                        new Item("аб"),
                        new Item("вг"),
                        new Item("де"),
                        new Item("жз"))
                );
        Collections.sort(items, new ItemAscByName());
        assertThat(items).isEqualTo(expected);
    }

    @Test
    public void itemDescByNameComporatorsTest() {
        ArrayList<Item> items = new ArrayList<>(
                Arrays.asList(
                        new Item("де"),
                        new Item("вг"),
                        new Item("жз"),
                        new Item("аб"))
                );
        ArrayList<Item> expected = new ArrayList<>(
                Arrays.asList(
                        new Item("жз"),
                        new Item("де"),
                        new Item("вг"),
                        new Item("аб"))
                );
        Collections.sort(items, new ItemDescByName());
        assertThat(items).isEqualTo(expected);
    }
}
