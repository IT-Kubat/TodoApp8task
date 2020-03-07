package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.model.Work;

public class FormActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editDesk;
    private Work myWork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle = findViewById(R.id.editTitle);
        editDesk = findViewById(R.id.editDesk);
        edit();
    }

    public void onClick(View view) {
        String title = editTitle.getText().toString().trim();
        String desc = editDesk.getText().toString().trim();
        Work work = new Work(title, desc);
        work.setTitle(title);
        work.setDescription(desc);
        Intent intent = new Intent();
        intent.putExtra("title", title);
        setResult(RESULT_OK,intent);
        if (editTitle.getText().toString().matches("")|| editDesk.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Fill the Line", Toast.LENGTH_SHORT).show();
        }else if(myWork != null){
            myWork.setTitle(title);
            myWork.setDescription(desc);
            App.getDatabase().workDao().update(myWork);

        }else {
            myWork = new Work(title,desc);
            App.getDatabase().workDao().insert(myWork);
        }
        finish();

    }

    public void edit(){
        myWork = (Work) getIntent().getSerializableExtra("work");
        if (myWork != null){
            editDesk.setText(myWork.getDescription());
            editTitle.setText(myWork.getTitle());
        }
    }
}
