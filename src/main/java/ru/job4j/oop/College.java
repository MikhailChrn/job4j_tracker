package ru.job4j.oop;

public class College {
    public static void main(String[] args) {
        Frashman frashman = new Frashman();
        Student student = (Student) frashman;
        Object obj = (Object) student;
    }
}
