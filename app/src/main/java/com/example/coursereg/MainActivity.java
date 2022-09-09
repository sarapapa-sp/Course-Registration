package com.example.coursereg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

            }
        });
    }

    public void insert(){
        try{
            String nameData;
            nameData=name.getText().toString();
            String courseData = course.getText().toString();
            String feesData = fees.getText().toString();

        }catch (Exception ex){

        }
    }
}