package ru.job4j.oop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class PointTest {
    @Test
    void distanceFromPoint0Сomma0ToPoint0Сomma2() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        double result = a.distance(b);
        double expected = 2.0;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void distanceFromPointMinus5Сomma0ToPoint0Сomma0() {
        Point a = new Point(-5, 0);
        Point b = new Point(0, 0);
        double result = a.distance(b);
        double expected = 5.0;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void distanceFromPointMinus2СommaMinus2ToPoint2Сomma2() {
        Point a = new Point(-2, -2);
        Point b = new Point(2, 2);
        double result = a.distance(b);
        double expected = Math.sqrt(32);
        assertThat(result).isEqualTo(expected);
    }
}
