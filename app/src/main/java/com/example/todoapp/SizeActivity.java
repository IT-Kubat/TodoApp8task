package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class SizeActivity extends AppCompatActivity {

    private TextView mInfoTextView;

    EditText editText;
    float size;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);
        editText = findViewById(R.id.edit_size);

        RadioButton redRadioButton = (RadioButton)findViewById(R.id.Shrift_14);
        redRadioButton.setOnClickListener(radioButtonClickListener);

        RadioButton greenRadioButton = (RadioButton)findViewById(R.id.Shrift_22);
        greenRadioButton.setOnClickListener(radioButtonClickListener);

        RadioButton blueRadioButton = (RadioButton)findViewById(R.id.Shrift_28);
        blueRadioButton.setOnClickListener(radioButtonClickListener);

        }
        View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.Shrift_14: editText.setTextSize(14);
                size = editText.getTextSize();
                    intent.putExtra("size", size);
                    setResult(RESULT_OK,intent);
                    finish();
                    break;
                case R.id.Shrift_22: editText.setTextSize(22);
                    size = editText.getTextSize();
                    intent.putExtra("size", size);
                    setResult(RESULT_OK,intent);
                    finish();
                    break;
                case R.id.Shrift_28: editText.setTextSize(24);
                    size = editText.getTextSize();
                    intent.putExtra("size", size);
                    setResult(RESULT_OK,intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
}


