package com.exactmobile.jean.challenge.model;

import android.media.MediaPlayer;

/**
 * Created by jmvnkuru on 21/04/16.
 */
public class SoundPlayerSingleton extends MediaPlayer {
    private static SoundPlayerSingleton soundPlayerSingleton;

    private SoundPlayerSingleton() {
    }

    public static SoundPlayerSingleton getInstance() {
        if (soundPlayerSingleton == null)
            soundPlayerSingleton = new SoundPlayerSingleton();

        return soundPlayerSingleton;
    }
}
