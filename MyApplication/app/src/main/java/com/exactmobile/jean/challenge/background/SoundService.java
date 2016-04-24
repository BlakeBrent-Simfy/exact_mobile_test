package com.exactmobile.jean.challenge.background;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;

import com.exactmobile.jean.challenge.model.SoundMediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SoundService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    private SoundMediaPlayer mMediaPlayer;
    private final IBinder mSoundBinder = new SoundPlayerBinder();
    private List<AssetFileDescriptor> soundList;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mSoundBinder;
    }

    public class SoundPlayerBinder extends Binder {
        public SoundService getService() {
            return SoundService.this;
        }
    }


    public void onCreate() {
        initiateSoundPlayer();
        setSoundListFromAssets();
    }


    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    public void initiateSoundPlayer() {
        mMediaPlayer = SoundMediaPlayer.getInstance();
        mMediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void playSound(final int position) {
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(soundList.get(position).getFileDescriptor(),
                    soundList.get(position).getStartOffset(), soundList.get(position).getLength());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {

            //TODO--Handle exception here if this was a proper media play app
            e.printStackTrace();
        }

    }

    private void setSoundListFromAssets() {
        soundList = new ArrayList<>();
        try {
            soundList.add(getApplication().getAssets().openFd("a.mp3"));
            soundList.add(getApplication().getAssets().openFd("b.mp3"));
            soundList.add(getApplication().getAssets().openFd("c.mp3"));
            soundList.add(getApplication().getAssets().openFd("d.mp3"));
            soundList.add(getApplication().getAssets().openFd("e.mp3"));
            soundList.add(getApplication().getAssets().openFd("f.mp3"));
            soundList.add(getApplication().getAssets().openFd("g.mp3"));
            soundList.add(getApplication().getAssets().openFd("h.mp3"));
            soundList.add(getApplication().getAssets().openFd("i.mp3"));
            soundList.add(getApplication().getAssets().openFd("j.mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
