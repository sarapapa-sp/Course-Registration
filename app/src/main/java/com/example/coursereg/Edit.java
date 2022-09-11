package com.example.coursereg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    EditText name,course,fees,id;
    Button btnedit,btndelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
//        accessing the variables
        name=findViewById(R.id.name);
        course=findViewById(R.id.course);
        fees=findViewById(R.id.fees);
        id=findViewById(R.id.id);

//        Getting the buttons
        btnedit=findViewById(R.id.btnok);
        btndelete=findViewById(R.id.btnview);

//        adding the on click listners for edit and delete buttons
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Calling the edit function to edit the view
                edit();
            }
        });

    }


    public void edit(){
        try{
            String nameData = name.getText().toString();
            String courseData = course.getText().toString();
            String feesData = fees.getText().toString();
            String idData = id.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SQLite", Context.MODE_PRIVATE,null);

            String sql = "update records set name=? , course=? ,fees=? where id=?";
            SQLiteStatement statement=db.compileStatement(sql);
            statement.bindString(1,nameData);
            statement.bindString(2,courseData);
            statement.bindString(3,feesData);
            statement.bindString(3,idData);
            statement.execute();

            Toast.makeText(this,"Record was edited successfully",Toast.LENGTH_LONG).show();

        }catch (Exception exception){
            Toast.makeText(this,"Record was not edited.",Toast.LENGTH_LONG).show();
        }
    }
}