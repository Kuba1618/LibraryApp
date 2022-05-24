package pl.kielce.tu.library;

import java.util.HashMap;

public class Student {
    long studentCardId;
    String name;
    String surname;

    public Student(String name, String surname) {
        studentCardId = System.currentTimeMillis();
        this.name = name;
        this.surname = surname;
    }

    public long getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(long studentCardId) {
        this.studentCardId = studentCardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public HashMap<String, String> toHashMap(){
        final HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("studentCardId", "" + studentCardId);
        hashMap.put("name","" + name);
        hashMap.put("surname","" + surname);
        return hashMap;
    }
}
