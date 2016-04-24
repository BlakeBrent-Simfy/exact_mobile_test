package com.exactmobile.jean.challenge.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
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
import com.exactmobile.jean.challenge.background.SoundService;
import com.exactmobile.jean.challenge.callbacks.ImageClickedListener;
import com.exactmobile.jean.challenge.model.ImageItem;
import com.exactmobile.jean.challenge.utils.ImageItemBuilder;

import java.util.List;


public class ImageItemListFragment extends android.support.v4.app.Fragment {
    private View fragView;
    private SoundService mSoundService;
    private Intent mPlayIntent;
    private boolean mSoundServiceBound = false;


    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        if (mPlayIntent == null) {
            mPlayIntent = new Intent(getActivity(), SoundService.class);
            getActivity().bindService(mPlayIntent, soundServiceConnection, Context.BIND_AUTO_CREATE);
            getActivity().startService(mPlayIntent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView = inflater.inflate(R.layout.image_list, container, false);
        initialize();
        return fragView;
    }

    private void initialize() {
        RecyclerView recyclerView = (RecyclerView) fragView.findViewById(R.id.rv_image_list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        List<ImageItem> imageItemList = ImageItemBuilder.buildImageItemList();
        ImageItemAdapter adapter = new ImageItemAdapter(imageItemList, getActivity(), new ImageClickedListener() {
            @Override
            public void onImageClicked(ImageItem imageItem, int position) {
                buildAndShowDialog(imageItem, position);
            }

        });
        recyclerView.setAdapter(adapter);
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
                        playSound(position);
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

    private void playSound(int position) {
        mSoundService.playSound(position);
    }


    private ServiceConnection soundServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SoundService.SoundPlayerBinder binder = (SoundService.SoundPlayerBinder) service;
            mSoundService = binder.getService();
            mSoundServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mSoundServiceBound = false;
        }
    };

    @Override
    public void onDestroy() {
        unbindService();
        super.onDestroy();
    }

    private void unbindService() {
        if (mSoundServiceBound && soundServiceConnection != null) {
            getActivity().unbindService(soundServiceConnection);
            soundServiceConnection = null;
        }
    }
}
