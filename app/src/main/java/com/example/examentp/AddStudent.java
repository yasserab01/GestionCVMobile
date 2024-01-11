package com.example.examentp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {

    private DatabaseHelper DB;
    private EditText edtNom,edtPrenom,edtAge,edtEmail;
    private Button btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        DB = new DatabaseHelper(this);
        edtNom =findViewById(R.id.etNom);
        edtPrenom = findViewById(R.id.etPrenom);
        edtAge = findViewById(R.id.etAge);
        edtEmail = findViewById(R.id.etEmail);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        AddData();

    }

    public void AddData(){
        btnAddStudent.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        boolean
                                isInserted=DB.insertEtudiant(edtNom.getText().toString(),edtPrenom.getText().toString(),Integer.parseInt(edtAge.getText().toString()),edtEmail.getText().toString());
                        if(isInserted){
                            Toast.makeText(AddStudent.this, "Data Inserted",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddStudent.this, "Data Not Inserted",
                                    Toast.LENGTH_SHORT).show();
                        }
                        ReturnToMain(view);
                    }
                }
        );
    }

    public void ReturnToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}