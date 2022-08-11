package com.example.uas_akb_if3_10119094;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText Regemail, Regpass;
    Button RegBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Regemail = findViewById(R.id.EmailRegTxt);
        Regpass = findViewById(R.id.PasswordRegTxt);
        RegBtn = findViewById(R.id.RegisterBtnInReg);

        fAuth = FirebaseAuth.getInstance();
//        Jika user sudah terdaftar maka user akan diarahkan ke MainActivity secara otomatis
        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Regemail.getText().toString().trim();
                String password = Regpass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Regemail.setError("Email tidak boleh kosong");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Regpass.setError("Password tidak boleh kosong");
                    return;
                }
                if (password.length() < 6){
                    Regpass.setError("Password harus lebih dari 6 karakter");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Akun baru berhasil terdaftar", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }else {
                            Toast.makeText(RegisterActivity.this, "Akun baru gagal terdaftar " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        TextView loginbtn = findViewById(R.id.LoginBtnInReg);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}