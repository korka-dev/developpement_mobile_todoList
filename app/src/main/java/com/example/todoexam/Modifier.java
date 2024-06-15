package com.example.todoexam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Modifier extends AppCompatActivity {

    String[] choix = {" ","Todo","In process","Done","Bug"};

    Spinner status;

    ArrayAdapter<String> adapter;

    int index;

    FloatingActionButton retouner;

    EditText tache , description;

    Button modification;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        modification = findViewById(R.id.modif_button);
        tache = findViewById(R.id.tache_modif);
        description = findViewById(R.id.description_modif);
        retouner = findViewById(R.id.go_main_Button);
        status = findViewById(R.id.spinner_modif);

        Intent intent_retour = new Intent(this, MainActivity.class);

        retouner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_retour);
            }
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,choix);

        status.setAdapter(adapter);

        Intent intent = getIntent();
        int position = intent.getIntExtra("data", -1);

        int id_listview = position + 1;

        com.example.todoexam.MyDataBaseHelper DB = new com.example.todoexam.MyDataBaseHelper(Modifier.this);



        String[] Infos =  DB.getInfosFromId(id_listview);

        String name = Infos[0];
        String status_value = Infos[1];
        String descrip = Infos[2];

        for (int i = 0; i < choix.length; i++) {
            if (choix[i].equals(status_value)) {
                index = i;
                break;
            }
        }

        tache.setText(name);
        description.setText(descrip);
        status.setSelection(index);


        modification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // tache.getText().toString().trim();
               // description.getText().toString().trim();
               // status.getSelectedItem().toString().trim();
                DB.Modifier(id_listview,tache.getText().toString().trim(),description.getText().toString().trim(),status.getSelectedItem().toString().trim());
            }
        });

    }
}