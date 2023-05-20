package ru.job4j.map;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.*;

public class College {
    private final Map<Student, Set<Subject>> students;

    public College(Map<Student, Set<Subject>> students) {
        this.students = students;
    }

    public Optional<Student> findByAccount(String account) {
        return ofNullable(students.keySet()
                .stream()
                .filter(student -> student.account().equals(account))
                .findFirst()
                .orElse(null));
    }

    public Optional<Subject> findBySubjectName(String account, String name) {
        Optional<Student> student = findByAccount(account);
        if (student.isEmpty()) {
            return Optional.empty();
        }
        return ofNullable(students.get(student.get())
                .stream()
                .filter(subject -> subject.name().equals(name))
                .findFirst()
                .orElse(null));
    }

    public static void main(String[] args) {
        Map<Student, Set<Subject>> students = Map.of(new Student("Student", "000001", "201-18-15"),
                Set.of(
                        new Subject("Math", 70),
                        new Subject("English", 85)
                )
        );
        College college = new College(students);
        Student student = college.findByAccount("000001").get();
        System.out.println("Найденный студент: " + student);
        Subject english = college.findBySubjectName("000001", "English").get();
        System.out.println("Оценка по найденному предмету: " + english.score());
    }

}
