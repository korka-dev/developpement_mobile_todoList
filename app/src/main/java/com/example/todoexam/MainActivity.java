package com.example.todoexam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add_btn;

    private ImageView filterImageView;
    private LinearLayout checkboxMenuContainer;

    private boolean isMenuVisible;


    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, com.example.todoexam.todo_add.class);

        add_btn = findViewById(R.id.addingButton);
        filterImageView = findViewById(R.id.filter);
        checkboxMenuContainer = findViewById(R.id.checkbox_menu_container);

        filterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuVisible) {
                    hideCheckboxMenu();
                } else {
                    showCheckboxMenu();
                }
                isMenuVisible = !isMenuVisible;
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(intent); // Démarrer l'activité cible
            }
        });
            initView();
    }

    private void initView() {

        com.example.todoexam.MyDataBaseHelper MyDB = new com.example.todoexam.MyDataBaseHelper(this);

        List<String> List = MyDB.getTodo();


        list = findViewById(R.id.todo_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row, R.id.name, List);


        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), com.example.todoexam.Modifier.class);
                intent.putExtra("data", position);
                startActivity(intent);
            }
        });
    }


    private void showCheckboxMenu() {
        checkboxMenuContainer.setVisibility(View.VISIBLE);
        // ... Gérer l'animation ou les effets du menu à cases à cocher (facultatif)
    }

    private void hideCheckboxMenu() {
        checkboxMenuContainer.setVisibility(View.GONE);
        // ... Gérer l'animation ou les effets du menu à cases à cocher (facultatif)
    }

}