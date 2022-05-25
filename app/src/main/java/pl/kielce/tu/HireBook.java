package pl.kielce.tu;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import pl.kielce.tu.library.Renting;

public class HireBook extends AppCompatActivity {
    Button menuBtn,rentBtn;
    EditText pole1,pole2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_book);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Renting Book");

        menuBtn = findViewById(R.id.menuBtn);
        rentBtn = findViewById(R.id.rentBtn);
        pole1 = findViewById(R.id.bookIdEdtTxt);
        pole2 = findViewById(R.id.studentIdEdtTxt);

        rentBtn.setOnClickListener(v -> {
            Renting renting = new Renting(pole1.getText().toString().trim(),pole2.getText().toString().trim());

            DatabaseReference ref = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Rentals");
            ref.child("" + renting.getRentingId()).setValue(renting.toHashMap())
                    .addOnSuccessListener(unused -> Toast.makeText(HireBook.this,
                            "Book rented successfully !", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(HireBook.this,"" + e.getMessage(),Toast.LENGTH_SHORT).show());
        });

        menuBtn.setOnClickListener(v -> goToMenu());
    }

    private void goToMenu() {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }
}