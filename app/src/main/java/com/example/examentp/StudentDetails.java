package com.example.examentp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.HashSet;
import java.util.Set;

public class StudentDetails extends AppCompatActivity {
    private DatabaseHelper DB;
    private TextView name, last, age, email, experiences, formations, competences, langues, diplomes;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        DB = new DatabaseHelper(this);
        Intent intent = getIntent();
        int studentId = intent.getIntExtra("studentId", 0);
        Cursor student = DB.getEtudiant(String.valueOf(studentId));

        // Initialize sets to store unique values
        Set<String> experienceSet = new HashSet<>();
        Set<String> formationSet = new HashSet<>();
        Set<String> competenceSet = new HashSet<>();
        Set<String> langueSet = new HashSet<>();
        Set<String> diplomeSet = new HashSet<>();

        if (student.moveToFirst()) {
            String etudiantNom = student.getString(student.getColumnIndex("EtudiantNom"));
            String etudiantPrenom = student.getString(student.getColumnIndex("EtudiantPrenom"));
            String etudiantAge = student.getString(student.getColumnIndex("EtudiantAge"));
            String etudiantEmail = student.getString(student.getColumnIndex("EtudiantEmail"));
            do {

                // Add unique values to sets
                if (student.getString(student.getColumnIndex("ExperienceNom")) != null) {
                    experienceSet.add(student.getString(student.getColumnIndex("ExperienceNom")));
                }
                if (student.getString(student.getColumnIndex("FormationNom")) != null) {
                    formationSet.add(student.getString(student.getColumnIndex("FormationNom")));
                }
                if (student.getString(student.getColumnIndex("CompetenceIntitule")) != null) {
                    competenceSet.add(student.getString(student.getColumnIndex("CompetenceIntitule")));
                }
                if (student.getString(student.getColumnIndex("Langue")) != null) {
                    langueSet.add(student.getString(student.getColumnIndex("Langue")));
                }
                if (student.getString(student.getColumnIndex("DiplomeNom")) != null) {
                    diplomeSet.add(student.getString(student.getColumnIndex("DiplomeNom")));
                }

            } while (student.moveToNext());

            String experiencesText = String.join(" | ", experienceSet);
            String formationsText = String.join(" | ", formationSet);
            String competencesText = String.join(" | ", competenceSet);
            String languesText = String.join(" | ", langueSet);
            String diplomesText = String.join(" | ", diplomeSet);

            // Find TextView elements
            name = findViewById(R.id.textViewName);
            last = findViewById(R.id.textViewLastName);
            age = findViewById(R.id.textViewAge);
            email = findViewById(R.id.textViewEmail);
            experiences = findViewById(R.id.textViewExperiences);
            formations = findViewById(R.id.textViewFormations);
            competences = findViewById(R.id.textViewCompetences);
            langues = findViewById(R.id.textViewLangues);
            diplomes = findViewById(R.id.textViewDiplomes);

            // Set TextView values
            name.setText(etudiantNom);
            last.setText(etudiantPrenom);
            age.setText(etudiantAge);
            email.setText(etudiantEmail);
            experiences.setText(experiencesText);
            formations.setText(formationsText);
            competences.setText(competencesText);
            langues.setText(languesText);
            diplomes.setText(diplomesText);
        } else {
            System.out.println("No data found");
        }
    }

    public void Modifier(View view) {
        Intent intent = getIntent();
        int studentId = intent.getIntExtra("studentId", 0);
        Intent intent2 = new Intent(this, UpdateStudent.class);
        intent2.putExtra("studentId", studentId);
        startActivity(intent2);
    }

    public void Supprimer(View view) {
        Intent intent = getIntent();
        int studentId = intent.getIntExtra("studentId", 0);
        DB.delete(String.valueOf(studentId));
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }
}
