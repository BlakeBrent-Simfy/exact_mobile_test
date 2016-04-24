package com.exactmobile.jean.challenge.callbacks;

import com.exactmobile.jean.challenge.model.ImageItem;


public interface ImageClickedListener {
    void onImageClicked(ImageItem imageItem, final int position);
}
