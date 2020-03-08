package com.example.todoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ProfileActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;
    private ImageView mImage;
    private Uri mImageUri;

    SharedPreferences sharedPreferences;
    public static String myProfile = "MyProfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mImage = findViewById(R.id.imageView);
        editText = findViewById(R.id.edit_profile);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String image = preferences.getString("image", "");
        mImage.setImageURI(Uri.parse(image));


    }


    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 2 && data != null) {
            mImageUri = data.getData();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("image", String.valueOf(mImageUri));
            editor.apply();

            mImage.setImageURI(mImageUri);
            mImage.invalidate();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}

