
//10119094 IF-3 Saeful Anwar Oktariansah

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

public class LoginActivity extends AppCompatActivity {
    EditText Logemail, Logpass;
    Button LogBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Logemail = findViewById(R.id.EmailLoginTxt);
        Logpass = findViewById(R.id.PasswordLoginTxt);
        LogBtn = findViewById(R.id.LoginBtnInLog);

        fAuth = FirebaseAuth.getInstance();
        //        Jika user sudah terdaftar maka user akan diarahkan ke MainActivity secara otomatis
        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        LogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Logemail.getText().toString().trim();
                String password = Logpass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Logemail.setError("Email tidak boleh kosong");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Logpass.setError("Password tidak boleh kosong");
                    return;
                }
                if (password.length() < 6){
                    Logpass.setError("Password harus lebih dari 6 karakter");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, "Login gagal " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        TextView registerbtn = findViewById(R.id.RegisterBtnInLog);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}