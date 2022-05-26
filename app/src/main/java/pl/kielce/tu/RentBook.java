package pl.kielce.tu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;

public class RentBook extends AppCompatActivity {
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
        rentBtn = findViewById(R.id.rentalBtn);
        pole1 = findViewById(R.id.bookIdEdtTxt);
        pole2 = findViewById(R.id.studentIdEdtTxt);

//        rentBtn.setOnClickListener(v -> {
//            Renting renting = new Renting(pole1.getText().toString().trim(),pole2.getText().toString().trim());
//
//            DatabaseReference ref = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Rentals");
//            ref.child("" + renting.getRentingId()).setValue(renting.toHashMap())
//                    .addOnSuccessListener(unused -> Toast.makeText(RentBook.this,
//                            "Book rented successfully !", Toast.LENGTH_SHORT).show())
//                    .addOnFailureListener(e -> Toast.makeText(RentBook.this,"" + e.getMessage(),Toast.LENGTH_SHORT).show());
//        });

        menuBtn.setOnClickListener(v -> goToMenu());
    }

    private void goToMenu() {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }
}