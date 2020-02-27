package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.todoapp.model.Work;

public class FormActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editDesk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle = findViewById(R.id.editTitle);
        editDesk = findViewById(R.id.editDesk);
    }

    public void onClick(View view) {
        String title = editTitle.getText().toString().trim();
        String desc = editDesk.getText().toString().trim();
        Work work = new Work();
        work.setTitle(title);
        work.setDescription(desc);
        App.getDatabase().workDao().insert(work);
        Intent intent = new Intent();
        intent.putExtra("title", title);
        setResult(RESULT_OK,intent);
        finish();

    }
}
