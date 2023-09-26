package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

/**
 * Пояснения:
 *
 *  - на все тесты создаем одно подключение к БД, чтобы ускорить их,
 *     поэтому Connection делаем статическим;
 *
 *  - в методе initConnection() выполняется инициализация подключения.
 *     Данный метод обозначен аннотацией @BeforeAll,
 *     т.е. метод выполняется один раз до начала тестов;
 *
 *  - в методе closeConnection() выполняется закрытие подключения.
 *     Данный метод обозначен аннотацией @AfterAll,
 *     т.е. метод выполняется один раз после тестов;
 *
 *  - в методе wipeTable() мы чистим таблицу items после внесенных изменений.
 *     Делается это специально, чтобы облегчить тестирование,
 *     иначе изменения, внесенные один тестом, будут видны другому.
 *     Данный метод обозначен аннотацией @AfterEach,
 *     т.е. метод выполняется после каждого теста;
 *
 *  - метод whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() представляет собой уже тест.
 *     Обратите внимание, что после вставки мы находим item по сгенерированному БД ключу.
 *
 * Важно! В тесте сравниваются сами заявки,
 *     а это значит что должны быть переопределены equals() & hashCode().
 *
 *
*/

public class SqlTrackerTest {

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
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenTestFindById() {
        Store tracker = new SqlTracker(connection);
        Item bug = new Item("Bug");
        Item item = tracker.add(bug);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenTestFindAll() {
        Store tracker = new SqlTracker(connection);
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        Item result = tracker.findAll().get(0);
        assertThat(result.getName()).isEqualTo(first.getName());
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        Store tracker = new SqlTracker(connection);
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        tracker.add(new Item("First"));
        tracker.add(new Item("Second"));
        tracker.add(new Item("First"));
        List<Item> result = tracker.findByName(first.getName());
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void whenTestFindByNameCheckSecondItemName() {
        Store tracker = new SqlTracker(connection);
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        tracker.add(new Item("First"));
        tracker.add(new Item("Second"));
        tracker.add(new Item("First"));
        List<Item> result = tracker.findByName(second.getName());
        assertThat(result.get(1).getName()).isEqualTo(second.getName());
    }

    @Test
    public void whenReplaceItemIsSuccessful() {
        Store tracker = new SqlTracker(connection);
        Item item = new Item("Bug");
        tracker.add(item);
        int id = item.getId();
        Item updateItem = new Item("Bug with description");
        tracker.replace(id, updateItem);
        assertThat(tracker.findById(id).getName()).isEqualTo("Bug with description");
    }

    @Test
    public void whenReplaceItemIsNotSuccessful() {
        Store tracker = new SqlTracker(connection);
        Item item = new Item("Bug");
        tracker.add(item);
        Item updateItem = new Item("Bug with description");
        boolean result = tracker.replace(1000, updateItem);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo("Bug");
        assertThat(result).isFalse();
    }

    @Test
    public void whenDeleteItemIsSuccessful() {
        Store tracker = new SqlTracker(connection);
        Item item = new Item("Bug");
        tracker.add(item);
        int id = item.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id)).isNull();
    }

    @Test
    public void whenDeleteItemWasSuccessful() {
        Store tracker = new SqlTracker(connection);
        Item item = new Item("Bug");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo("Bug");
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId()) != null).isFalse();
    }

    @Test
    public void whenThereAreAnyRecordsDeleteOneItem() {
        Store tracker = new SqlTracker(connection);
        tracker.add(new Item("First"));
        Item second = new Item("Second");
        tracker.add(second);
        tracker.add(new Item("Third"));
        tracker.add(new Item("Fourth"));
        tracker.delete(second.getId());
        assertThat(tracker.findAll().size()).isEqualTo(3);
    }
}
