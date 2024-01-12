package com.example.examentp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            DB = new DatabaseHelper(this);
            Cursor students =DB.getAllEtudiant();
            ListView listViewStudents =  findViewById(R.id.listViewStudents);
            String[] fromColumns = { "_id","Nom", "Prenom"};
            int[] toViews = { R.id.textViewId, R.id.textViewName,R.id.textViewLast };

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.student_item, students, fromColumns, toViews, 0);
            listViewStudents.setAdapter(adapter);
            listViewStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                    int studentId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                    Intent intent = new Intent(MainActivity.this, StudentDetails.class);
                    intent.putExtra("studentId", studentId);
                    startActivity(intent);
                }
            }


            );
        }catch (Exception e) {
            e.printStackTrace(); // Print the exception stack trace to Logcat for debugging
        }

    }

    public void CreateStudent(View view){
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);

    }

    public void StudentDetails() {
        Intent intent = new Intent(this, StudentDetails.class);
        startActivity(intent);
    }
}