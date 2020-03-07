package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {

    private EditText editPhone;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        editPhone = findViewById(R.id.editPhone);
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Log.e("TAG", "onVerificationCompleted");
                signIn(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.e("TAG", "onVerificationFailed:" + e.getMessage());

            }
        };
    }

    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(PhoneActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PhoneActivity.this,MainActivity.class));
                } else{
                    Toast.makeText(PhoneActivity.this, "Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }


            }
        });
    }


    public void onClickContinue(View view) {
        String phone = editPhone.getText().toString().trim();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                60,
                TimeUnit.SECONDS,
                this,
                callbacks);

    }
}
