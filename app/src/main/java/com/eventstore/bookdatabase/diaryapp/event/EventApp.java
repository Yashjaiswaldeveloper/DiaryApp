package com.eventstore.bookdatabase.diaryapp.event;

import android.app.Application;
import android.os.Handler;
import android.os.StrictMode;

import com.eventstore.bookdatabase.diaryapp.BuildConfig;

public class EventApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new Handler().postAtFrontOfQueue(this::enableStrictMode);
    }

    private void enableStrictMode(){
        if (BuildConfig.DEBUG){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        } else {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
    }
}
