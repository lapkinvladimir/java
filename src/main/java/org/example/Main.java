package org.example;

import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PublicationDate date1 = new PublicationDate(1, 1, 2000);
        PublicationDate date2 = new PublicationDate(15, 5, 1999);
        PublicationDate date3 = new PublicationDate(10, 10, 2010);

        Book book1 = new Book("Harry Potter", "J.K. Rowling", date1, "Fantasy", Arrays.asList("magic", "wizard"));
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", date2, "Adventure", Arrays.asList("dragon", "journey"));

        Book book3 = new Book("Clean Code", "Robert C. Martin", date3, Arrays.asList("programming", "software"));

        Book.save("books.dat");

        List<Book> books = Book.load("books.dat");

        System.out.println("All books in the file books.dat:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
