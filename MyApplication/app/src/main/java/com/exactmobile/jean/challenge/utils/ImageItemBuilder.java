package com.exactmobile.jean.challenge.utils;

import com.exactmobile.jean.challenge.model.ImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmvnkuru on 20/04/16.
 */
public class ImageItemBuilder {

    public static List<ImageItem> buildImageItemList() {
        String dummy = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        String prefixImageUrl = "http://placehold.it/4096x4096&text=Track";

        List<ImageItem> imageItemList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            imageItemList.add(new ImageItem(prefixImageUrl + (i + 1), (i + 1) + " " + dummy + prefixImageUrl + (i + 1), prefixImageUrl + (i + 1)));
        }
        return imageItemList;
    }
}
