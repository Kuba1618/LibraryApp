package pl.kielce.tu.library;

import java.util.HashMap;

public class Student {
    long studentId;
    String name;
    String surname;

    public Student(String name, String surname) {
        studentId = System.currentTimeMillis();
        this.name = name;
        this.surname = surname;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
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
        hashMap.put("studentId", "" + studentId);
        hashMap.put("name","" + name);
        hashMap.put("surname","" + surname);
        return hashMap;
    }
}
