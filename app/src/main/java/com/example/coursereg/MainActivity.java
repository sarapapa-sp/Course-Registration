package com.example.coursereg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    Creating variables
    /*
    * text boxes - Name , Course , Fees
    * buttons - Add , view
    * */
    EditText name,course,fees;
    Button btnok,btnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        accessing the variables
        name=findViewById(R.id.name);
        course=findViewById(R.id.course);
        fees=findViewById(R.id.fees);

//        Getting the buttons
        btnok=findViewById(R.id.btnok);
        btnview=findViewById(R.id.btnview);

//        adding the event listners
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });
//        changing the screen after view button clicked
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),view.class);
                startActivity(intent);
            }
        });
    }

    public void insert(){
        try{
            String nameData;
            nameData=name.getText().toString();
            String courseData = course.getText().toString();
            String feesData = fees.getText().toString();
//            TODO : Creating and accessing the SQLite Database and adding data to the database
            SQLiteDatabase db = openOrCreateDatabase("SQLite", Context.MODE_PRIVATE,null);
            db.execSQL("create table if not exists records(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,course VARCHAR,fee VARCHAR )");
            String sql = "insert into records(name,course,fee) values (?,?,?)";
            SQLiteStatement statement=db.compileStatement(sql);
            statement.bindString(1,nameData);
            statement.bindString(2,courseData);
            statement.bindString(3,feesData);
            statement.execute();


            Toast.makeText(this,"Record was added successfully",Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(this,"Record was not added",Toast.LENGTH_LONG).show();
        }
    }
}