package com.insta.black;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class upload extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle svi){
        super.onCreate(svi);
        setContentView(R.layout.up);
        setContentView(R.layout.up);
        String path = svi.getString("path");
        File fl = new File(path);
        

    }
}
