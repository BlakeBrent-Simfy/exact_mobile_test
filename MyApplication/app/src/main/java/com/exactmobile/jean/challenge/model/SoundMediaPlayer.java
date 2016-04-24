package com.exactmobile.jean.challenge.model;

import android.media.MediaPlayer;


public class SoundMediaPlayer extends MediaPlayer {
    private static SoundMediaPlayer mSoundMediaPlayer;

    private SoundMediaPlayer() {
        super();
    }

    public static SoundMediaPlayer getInstance() {
        if (mSoundMediaPlayer == null) {
            mSoundMediaPlayer = new SoundMediaPlayer();
        }

        return mSoundMediaPlayer;
    }
}
