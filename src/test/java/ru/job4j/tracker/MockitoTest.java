package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.DeleteItem;
import ru.job4j.tracker.action.EditItem;
import ru.job4j.tracker.action.FindItemById;
import ru.job4j.tracker.action.FindItemsByName;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenItemWasFindByIdActiondSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("Finded item"));
        UserAction findItemById = new FindItemById(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(item.getId());

        findItemById.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + item.toString() + ln
        );
    }

    @Test
    public void whenItemWasFindByNameSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new SqlTracker(connection);
        Item item1 = tracker.add(new Item("Finded item #1"));
        Item item2 = tracker.add(new Item("Finded item #2"));
        UserAction findItemsByName = new FindItemsByName(output);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(item2.getName());

        findItemsByName.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + item2.toString() + ln
        );
    }

    @Test
    public void whenItemWasDelitedSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("Deleted item"));
        UserAction findItemById = new FindItemById(output);
        UserAction deleteItem = new DeleteItem(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(item.getId());

        findItemById.execute(input, tracker);
        deleteItem.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + item.toString() + ln
                        + "=== Delete item ===" + ln
                        + "Заявка удалена успешно." + ln
        );
    }

    @Test
    public void whenItemWasReplacedSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        UserAction editItem = new EditItem(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(item.getId());
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        editItem.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Edit item ===" + ln
                        + "Заявка изменена успешно." + ln
        );
    }
}
