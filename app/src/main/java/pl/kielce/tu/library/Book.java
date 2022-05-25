package pl.kielce.tu.library;

import java.util.HashMap;

public class Book {
    String bookId;
    String title;
    String author;

    public Book() {}

    public Book(String title, String author) {
        this.bookId = "" + System.currentTimeMillis();
        this.title = title;
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public HashMap<String, String> toHashMap(){
        final HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("bookId", "" + bookId);
        hashMap.put("title","" + title);
        hashMap.put("author","" + author);
        return hashMap;
    }
}
