package com.example.todoexam;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    int id ;

    private Context context;

    // Database
    public static  final String DB_NAME = "myDb.db";
    public static final int DB_VERSION = 2;

    public static  final String TABLE_NAME = "Enregistrement";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_Status = "status";
    public static final String COLUMN_DESCRIPTION = "description";








    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_Status + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT " +
                ");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public void addEnregistrement(String name , String status, String description) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put("name", name);
            cv.put("status", status);
            cv.put("description",description);

            db.insert("Enregistrement", null, cv);
            db.close();

            Toast.makeText(context,"succesfull Registration!",Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
        }

    }


    public List<String> getTodo(){


        List<String> List = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        try {

            String[] projection = {"name"};


            Cursor c = db.query("Enregistrement",projection,null,null,null,null,null);


            if (c!=null && c.getCount()>0){

                c.moveToFirst();

                do {

                    @SuppressLint("Range") String Name = c.getString(c.getColumnIndex("name"));

                    List.add(Name);

                    c.moveToNext();

                }while (!c.isAfterLast());

            }


            c.close();

        }catch (Exception e){

            e.printStackTrace();

        }


        return List;
    }




    public String[] getInfosFromId(int Id) {


        try{

            SQLiteDatabase db = this.getReadableDatabase();

            // Construire une requête pour sélectionner les infos
            String sql = "SELECT  name, status , description  FROM Enregistrement WHERE id = "+Id;
            Cursor cursor = db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex("status"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));


                return new String[]{name, status, description};
            } else {
                return new String[]{"False"," ","False"};
            }

        }catch (Exception e) {
            e.printStackTrace();
            return new String[]{"false"," ","false"};
        }

    }



    public void Modifier(int id, String new_nom , String new_description , String new_status) {

/*
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] projection = {"id"};

            String selection = "name="+nom+" AND status="+status+" AND description="+description;


            Cursor c = db.query("Enregistrement",projection,selection,null,null,null,null);


            if (c!=null && c.getCount()>0){

                    @SuppressLint("Range") int Id = Integer.parseInt(c.getString(c.getColumnIndex("id")));

                    id = Id;

            }



        }catch (Exception e){

            e.printStackTrace();

            Toast.makeText(context," Error recup Id ",Toast.LENGTH_SHORT).show();

        }


*/


        try {



            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put("name", new_nom);
            cv.put("status", new_status);
            cv.put("description", new_description);


            db.update("Enregistrement",cv,"id="+id,null);


            //String sql = "UPDATE Enregistrement SET Statut = 'no' WHERE Statut = 'yes'";

            // db.update("Enregistrement", cv, null, null);


            db.close();

            Toast.makeText(context,"succesful Modifiaction!",Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
        }

    }



}
