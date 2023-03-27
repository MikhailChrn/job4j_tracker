package ru.job4j.pojo;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class College {
    public static void main(String[] args) throws ParseException {
        Student student1 = new Student();
        student1.setFullName("Miriam Thomas");
        student1.setGroupNumber("B2");
        student1.setDateOfAdmission(LocalDateTime.of(2023, 9, 1, 0, 0));
        System.out.print(student1.getGroupNumber() + " ");
        System.out.print(student1.getFullName() + " ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy");
        System.out.println(student1.getDateOfAdmission().format(formatter));
    }
}
