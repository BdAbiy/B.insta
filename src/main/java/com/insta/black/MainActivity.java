package com.insta.black;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isConnected()) {
                    try {
                        Intent intent = new Intent(MainActivity.this, requestpermissons.class);
                        startActivity(intent);
                        finish();
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(MainActivity.this, "LoginActivity not found", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    try {
                        Intent offlineIntent = new Intent(MainActivity.this, offline.class);
                        startActivity(offlineIntent);
                        finish();
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(MainActivity.this, "OfflineActivity not found", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        }, 10000);
    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        return nf != null && nf.isConnectedOrConnecting();
    }
}