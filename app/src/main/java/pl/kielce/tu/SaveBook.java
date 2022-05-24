package pl.kielce.tu;

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
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = findViewById(R.id.saveBtn);
        titleEdtTxt = findViewById(R.id.titleEdtTxt);
        authorEdtTxt = findViewById(R.id.authorEdtTxt);

        saveBtn.setOnClickListener(v -> saveBook());
    }

    public void saveBook(){

        Book book = new Book(titleEdtTxt.getText().toString().trim(),authorEdtTxt.getText().toString().trim());

        DatabaseReference ref = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Books");
        ref.child("" + book.getCopyOfBookId()).setValue(book.toHashMap())
                .addOnSuccessListener(unused -> Toast.makeText(SaveBook.this,
                        "Book saved successfully !", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(SaveBook.this,"" + e.getMessage(),Toast.LENGTH_SHORT).show());
    }


}