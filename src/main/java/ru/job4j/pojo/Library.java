package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book[] books = new Book[] {
                new Book("The Hound of the Baskervilles", 84),
                new Book("The Old Man And The Sea"),
                new Book("White Fang", 100),
                new Book("Clean code", 462)
        };
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getNameOfBook() + " " + books[i].getNumberOfPages());
        }
        System.out.println();
        Book tmp = books[3];
        books[3] = books[0];
        books[0] = tmp;
        for (Book book : books) {
            if ("Clean code".equals(book.getNameOfBook())) {
                System.out.println(book.getNameOfBook() + " " + book.getNumberOfPages());
            }
        }
    }
}
