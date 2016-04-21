package com.exactmobile.jean.challenge.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.exactmobile.jean.challenge.R;
import com.exactmobile.jean.challenge.adapter.ImageItemAdapter;
import com.exactmobile.jean.challenge.callbacks.ImageClickedListener;
import com.exactmobile.jean.challenge.model.ImageItem;
import com.exactmobile.jean.challenge.model.SoundPlayerSingleton;
import com.exactmobile.jean.challenge.utils.ImageItemBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmvnkuru on 20/04/16.
 */
public class ImageItemListFragment extends android.support.v4.app.Fragment {
    private List<ImageItem> imageItemList = new ArrayList<>();
    private ImageItemAdapter adapter;
    private RecyclerView recyclerView;
    private View fragView;
    private int[] soundIds = new int[10];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView = inflater.inflate(R.layout.image_list, container, false);
        initialize();
        return fragView;
    }

    private void initialize() {
        recyclerView = (RecyclerView) fragView.findViewById(R.id.image_list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        imageItemList = ImageItemBuilder.buildImageItemList();
        adapter = new ImageItemAdapter(imageItemList, getActivity(), new ImageClickedListener() {
            @Override
            public void onImageClicked(ImageItem imageItem, int position) {
                buildAndShowDialog(imageItem, position);
            }

        });
        recyclerView.setAdapter(adapter);
        populateSoundIds();
    }

    private void populateSoundIds() {
        soundIds[0] = R.raw.a;
        soundIds[1] = R.raw.b;
        soundIds[2] = R.raw.c;
        soundIds[3] = R.raw.d;
        soundIds[4] = R.raw.e;
        soundIds[5] = R.raw.f;
        soundIds[6] = R.raw.g;
        soundIds[7] = R.raw.h;
        soundIds[8] = R.raw.i;
        soundIds[9] = R.raw.j;
    }

    private void buildAndShowDialog(final ImageItem imageItem, final int position) {

        new MaterialDialog.Builder(getActivity())
                .title(imageItem.getTitle())
                .content(imageItem.getImageDescription())
                .positiveText(getString(R.string.zz_play))
                .negativeText(getString(R.string.zz_close))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        playRadio(position);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        //Do nothing, the material library already dismisses the dialog by default
                    }
                })
                .show();
    }

    private void playRadio(int position) {
        MediaPlayer mediaPlayer = SoundPlayerSingleton.create(getActivity(), soundIds[position]);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
    }


}
