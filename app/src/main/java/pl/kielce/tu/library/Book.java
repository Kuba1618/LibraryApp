package pl.kielce.tu.library;

import java.util.HashMap;

public class Book {
    long copyOfBookId;
    String title;
    String author;

    public Book(String title, String author) {
        this.copyOfBookId = System.currentTimeMillis();
        this.title = title;
        this.author = author;
    }

    public long getCopyOfBookId() {
        return copyOfBookId;
    }

    public void setCopyOfBookId(int copyOfBookId) {
        this.copyOfBookId = copyOfBookId;
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
        hashMap.put("copyOfBookId", "" + copyOfBookId);
        hashMap.put("title","" + title);
        hashMap.put("author","" + author);
        return hashMap;
    }
}
