package ru.job4j.ex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactTest {
    @Test
    void whenException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Fact().calc(-1);
                });
        assertThat(exception.getMessage()).isEqualTo("Аргумент меньше 0.");
    }

    @Test
    void whenFactOfFiveResultOneHundredTwentySuccess() {
        int input = 5;
        int expected = 120;
        Fact fact = new Fact();
        int result = fact.calc(5);
        assertThat(result).isEqualTo(expected);
    }

}