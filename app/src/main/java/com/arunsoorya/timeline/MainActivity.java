package com.arunsoorya.timeline;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.arunsoorya.timeline.presenter.ImagePresenterImpl;
import com.arunsoorya.timeline.view.ImageSelectListener;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements ImageSelectListener {

    @BindView(R.id.coordinator_layout)
    private CoordinatorLayout coordinatorLayout;

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
        Snackbar snackbar = Snackbar.make(coordinatorLayout,"Failed to get the "+permission,Snackbar.LENGTH_LONG)
                .setAction("Re-try", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                });
        snackbar.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagePresenterImpl.PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                && data != null) {

        }
    }
    private void setPic(String mCurrentPhotoPath) {
        // Get the dimensions of the View
//        int targetW = mImageView.getWidth();
//        int targetH = mImageView.getHeight();
//
//        // Get the dimensions of the bitmap
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        bmOptions.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//        int photoW = bmOptions.outWidth;
//        int photoH = bmOptions.outHeight;
//
//        // Determine how much to scale down the image
//        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
//
//        // Decode the image file into a Bitmap sized to fill the View
//        bmOptions.inJustDecodeBounds = false;
//        bmOptions.inSampleSize = scaleFactor;
//        bmOptions.inPurgeable = true;
//
//        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//        mImageView.setImageBitmap(bitmap);
    }
    @Override
    public void imageSelectionFailed(String reason) {
        Snackbar.make(coordinatorLayout,"Failed to get the image",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void imageSelected(Bitmap bitmap) {

    }
}
