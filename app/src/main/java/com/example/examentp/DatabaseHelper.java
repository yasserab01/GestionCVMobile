package com.example.examentp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Etudiant.db";
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
                col_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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
                col_14 + " INTEGER, " +
                "etudiant_id INTEGER, " +  // Use a unique name for the foreign key column
                "FOREIGN KEY(etudiant_id) REFERENCES " + TABLE_NAME + "(" + col_1 + "));";
        sqLiteDatabase.execSQL(createFormationsTable);


        // Create the Diplômes table
        String createDiplomesTable = "CREATE TABLE " + TABLE_NAME2 + " (" +
                col_21 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_22 + " TEXT, " +
                col_23 + " TEXT, " +
                col_24 + " INTEGER, " +
                col_25 + " INTEGER, " +
                "FOREIGN KEY(" + col_25 + ") REFERENCES " + TABLE_NAME + "(" + col_1 + "));";
        sqLiteDatabase.execSQL(createDiplomesTable);

        // Create the Expériences table
        String createExperiencesTable = "CREATE TABLE " + TABLE_NAME3 + " (" +
                col_31 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_32 + " TEXT, " +
                col_33 + " TEXT, " +
                col_34 + " INTEGER, " +
                col_35 + " INTEGER, " +
                "FOREIGN KEY(" + col_35 + ") REFERENCES " + TABLE_NAME + "(" + col_1 + "));";
        sqLiteDatabase.execSQL(createExperiencesTable);

        // Create the Competence table
        String createCompetenceTable = "CREATE TABLE " + Table_Name4 + " (" +
                col_41 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_42 + " TEXT, " +
                col_43 + " TEXT, " +
                col_25 + " INTEGER, " +
                "FOREIGN KEY(" + col_25 + ") REFERENCES " + TABLE_NAME + "(" + col_1 + "));";
        sqLiteDatabase.execSQL(createCompetenceTable);

        // Create the Langues table
        String createLanguesTable = "CREATE TABLE " + Table_Name5 + " (" +
                col_51 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_52 + " TEXT, " +
                col_53 + " TEXT, " +
                col_25 + " INTEGER, " +
                "FOREIGN KEY(" + col_25 + ") REFERENCES " + TABLE_NAME + "(" + col_1 + "));";
        sqLiteDatabase.execSQL(createLanguesTable);
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

        // Recreate the database
        onCreate(sqLiteDatabase);
    }



    public boolean insertEtudiant(String Nom,String Prenom ,Integer Age,String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(col_2,Nom);
        contentValues.put(col_3,Prenom);
        contentValues.put(col_4,Age);
        long res= db.insert(TABLE_NAME,null,contentValues);
        return res != -1;
    }

    public Cursor getAllEtudiant(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+TABLE_NAME,null);
    }

    public boolean UpdateEtudiant(String ID,String Nom,String Prenom ,Integer Age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(col_1,ID);
        contentValues.put(col_2,Nom);
        contentValues.put(col_3,Prenom);
        contentValues.put(col_4,Age);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{ID});
        return true;
    }

    public Integer Delete(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{ID});
    }


    // Méthode pour insérer dans la table Formations
    public boolean insertFormation(String nom, String institution, Integer année, Integer etudiantID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_12, nom);
        contentValues.put(col_13, institution);
        contentValues.put(col_14, année);
        contentValues.put(col_15, etudiantID);
        long result = db.insert(TABLE_NAME1, null, contentValues);
        return result != -1;
    }

    // Méthode pour récupérer toutes les données de la table Formations
    public Cursor getAllFormations() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
    }

    // Méthode pour insérer dans la table Diplômes
    public boolean insertDiplome(String nom, String institution, Integer année, Integer etudiantID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_22, nom);
        contentValues.put(col_23, institution);
        contentValues.put(col_24, année);
        contentValues.put(col_25, etudiantID);
        long result = db.insert(TABLE_NAME2, null, contentValues);
        return result != -1;
    }

    // Méthode pour récupérer toutes les données de la table Diplômes
    public Cursor getAllDiplomes() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
    }


    // Méthode pour insérer dans la table Expériences
    public boolean insertExperience(String nom, String institution, Integer année, Integer etudiantID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_32, nom);
        contentValues.put(col_33, institution);
        contentValues.put(col_34, année);
        contentValues.put(col_35, etudiantID);
        long result = db.insert(TABLE_NAME3, null, contentValues);
        return result != -1;
    }

    // Méthode pour récupérer toutes les données de la table Expériences
    public Cursor getAllExperiences() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME3, null);
    }



}
