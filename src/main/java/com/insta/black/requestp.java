package com.insta.black;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class requestp extends AppCompatActivity {

    private static final int PERMISSION_CODE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perm);
        Button Brequest = (Button) findViewById(R.id.request);

        
        Brequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rqpermissions();
            }
        });

    }

    private void Rqpermissions(){
        if (Build.VERSION.SDK_INT >= 23){
            if ( ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                Log.d("app","internet Perm not granted requesting");
                requestPermissions(new String[]{Manifest.permission.INTERNET},PERMISSION_CODE);
            }

        }else {
            Log.d("app","Build version >= 23 ");
            proceed();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                TextView t = findViewById(R.id.textView2);
                t.setText("requesting");
                Log.d("app","ok !");
                proceed();
            }else{
                Log.d("app","error perms not granted");
                Toast.makeText(this, "Permissions are required to proceed", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void proceed(){
        Intent login = new Intent(requestp.this, login.class);
        startActivity(login);
        finish();

    }
}