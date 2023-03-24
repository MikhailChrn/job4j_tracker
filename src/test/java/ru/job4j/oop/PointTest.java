package ru.job4j.oop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class PointTest {
    @Test
    void distanceFromPointZeroCommaZeroToPointZeroCommaTwo() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        double result = a.distance(b);
        double expected = 2.0;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void distanceFromPointMinusFiveCommaZeroToPointZeroCommaZero() {
        Point a = new Point(-5, 0);
        Point b = new Point(0, 0);
        double result = a.distance(b);
        double expected = 5.0;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void distanceFromPointMinusTwoCommaMinusTwoToPointTwoCommaTwo() {
        Point a = new Point(-2, -2);
        Point b = new Point(2, 2);
        double result = a.distance(b);
        double expected = Math.sqrt(32);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void distanceFromPointMinus1CommaMinus1CommaMinus1ToPoint1Comma1Comma1() {
        Point a = new Point(-1, -1, -1);
        Point b = new Point(1, 1, 1);
        double result = a.distance3d(b);
        double expected = Math.sqrt(12);
        assertThat(result).isEqualTo(expected);
    }
}
