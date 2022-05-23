package pl.kielce.tu.library;

import java.util.Random;

public class Book {
    long id;
    String title;
    String author;

    public Book(String title, String author) {
        this.id = System.currentTimeMillis();
        this.title = title;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
