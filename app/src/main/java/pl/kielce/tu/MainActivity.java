package pl.kielce.tu;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TextView titleTV,authorTV;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = findViewById(R.id.saveBtn);
        titleTV = findViewById(R.id.titleTV);
        authorTV = findViewById(R.id.authorTV);

        saveBtn.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Book: " + titleTV.getText()
                        + authorTV.getText(),Toast.LENGTH_SHORT).show());
    }


}