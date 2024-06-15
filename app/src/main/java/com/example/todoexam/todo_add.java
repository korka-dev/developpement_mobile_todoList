package com.example.todoexam;

import android.os.Handler;
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


public class todo_add extends AppCompatActivity {

    EditText name , descripttion;
    Spinner status;

    Button ajouter;

    Handler handler;

    FloatingActionButton button;

    String[] choix = {" ","Todo","In process","Done","Bug"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_add);



        name = findViewById(R.id.tache);
        descripttion = findViewById(R.id.description);
        status = findViewById(R.id.spinner);
        ajouter = findViewById(R.id.add_button);

        button = findViewById(R.id.annulationButton);

        Intent intent = new Intent(this, MainActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,choix);

        status.setAdapter(adapter);

        ajouter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyDataBaseHelper MyDB = new MyDataBaseHelper(todo_add.this);
                MyDB.addEnregistrement(name.getText().toString().trim(), status.getSelectedItem().toString().trim(), descripttion.getText().toString().trim());

            }
        });


    }
}