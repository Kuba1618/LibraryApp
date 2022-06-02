package pl.kielce.tu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import pl.kielce.tu.library.Book;

import java.util.ArrayList;
import java.util.List;

public class SearchBook extends AppCompatActivity implements AdapterAllOfBooks.OnBookListener{
    RecyclerView bookRecyclerView;
    DatabaseReference refBook;
    AdapterAllOfBooks adapterAllOfBooks;
    ArrayList<Book> listOfBooks;
    Button menuBtn;
    SearchView searchBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("All Of Books");

        searchBook = findViewById(R.id.searchBookView);
        searchBook.clearFocus();
        bookRecyclerView = findViewById(R.id.listOfSearchingBooksRv);

        menuBtn = findViewById(R.id.menuBtn);
        refBook = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Books");

        bookRecyclerView.setHasFixedSize(true);
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listOfBooks = new ArrayList<>();
        adapterAllOfBooks = new AdapterAllOfBooks(this,listOfBooks,this);
        bookRecyclerView.setAdapter(adapterAllOfBooks);

        searchBook.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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

        menuBtn.setOnClickListener(v -> goToMenu());
    }

    private void filterList(String query) {
        List<Book> filteredList = new ArrayList<>();
        for(Book b : listOfBooks) {
            if(b.getTitle().toLowerCase().contains(query.toLowerCase())){
                filteredList.add(b);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_LONG).show();
        }
        else {
            adapterAllOfBooks.setFilteredList(filteredList);
        }
    }

    @Override
    public void onBookCLick(int position) {
        Toast.makeText(this, "Book: " + listOfBooks.get(position).getTitle(), Toast.LENGTH_SHORT).show();
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

    private void goToMenu() {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }
}