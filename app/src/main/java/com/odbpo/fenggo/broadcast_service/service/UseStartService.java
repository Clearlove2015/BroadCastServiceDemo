package com.odbpo.fenggo.broadcast_service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.odbpo.fenggo.broadcast_service.receiver.ReceiverConstant;
import com.odbpo.fenggo.broadcast_service.receiver.ServiceReceiver;

public class UseStartService extends Service {

    private ServiceReceiver receiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new ServiceReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int data = intent.getIntExtra("data", 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    //发送广播
                    Intent intent1 = new Intent();
                    intent1.setAction(ReceiverConstant.START_SERVICE);
                    intent1.putExtra("data",data);
                    sendBroadcast(intent1);
                    System.out.println("发送广播");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }
}
