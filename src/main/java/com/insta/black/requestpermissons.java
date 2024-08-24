package com.insta.black;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class requestpermissons extends AppCompatActivity{
    private final int PERMISIONCODE = 11;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requestp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            proceed();

        }
        Button request = findViewById(R.id.request);

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(requestpermissons.this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(requestpermissons.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISIONCODE);
                    }else {
                        proceed();
                    }
                }
            }
        });
    }
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == PERMISIONCODE){
                if (grantResults.length > 0 ){
                    int pg = 0;
                    for(int i : grantResults){
                        if (i == PackageManager.PERMISSION_GRANTED){
                            pg++;
                        }
                        if (i == grantResults.length){
                            proceed();
                        }
                    }
                }else{
                   TextView text =  findViewById(R.id.textView2);
                   text.setTextColor(Color.red(1));
                }

            }
        }
        public void proceed(){
        Intent login = new Intent(requestpermissons.this, login.class);
        startActivity(login);
        finish();
        }
    }


