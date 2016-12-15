package com.arunsoorya.timeline.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arunsoorya.timeline.view.ImageSelectListener;

/**
 * Created by arunkumar.sw on 15-12-2016.
 */

public interface ImagePresenter {
    void selectImage() throws Exception;
    void manageSelectedUri(Intent intent);
}
