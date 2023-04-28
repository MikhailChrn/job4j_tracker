package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JobComparatorSingleTest {

    @Test
    public void jobAscByNameSingleTest() {
        List<Job> result = Arrays.asList(
                new Job("Fix B-bug", 0),
                new Job("Fix A-bug", 0),
                new Job("Fix D-bug", 0),
                new Job("Fix C-bug", 0)
        );
        result.sort(new JobAscByName());
        List<Job> expected = Arrays.asList(
                new Job("Fix A-bug", 0),
                new Job("Fix B-bug", 0),
                new Job("Fix C-bug", 0),
                new Job("Fix D-bug", 0)
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void jobDescByNameSingleTest() {
        List<Job> result = Arrays.asList(
                new Job("Fix B-bug", 0),
                new Job("Fix A-bug", 0),
                new Job("Fix D-bug", 0),
                new Job("Fix C-bug", 0)
        );
        result.sort(new JobDescByName());
        List<Job> expected = Arrays.asList(
                new Job("Fix D-bug", 0),
                new Job("Fix C-bug", 0),
                new Job("Fix B-bug", 0),
                new Job("Fix A-bug", 0)
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void jobAscByPrioritySingleTest() {
        List<Job> result = Arrays.asList(
                new Job("Fix X-bug", 3),
                new Job("Fix X-bug", 2),
                new Job("Fix X-bug", 0),
                new Job("Fix X-bug", 1)
        );
        result.sort(new JobAscByPriority());
        List<Job> expected = Arrays.asList(
                new Job("Fix X-bug", 0),
                new Job("Fix X-bug", 1),
                new Job("Fix X-bug", 2),
                new Job("Fix X-bug", 3)
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void jobDescByPrioritySingleTest() {
        List<Job> result = Arrays.asList(
                new Job("Fix X-bug", 3),
                new Job("Fix X-bug", 2),
                new Job("Fix X-bug", 0),
                new Job("Fix X-bug", 1)
        );
        result.sort(new JobDescByPriority());
        List<Job> expected = Arrays.asList(
                new Job("Fix X-bug", 3),
                new Job("Fix X-bug", 2),
                new Job("Fix X-bug", 1),
                new Job("Fix X-bug", 0)
        );
        assertThat(result).isEqualTo(expected);
    }
}
