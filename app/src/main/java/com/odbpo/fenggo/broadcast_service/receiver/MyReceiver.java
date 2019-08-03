package com.odbpo.fenggo.broadcast_service.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.odbpo.fenggo.broadcast_service.Main2Activity;
import com.odbpo.fenggo.broadcast_service.Main3Activity;
import com.odbpo.fenggo.broadcast_service.MainActivity;
import com.odbpo.fenggo.broadcast_service.service.UseBindIntentService;

public class MyReceiver extends BroadcastReceiver {

    private UseBindIntentService service;

    @Override
    public void onReceive(Context context, Intent intent) {
        service = new UseBindIntentService();
        if (context instanceof MainActivity) {
            Toast.makeText(context, "MainActivity接收到数据", Toast.LENGTH_SHORT).show();

            Intent intent1 = new Intent();
            intent1.setAction("com.odbpo.fenggo.broadcast_demo.service.UseBindIntentService");
            context.startService(intent1);
        } else if (context instanceof Main2Activity) {
            Toast.makeText(context, "Main2Activity接收到数据", Toast.LENGTH_SHORT).show();

        } else if (context instanceof Main3Activity) {
            Toast.makeText(context, "Main3Activity接收到数据", Toast.LENGTH_SHORT).show();

        }
    }
}
