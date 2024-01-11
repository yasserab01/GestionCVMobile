package com.example.examentp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateStudent extends AppCompatActivity {

    private DatabaseHelper DB;
    private int studentId;

    private EditText name, last, age, email;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        DB = new DatabaseHelper(this);
        name = findViewById(R.id.editName);
        last = findViewById(R.id.editLastName);
        age = findViewById(R.id.editAge);
        email = findViewById(R.id.editEmail);

        Intent intent = getIntent();
        studentId = intent.getIntExtra("studentId", 0);
        Cursor student = DB.getEtudiant(String.valueOf(studentId));

        if (student.moveToFirst()){
            name.setText(student.getString(student.getColumnIndex("EtudiantNom")));
            last.setText(student.getString(student.getColumnIndex("EtudiantPrenom")));
            age.setText(student.getString(student.getColumnIndex("EtudiantAge")));
            email.setText(student.getString(student.getColumnIndex("EtudiantEmail")));
        }

    }

    public void updateStudent(View view){
        String nom = name.getText().toString();
        String prenom = last.getText().toString();
        int age = Integer.parseInt(this.age.getText().toString());
        String email = this.email.getText().toString();
        System.out.println("studentId: "+studentId+" nom: "+nom+" prenom: "+prenom+" age: "+age+" email: "+email);
        if(DB.UpdateEtudiant(String.valueOf(studentId),nom, prenom, age, email)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}