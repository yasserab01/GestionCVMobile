package com.example.examentp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Etudiant2.db";
    public static final String TABLE_NAME="Etudiant";
    public static final String col_1="ID";
    public static final String col_2="Nom";
    public static final String col_3="Prenom";
    public static final String col_4="Age";
    public static final String col_5="Email";
    public static final String col_6="Formations";
    public static final String col_7="Expériences";
    public static final String col_8="Diplômes";
    public static final String col_9="Competences";
    public static final String col_91="Langues";

    public static final String TABLE_NAME1="Formations";
    public static final String col_11="ID";
    public static final String col_12="Nom";
    public static final String col_13="Institution";
    public static final String col_14="Année";
    public static final String col_15="EtudiantID";
    public static final String TABLE_NAME2="Diplômes";
    public static final String col_21="ID";
    public static final String col_22="Nom";
    public static final String col_23="Institution";
    public static final String col_24="Année";
    public static final String col_25="EtudiantID";

    public static final String TABLE_NAME3="Expériences";
    public static final String col_31="ExperienceID";
    public static final String col_32="Nom";
    public static final String col_33="Entreprise";
    public static final String col_34="Poste";
    public static final String col_35="Description";
    public static final String col_36="DateDebut";
    public static final String col_37="DateFin";
    public static final String col_38="Ville";
    public static final String col_39="Pays";
    public static final String col_331="EtudiantID";


    public static final String Table_Name4="Competence";
    public static final String col_41="CompetenceID";
    public static final String col_42="IntituleCompetence";
    public static final String col_43="NiveauMaitrise";
    public static final String col_44="EtudiantID";

    public static final String Table_Name5="Langues";
    public static final String col_51="LangueID";
    public static final String col_52="Langue";
    public static final String col_53="Niveau";
    public static final String col_54="EtudiantID";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create the Etudiant table
        String createEtudiantTable = "CREATE TABLE " + TABLE_NAME + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "  +
                col_2 + " TEXT, " +
                col_3 + " TEXT, " +
                col_4 + " INTEGER, " +
                col_5 + " TEXT);";
        sqLiteDatabase.execSQL(createEtudiantTable);

        // Create the Formations table
        String createFormationsTable = "CREATE TABLE " + TABLE_NAME1 + " (" +
                col_11 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_12 + " TEXT, " +
                col_13 + " TEXT, " +
                col_14 + " INTEGER);";
        sqLiteDatabase.execSQL(createFormationsTable);

        // Create the junction table for the many-to-many relationship between Etudiant and Formations
        String createEtudiantFormationsTable = "CREATE TABLE EtudiantFormations (" +
                "EtudiantID INTEGER, " +
                "FormationID INTEGER, " +
                "FOREIGN KEY(EtudiantID) REFERENCES Etudiant(ID), " +
                "FOREIGN KEY(FormationID) REFERENCES Formations(" + col_11 + "));";
        sqLiteDatabase.execSQL(createEtudiantFormationsTable);

        // Create the Diplômes table
        String createDiplomesTable = "CREATE TABLE " + TABLE_NAME2 + " (" +
                col_21 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_22 + " TEXT, " +
                col_23 + " TEXT, " +
                col_24 + " INTEGER);";
        sqLiteDatabase.execSQL(createDiplomesTable);

        // Create the junction table for the many-to-many relationship between Etudiant and Diplômes
        String createEtudiantDiplomesTable = "CREATE TABLE EtudiantDiplomes (" +
                "EtudiantID INTEGER, " +
                "DiplomeID INTEGER, " +
                "FOREIGN KEY(EtudiantID) REFERENCES Etudiant(ID), " +
                "FOREIGN KEY(DiplomeID) REFERENCES Diplômes(" + col_21 + "));";
        sqLiteDatabase.execSQL(createEtudiantDiplomesTable);

        // Create the Expériences table
        String createExperiencesTable = "CREATE TABLE " + TABLE_NAME3 + " (" +
                col_31 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_32 + " TEXT, " +
                col_33 + " TEXT, " +
                col_34 + " INTEGER);";
        sqLiteDatabase.execSQL(createExperiencesTable);

        // Create the junction table for the many-to-many relationship between Etudiant and Expériences
        String createEtudiantExperiencesTable = "CREATE TABLE EtudiantExperiences (" +
                "EtudiantID INTEGER, " +
                "ExperienceID INTEGER, " +
                "FOREIGN KEY(EtudiantID) REFERENCES Etudiant(ID), " +
                "FOREIGN KEY(ExperienceID) REFERENCES Expériences(" + col_31 + "));";
        sqLiteDatabase.execSQL(createEtudiantExperiencesTable);

        // Create the Competence table
        String createCompetenceTable = "CREATE TABLE " + Table_Name4 + " (" +
                col_41 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_42 + " TEXT, " +
                col_43 + " TEXT);";
        sqLiteDatabase.execSQL(createCompetenceTable);

// Create the junction table for the many-to-many relationship between Etudiant and Competence
        String createEtudiantCompetenceTable = "CREATE TABLE EtudiantCompetence (" +
                "EtudiantID INTEGER, " +
                "CompetenceID INTEGER, " +
                "FOREIGN KEY(EtudiantID) REFERENCES Etudiant(ID), " +
                "FOREIGN KEY(CompetenceID) REFERENCES Competence(" + col_41 + "));";
        sqLiteDatabase.execSQL(createEtudiantCompetenceTable);

        // Create the Langues table
        String createLanguesTable = "CREATE TABLE " + Table_Name5 + " (" +
                col_51 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_52 + " TEXT, " +
                col_53 + " TEXT);";
        sqLiteDatabase.execSQL(createLanguesTable);

// Create the junction table for the many-to-many relationship between Etudiant and Langues
        String createEtudiantLanguesTable = "CREATE TABLE EtudiantLangues (" +
                "EtudiantID INTEGER, " +
                "LangueID INTEGER, " +
                "FOREIGN KEY(EtudiantID) REFERENCES Etudiant(ID), " +
                "FOREIGN KEY(LangueID) REFERENCES Langues(" + col_51 + "));";
        sqLiteDatabase.execSQL(createEtudiantLanguesTable);




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop all the tables if they exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name4);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name5);
        onCreate(sqLiteDatabase);
    }



    public boolean insertEtudiant(String Nom,String Prenom ,Integer Age,String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(col_2,Nom);
        contentValues.put(col_3,Prenom);
        contentValues.put(col_4,Age);
        contentValues.put(col_5,Email);
        long res= db.insert(TABLE_NAME,null,contentValues);
        for (int i = 0; i < 2; i++) {
            int r = ((int) (Math.random() * 4))+1;
            contentValues = new ContentValues();
            contentValues.put("EtudiantID", res);
            contentValues.put("FormationID", r);
            db.insert("EtudiantFormations", null, contentValues);
            contentValues = new ContentValues();
            contentValues.put("EtudiantID", res);
            contentValues.put("DiplomeID", r);
            db.insert("EtudiantDiplomes", null, contentValues);
            contentValues = new ContentValues();
            contentValues.put("EtudiantID", res);
            contentValues.put("ExperienceID", r);
            db.insert("EtudiantExperiences", null, contentValues);
            contentValues = new ContentValues();
            contentValues.put("EtudiantID", res);
            contentValues.put("CompetenceID", r);
            db.insert("EtudiantCompetence", null, contentValues);
            contentValues = new ContentValues();
            contentValues.put("EtudiantID", res);
            contentValues.put("LangueID", r);
            db.insert("EtudiantLangues", null, contentValues);

        }
        return res != -1;
    }

    public Cursor getAllEtudiant(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+TABLE_NAME,null);
    }

    public Cursor getEtudiant(String ID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " +
                "Etudiant._id AS EtudiantID, " +
                "Etudiant.Nom AS EtudiantNom, " +
                "Etudiant.Prenom AS EtudiantPrenom, " +
                "Etudiant.Age AS EtudiantAge, " +
                "Etudiant.Email AS EtudiantEmail, " +
                "Formations.ID AS FormationID, " +
                "Formations.Nom AS FormationNom, " +
                "Formations.Institution AS FormationInstitution, " +
                "Formations.Année AS FormationAnnée, " +
                "Diplômes.ID AS DiplomeID, " +
                "Diplômes.Nom AS DiplomeNom, " +
                "Diplômes.Institution AS DiplomeInstitution, " +
                "Diplômes.Année AS DiplomeAnnée, " +
                "Expériences.ExperienceID AS ExperienceID, " +
                "Expériences.Nom AS ExperienceNom, " +
                "Expériences.Entreprise AS ExperienceEntreprise, " +
                "Expériences.Poste AS ExperiencePoste, " +
                "Competence.CompetenceID AS CompetenceID, " +
                "Competence.IntituleCompetence AS CompetenceIntitule, " +
                "Competence.NiveauMaitrise AS CompetenceNiveau, " +
                "Langues.LangueID AS LangueID, " +
                "Langues.Langue AS Langue, " +
                "Langues.Niveau AS LangueNiveau " +
                "FROM Etudiant " +
                "LEFT JOIN EtudiantFormations ON Etudiant._id = EtudiantFormations.EtudiantID " +
                "LEFT JOIN Formations ON EtudiantFormations.FormationID = Formations.ID " +
                "LEFT JOIN EtudiantDiplomes ON Etudiant._id = EtudiantDiplomes.EtudiantID " +
                "LEFT JOIN Diplômes ON EtudiantDiplomes.DiplomeID = Diplômes.ID " +
                "LEFT JOIN EtudiantExperiences ON Etudiant._id = EtudiantExperiences.EtudiantID " +
                "LEFT JOIN Expériences ON EtudiantExperiences.ExperienceID = Expériences.ExperienceID " +
                "LEFT JOIN EtudiantCompetence ON Etudiant._id = EtudiantCompetence.EtudiantID " +
                "LEFT JOIN Competence ON EtudiantCompetence.CompetenceID = Competence.CompetenceID " +
                "LEFT JOIN EtudiantLangues ON Etudiant._id = EtudiantLangues.EtudiantID " +
                "LEFT JOIN Langues ON EtudiantLangues.LangueID = Langues.LangueID " +
                "WHERE Etudiant._id = ?";

        String[] selectionArgs = {ID};

        return db.rawQuery(query, selectionArgs);
    }

    public Cursor getEtudiantOnly(String ID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " +
                "Etudiant._id AS EtudiantID, " +
                "Etudiant.Nom AS EtudiantNom, " +
                "Etudiant.Prenom AS EtudiantPrenom, " +
                "Etudiant.Age AS EtudiantAge, " +
                "Etudiant.Email AS EtudiantEmail " +
                "FROM Etudiant " +
                "WHERE Etudiant._id = ?";

        String[] selectionArgs = {ID};

        return db.rawQuery(query, selectionArgs);
    }


    public boolean UpdateEtudiant(String ID,String Nom,String Prenom ,Integer Age,String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(col_2,Nom);
        contentValues.put(col_3,Prenom);
        contentValues.put(col_4,Age);
        contentValues.put(col_5,Email);
        db.update(TABLE_NAME,contentValues,"_id = ?",new String[]{ID});
        return true;
    }

    public void delete(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "_id = ?", new String[]{ID});
    }




}
