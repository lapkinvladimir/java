package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PublicationDate date1 = new PublicationDate(1, 1, 2000);
        PublicationDate date2 = new PublicationDate(15, 5, 1999);


        Book.save("books.dat");

        List<Book> books = Book.load("books.dat");
        for (Book book : books) {
            System.out.println(book);
        }


    }
}