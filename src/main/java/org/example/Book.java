package org.example;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book implements Serializable {

    private static List<Book> extent = new ArrayList<>();
    private static int totalBooksCount = 0;

    private String title;
    private String author;
    private PublicationDate publicationDate;
    private String genre;
    private List<String> tags;

    public Book(String title, String author, PublicationDate publicationDate, String genre, List<String> tags) {
        for (Object param : List.of(title, author, publicationDate, tags)) {
            if (param == null) {
                throw new IllegalArgumentException("Constructor parameters cannot be null.");
            }
        }

        if (tags.isEmpty()) {
            throw new IllegalArgumentException("Tags cannot be empty.");
        }

        for (String tag : tags) {
            if (tag == null) {
                throw new IllegalArgumentException("Tags cannot contain null values.");
            }
        }

        if (genre.isBlank()) {
            throw new IllegalArgumentException("Genre cannot be blank.");
        }

        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.genre = genre;
        this.tags = new ArrayList<>(tags);
        totalBooksCount++;
        extent.add(this);
    }

    public Book(String title, String author, PublicationDate publicationDate, List<String> tags) {
        for (Object param : List.of(title, author, publicationDate, tags)) {
            if (param == null) {
                throw new IllegalArgumentException("Constructor parameters cannot be null.");
            }
        }

        if (tags.isEmpty()) {
            throw new IllegalArgumentException("Tags cannot be empty.");
        }

        for (String tag : tags) {
            if (tag == null) {
                throw new IllegalArgumentException("Tags cannot contain null values.");
            }
        }

        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.genre = null;
        this.tags = new ArrayList<>(tags);
        totalBooksCount++;
        extent.add(this);
    }

    public static List<Book> getBooks() {
        return Collections.unmodifiableList(extent);
    }

    public static void save(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fileOut)) {

            oos.writeObject(extent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> load(String filename) {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fileIn)) {

            extent = (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return extent;
    }

    public static int getTotalBooksCount() {
        return totalBooksCount;
    }

    public static void setTotalBooksCount(int totalBooksCount) {
        if (totalBooksCount < 0) {
            throw new IllegalArgumentException("Total books count cannot be negative.");
        }
        Book.totalBooksCount = totalBooksCount;
    }

    public static List<Book> getBooksByGenre(String genre) {
        List<Book> booksByGenre = new ArrayList<>();
        for (Book book : extent) {
            if (book.getGenre() != null && book.getGenre().equals(genre)) {
                booksByGenre.add(book);
            }
        }
        return booksByGenre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
        this.author = author;
    }

    public PublicationDate getPublicationDate() {
        return publicationDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Genre cannot be blank.");
        }
        this.genre = genre;
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public void addTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            throw new IllegalArgumentException("Tag cannot be null or empty.");
        }
        tags.add(tag);
    }

    public void removeTag(String tag) {
        if (tags.size() == 1) {
            throw new IllegalArgumentException("Cannot remove the last tag.");
        }
        tags.remove(tag);
    }

    public int getBookAge() {
        LocalDate currentDate = LocalDate.now();
        LocalDate publicationLocalDate = LocalDate.of(
                publicationDate.getYear(),
                publicationDate.getMonth(),
                publicationDate.getDay()
        );
        return currentDate.getYear() - publicationLocalDate.getYear();
    }

    public String getBookInfo() {
        return "Title: " + title;
    }

    public String getBookInfo(boolean includeDetails) {
        if (includeDetails) {
            return "Title: " + title + ", Author: " + author + ", Publication Date: " + publicationDate + ", Genre: " + genre;
        } else {
            return "Title: " + title;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate=" + publicationDate +
                (genre != null ? ", genre='" + genre + '\'' : "") +
                ", tags=" + tags +
                '}';
    }
}
