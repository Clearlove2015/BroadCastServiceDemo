package com.odbpo.fenggo.broadcast_service.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class IntentServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int data = intent.getIntExtra("data", 0);
        Toast.makeText(context,"广播接收回调数据：" + data,Toast.LENGTH_SHORT).show();
    }
}
