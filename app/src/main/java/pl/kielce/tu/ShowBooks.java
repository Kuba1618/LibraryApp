package pl.kielce.tu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import pl.kielce.tu.library.Book;
import pl.kielce.tu.library.Renting;
import pl.kielce.tu.library.Student;

import java.util.ArrayList;

public class ShowBooks extends AppCompatActivity implements AdapterAllOfBooks.OnBookListener, AdapterAllOfStudents.OnStudentListener {
    RecyclerView bookRecyclerView,studentsRecyclerView;
    DatabaseReference refBook,refStudent;
    AdapterAllOfBooks adapterAllOfBooks;
    AdapterAllOfStudents adapterAllOfStudents;
    ArrayList<Book> listOfBooks;
    ArrayList<Student> listOfStudents;
    Button menuBtn,rentBtn;

    public Renting renting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("All Of Books");

        bookRecyclerView = findViewById(R.id.listOfBooksRv);
        studentsRecyclerView = findViewById(R.id.listOfStudentsRv);
        menuBtn = findViewById(R.id.menuBtn);
        rentBtn = findViewById(R.id.rentingBtn);
        refBook = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Books");
        refStudent = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Students");

        bookRecyclerView.setHasFixedSize(true);
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentsRecyclerView.setHasFixedSize(true);
        studentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        renting = new Renting();
        listOfBooks = new ArrayList<>();
        listOfStudents = new ArrayList<>();
        adapterAllOfBooks = new AdapterAllOfBooks(this,listOfBooks,this);
        adapterAllOfStudents = new AdapterAllOfStudents(this, listOfStudents, this);
        bookRecyclerView.setAdapter(adapterAllOfBooks);
        studentsRecyclerView.setAdapter(adapterAllOfStudents);

        refBook.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Book book = dataSnapshot.getValue(Book.class);
                    listOfBooks.add(book);
                }
                adapterAllOfBooks.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        refStudent.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Student student = dataSnapshot.getValue(Student.class);
                    listOfStudents.add(student);
                }
                adapterAllOfStudents.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rentBtn.setOnClickListener(v -> saveRent());
        menuBtn.setOnClickListener(v -> goToMenu());
    }

    @Override
    public void onBookCLick(int position) {
        Toast.makeText(this, "Book: " + listOfBooks.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        renting.setBookId(listOfBooks.get(position).getBookId());
    }

    @Override
    public void onLongBookClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete this item from all resources?");
        builder.setMessage("Deletion is permanent...");

        builder.setPositiveButton("Yes", (dialog, which) -> deleteSongFromSongs(listOfBooks.get(position)))
                .setNegativeButton("No", (dialog, which) -> {  });
        AlertDialog ad = builder.create();
        ad.show();
    }

    private void deleteSongFromSongs(Book book){
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
        DatabaseReference ref = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Books").child(book.getBookId());
        ref.removeValue();
    }


    @Override
    public void onStudentClick(int position) {
        Toast.makeText(this, "Student: " + listOfStudents.get(position).getName(), Toast.LENGTH_SHORT).show();
        renting.setStudentId(listOfStudents.get(position).getStudentId());
    }

    @Override
    public void onLongStudentClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete this item from all resources?");
        builder.setMessage("Deletion is permanent...");

        builder.setPositiveButton("Yes", (dialog, which) -> deleteStudentFromStudents(listOfStudents.get(position)))
                .setNegativeButton("No", (dialog, which) -> {            });
        AlertDialog ad = builder.create();
        ad.show();
    }

    private void deleteStudentFromStudents(Student student){
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
        DatabaseReference ref = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Students").child(student.getStudentId());
        ref.removeValue();
    }

    public void saveRent(){
        renting.setRentingId( "" + System.currentTimeMillis());
        DatabaseReference ref = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Rentals");
        ref.child("" + renting.getRentingId()).setValue(renting.toHashMap())
                .addOnSuccessListener(unused -> Toast.makeText(ShowBooks.this,
                        "Rent saved successfully !", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(ShowBooks.this,"" + e.getMessage(),Toast.LENGTH_SHORT).show());
    }

    private void goToMenu() {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }
}