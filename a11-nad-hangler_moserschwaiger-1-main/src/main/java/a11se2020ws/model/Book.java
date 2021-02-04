package a11se2020ws.model;

/**
 * Represents a book object with the give attributes
 */
public class Book {
    private final String title;
    private final String author;
    private final int year;
    private final long isbn;

    public Book(String title, String author, int year, long isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public long getIsbn() {
        return isbn;
    }

    public String toString() {
        return "Title: " + this.title + "\n   Author: " + this.author + "\n   Year: " + this.year + "\n   ISBN: " + this.isbn;
    }
}
