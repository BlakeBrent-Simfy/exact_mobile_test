package com.exactmobile.jean.challenge.background;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by jmvnkuru on 21/04/16.
 */

//FIXME--This class technically should implement intent service if we were running a music play service
public class PlaySoundIntentService extends IntentService{

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public PlaySoundIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
