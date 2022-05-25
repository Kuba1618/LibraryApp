package pl.kielce.tu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
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

import java.util.ArrayList;

public class ShowBooks extends AppCompatActivity implements AdapterAllOfBooks.OnBookListener {
    RecyclerView recyclerView;
    DatabaseReference ref;
    AdapterAllOfBooks adapterAllOfBooks;
    ArrayList<Book> listOfBooks;
    Button menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("All Of Books");

        recyclerView = findViewById(R.id.listOfBookTitlesRv);
        menuBtn = findViewById(R.id.menuBtn);
        ref = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Books");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listOfBooks = new ArrayList<>();
        adapterAllOfBooks = new AdapterAllOfBooks(this,listOfBooks,this);
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

        menuBtn.setOnClickListener(v -> goToMenu());
    }

    private void goToMenu() {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }

    @Override
    public void onLongBookClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete this item from all resources?");
        builder.setMessage("Deletion is permanent...");

        builder.setPositiveButton("Yes", (dialog, which) -> deleteSongFromSongs(listOfBooks.get(position)))
                .setNegativeButton("No", (dialog, which) -> {                      });
        AlertDialog ad = builder.create();
        ad.show();
    }

    private void deleteSongFromSongs(Book book){
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
        DatabaseReference ref = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Books").child(book.getBookId());
        ref.removeValue();
    }


}