package com.example.coursereg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class view extends AppCompatActivity {
    /*
    * Variables to store the data and the list view
    * */
    ListView list;
    ArrayList<String> titleData = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

//        Connecting with the DB
        SQLiteDatabase db = openOrCreateDatabase("SQLite", Context.MODE_PRIVATE,null);

        list=findViewById(R.id.list);
        final Cursor cursor=db.rawQuery("select * from records",null);
        int id=cursor.getColumnIndex("id");
        int name=cursor.getColumnIndex("name");
        int course=cursor.getColumnIndex("course");
        int fee=cursor.getColumnIndex("fee");
        titleData.clear();

        arrayAdapter=new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titleData);
        list.setAdapter(arrayAdapter);
//      Using the Student class to store the properties
        final ArrayList<Student> students = new ArrayList<Student>();

        if(cursor.moveToFirst()){
            do{
                Student stud = new Student();
                stud.id=cursor.getString(id);
                stud.course=cursor.getString(course);
                stud.name=cursor.getString(name);
                stud.fee=cursor.getString(fee);

                students.add(stud);

                titleData.add(cursor.getString(id)+" \t "+cursor.getString(name)+ " \t "+cursor.getString(course)+ " \t "+cursor.getString(fee));

            }while (cursor.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            list.invalidateViews();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String dataOfPosition = titleData.get(position).toString();

//                Toast.makeText(getApplicationContext(),dataOfPosition,Toast.LENGTH_LONG).show();
                Student data = students.get(position);
                Intent intent = new Intent(getApplicationContext(),Edit.class);
                intent.putExtra("id",data.id);
                intent.putExtra("name",data.name);
                intent.putExtra("course",data.course);
                intent.putExtra("fee",data.fee);
                startActivity(intent);

            }
        });

    }
}