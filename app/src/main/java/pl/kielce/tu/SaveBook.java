package pl.kielce.tu;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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

        saveBtn.setOnClickListener(v -> Toast.makeText(SaveBook.this, "Book: " + titleEdtTxt.getText()
                        + authorEdtTxt.getText(),Toast.LENGTH_SHORT).show());
    }


}