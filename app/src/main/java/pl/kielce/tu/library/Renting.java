package pl.kielce.tu.library;

import java.util.HashMap;

public class Renting {
    long rentingId;
    long bookId;
    long studentId;

    public Renting(long bookId, long studentId) {
        this.rentingId = System.currentTimeMillis();
        this.bookId = bookId;
        this.studentId = studentId;
    }

    public long getRentingId() {
        return rentingId;
    }

    public void setRentingId(long rentingId) {
        this.rentingId = rentingId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
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
