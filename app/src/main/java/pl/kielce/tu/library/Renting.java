package pl.kielce.tu.library;

import java.util.HashMap;

public class Renting {
    String rentingId;
    String bookId;
    String studentId;

    public Renting(String bookId, String studentId) {
        this.rentingId = "" + System.currentTimeMillis();
        this.bookId = bookId;
        this.studentId = studentId;
    }

    public String getRentingId() {
        return rentingId;
    }

    public void setRentingId(String rentingId) {
        this.rentingId = rentingId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public HashMap<String, String> toHashMap(){
        final HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("rentingId", "" + rentingId);
        hashMap.put("bookId","" + bookId);
        hashMap.put("studentId","" + studentId);
        return hashMap;
    }
}
