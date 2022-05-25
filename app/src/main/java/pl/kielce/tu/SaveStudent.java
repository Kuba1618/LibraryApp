package pl.kielce.tu;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pl.kielce.tu.library.Student;

public class SaveStudent extends AppCompatActivity {

    EditText nameEdtTxt,surnameEdtTxt;
    Button saveBtn,menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_student);

        saveBtn = findViewById(R.id.saveBtn);
        menuBtn = findViewById(R.id.menuBtn);
        nameEdtTxt = findViewById(R.id.nameEdtTxt);
        surnameEdtTxt = findViewById(R.id.surnameEdtTxt);
        menuBtn.setOnClickListener(v -> goToMenu());
        saveBtn.setOnClickListener(v -> saveBook());
    }
    public void saveBook(){

        Student student = new Student(nameEdtTxt.getText().toString().trim(),surnameEdtTxt.getText().toString().trim());

        DatabaseReference ref = FirebaseDatabase.getInstance(Firebase.firebaseURL).getReference("Students");
        ref.child("" + student.getStudentId()).setValue(student.toHashMap())
                .addOnSuccessListener(unused -> Toast.makeText(SaveStudent.this,
                        "Student saved successfully !", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(SaveStudent.this,"" + e.getMessage(),Toast.LENGTH_SHORT).show());
    }

    private void goToMenu() {
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }


}