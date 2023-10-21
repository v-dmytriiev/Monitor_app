package com.vdmytriiev.monitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private EditText email;
    private EditText password;
    private final ArrayList<String> emails = new ArrayList<>();
    private final ArrayList<String> passwords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = findViewById(R.id.login);
        InputStream emails_data = getResources().openRawResource(R.raw.emails);
        InputStream passwords_data = getResources().openRawResource(R.raw.password);
        try {
            BufferedReader em = new BufferedReader(new InputStreamReader(emails_data));
            BufferedReader pas = new BufferedReader(new InputStreamReader(passwords_data));
            String line;
            while ((line = em.readLine()) != null) {
                emails.add(line);
            }
            while ((line = pas.readLine()) != null) {
                passwords.add(line);
            }
            em.close();
            pas.close();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error reading user data", Toast.LENGTH_SHORT).show();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailEditText = findViewById(R.id.email);
                EditText passwordEditText = findViewById(R.id.password);
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                int index = emails.indexOf(email);
                if (index != -1 && passwords.get(0).equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}