package com.exactmobile.jean.challenge.callbacks;

import com.exactmobile.jean.challenge.model.ImageItem;

/**
 * Created by jmvnkuru on 20/04/16.
 */
public interface ImageClickedListener {
    void onImageClicked(ImageItem imageItem, final int position);
}
