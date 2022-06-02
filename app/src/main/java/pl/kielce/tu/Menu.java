package pl.kielce.tu;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Menu extends AppCompatActivity {
    Button studentBtn,bookBtn,rentingBtn,showBooksBtn,searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Connect with database and point where we want to save our data
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("").child("members");

        //btnAddUser = findViewById(R.id.btnAddUser);
        studentBtn = findViewById(R.id.studentBtn);
        bookBtn = findViewById(R.id.bookBtn);
        rentingBtn = findViewById(R.id.rentingBtn);
        showBooksBtn = findViewById(R.id.showBooksBtn);
        searchBtn = findViewById(R.id.searchBtn);

        //share an object to database  --->   myRef.push().setValue(member);

        studentBtn.setOnClickListener(v -> moveToActivity(String.valueOf(Activities.ADD_STUDENT)));

        bookBtn.setOnClickListener(v -> moveToActivity(String.valueOf(Activities.ADD_BOOK)));

        rentingBtn.setOnClickListener(v -> moveToActivity(String.valueOf(Activities.RENT_BOOK)));

        showBooksBtn.setOnClickListener(v -> moveToActivity(String.valueOf(Activities.SHOW_BOOKS)));

        searchBtn.setOnClickListener(v -> moveToActivity(String.valueOf(Activities.SEARCH_BOOK)));
    }


    private void moveToActivity(String activity) {
        Intent intent;
        switch(activity) {
            case "ADD_STUDENT" : {
                intent = new Intent(this, SaveStudent.class);
                break;
            }
            case "ADD_BOOK" : {
                intent = new Intent(this, SaveBook.class);
                break;
            }
            case "SHOW_BOOKS" : {
                intent = new Intent(this, ShowBooks.class);
                break;
            }
            case "RENT_BOOK" : {
                intent = new Intent(this, HireBook.class);
                break;
            }
            case "SEARCH_BOOK" : {
                intent = new Intent(this, SearchBook.class);
                break;
            }
            default:
                intent = new Intent(this, SaveStudent.class);
        }
        startActivity(intent);
    }

}