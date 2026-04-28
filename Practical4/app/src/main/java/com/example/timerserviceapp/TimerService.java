package com.example.timerserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class TimerService extends Service {

    public static final String TIMER_UPDATED = "com.example.timerserviceapp.TIMER_UPDATED";
    public static final String TIMER_EXTRA = "timerExtra";

    private int seconds = 0;
    private boolean running = false;
    private Thread timerThread;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!running) {
            running = true;
            timerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (running) {
                        try {
                            Thread.sleep(1000);
                            if (running) {
                                seconds++;
                                Intent timerIntent = new Intent(TIMER_UPDATED);
                                timerIntent.putExtra(TIMER_EXTRA, seconds);
                                sendBroadcast(timerIntent);
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            });
            timerThread.start();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        running = false;
        if (timerThread != null) {
            timerThread.interrupt();
        }
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
