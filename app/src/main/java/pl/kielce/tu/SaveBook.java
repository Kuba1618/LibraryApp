package pl.kielce.tu;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pl.kielce.tu.library.Book;

public class SaveBook extends AppCompatActivity {

    EditText titleEdtTxt,authorEdtTxt;
    Button saveBtn,menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_book);

        saveBtn = findViewById(R.id.saveBtn);
        menuBtn = findViewById(R.id.menuBtn);
        titleEdtTxt = findViewById(R.id.titleEdtTxt);
        authorEdtTxt = findViewById(R.id.authorEdtTxt);

        menuBtn.setOnClickListener(v -> goToMenu());
        saveBtn.setOnClickListener(v -> saveBook());

    }

    private void goToMenu() {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }

    public void saveBook(){

        Book book = new Book(titleEdtTxt.getText().toString().trim(),authorEdtTxt.getText().toString().trim());

        DatabaseReference ref = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Books");
        ref.child("" + book.getBookId()).setValue(book.toHashMap())
                .addOnSuccessListener(unused -> Toast.makeText(SaveBook.this,
                        "Book saved successfully !", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(SaveBook.this,"" + e.getMessage(),Toast.LENGTH_SHORT).show());
    }


}