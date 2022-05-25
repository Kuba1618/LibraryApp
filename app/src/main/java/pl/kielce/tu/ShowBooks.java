package pl.kielce.tu;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import pl.kielce.tu.library.Book;

import java.util.ArrayList;

public class ShowBooks extends AppCompatActivity  {

    private static final String TAG = "ShowBooks";
    RecyclerView recyclerView;
    DatabaseReference ref;
    AdapterAllOfBooks adapterAllOfBooks;
    ArrayList<Book> listOfBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("All Of Books");

        recyclerView = findViewById(R.id.listOfBookTitlesRv);
        ref = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Books");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listOfBooks = new ArrayList<>();
        adapterAllOfBooks = new AdapterAllOfBooks(this,listOfBooks);
        recyclerView.setAdapter(adapterAllOfBooks);

        ref.addValueEventListener(new ValueEventListener() {
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

    }
}