package com.arunsoorya.timeline.view;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by arunkumar.sw on 15-12-2016.
 */

public interface ImageSelectListener {

    void imageSelectionFailed(String reason);

    void imageSelected(Bitmap bitmap);

}
