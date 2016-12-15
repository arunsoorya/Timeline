package com.arunsoorya.timeline;

import android.Manifest;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onPermissionGranted(String permission) {
        if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            checkPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }else{

        }
    }

    @Override
    public void onPermissionNotGranted(String permission) {
        Snackbar.make()
    }
}
