package com.example.easygo.driver;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DriverMessageService extends Service {
    public DriverMessageService() { }

    ExecutorService executor = Executors.newSingleThreadExecutor(); //kreira samo jedan thread
    Handler handler = new Handler(Looper.getMainLooper()); //handler koji upravlja glavim thread-om od aplikacije (applications main thread)

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "onStartCommand");
        executor.execute(() -> {
            /*
                Za sada samo ispisujemo poruku u konzoli, posle cemo raditi sa porukama.
                Ispod ovoga je zakomentarisano ono sto se radilo na Vezbe05, mozda ce zatrebati kasnije pa zato stoji tu i dalje.
             */
            Log.e("Service", "Send new message");

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            handler.post(() -> {
                Log.i("REZ", "UI Thread work here");
                Intent intent1 = new Intent("message");
                intent1.putExtra("messageText", "Legendo kako si?");
                getApplicationContext().sendBroadcast(intent1);
            });
        });

        stopSelf();
        return START_NOT_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}