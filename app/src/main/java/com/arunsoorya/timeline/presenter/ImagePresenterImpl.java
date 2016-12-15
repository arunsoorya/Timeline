package com.arunsoorya.timeline.presenter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.arunsoorya.timeline.BuildConfig;
import com.arunsoorya.timeline.MainActivity;
import com.arunsoorya.timeline.view.ImageSelectListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by arunkumar.sw on 15-12-2016.
 */

public class ImagePresenterImpl extends BasePresenter<ImageSelectListener> implements ImagePresenter {

    public static final int PICK_IMAGE_MULTIPLE = 11;
    private ImageSelectListener imageSelectListener;

    public ImagePresenterImpl(ImageSelectListener mainActivity) {
        super(mainActivity);
        this.imageSelectListener = mainActivity;
    }


    @Override
    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        }
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((Activity) context).startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

    }

    @Override
    public void manageSelectedUri(Intent data) {
        if (data.getClipData() != null) {
            ClipData mClipData = data.getClipData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
            for (int i = 0; i < mClipData.getItemCount(); i++) {

                ClipData.Item item = mClipData.getItemAt(i);
                Uri uri = item.getUri();
                mArrayUri.add(uri);
                // Get the cursor
                Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
                // Move to first row
               if(cursor != null) {
                   cursor.moveToFirst();

                   int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                   imageEncoded = cursor.getString(columnIndex);
//                   imagesEncodedList.add(imageEncoded);
//                   BitmapFactory.decodeFile(picturePath)
                   cursor.close();
               }

            }
            Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
//        String mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
