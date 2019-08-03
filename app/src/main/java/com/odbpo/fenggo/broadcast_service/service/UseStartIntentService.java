package com.odbpo.fenggo.broadcast_service.service;

import android.app.IntentService;
import android.content.Intent;

import com.odbpo.fenggo.broadcast_service.receiver.IntentServiceReceiver;
import com.odbpo.fenggo.broadcast_service.receiver.ReceiverConstant;

public class UseStartIntentService extends IntentService {

    private IntentServiceReceiver receiver;

    public UseStartIntentService() {
        super("UseStartIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.receiver = new IntentServiceReceiver();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int data = intent.getIntExtra("data", 0);
        try {
            Thread.sleep(2000);
            //发送广播
            Intent intent1 = new Intent();
            intent1.setAction(ReceiverConstant.START_INTENT_SERVICE);
            intent1.putExtra("data",data);
            sendBroadcast(intent1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
