package ru.job4j.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JobSorter {
    public static void main(String[] args) {
        List<Job> jobs = Arrays.asList(
                new Job("Fix A-bug", 1),
                new Job("Fix B-bug", 4),
                new Job("Fix C-bug", 2),
                new Job("Fix D-bug", 0)
        );
        Collections.sort(jobs, new JobAscByPriority());
        System.out.println(jobs);
    }
}
