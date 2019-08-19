package com.odbpo.fenggo.broadcast_service.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.odbpo.fenggo.broadcast_service.Main2Activity;
import com.odbpo.fenggo.broadcast_service.Main3Activity;
import com.odbpo.fenggo.broadcast_service.MainActivity;
import com.odbpo.fenggo.broadcast_service.service.UseBindIntentService;

public class CommonReceiver extends BroadcastReceiver {

    private TextView tvContent;

    public CommonReceiver(TextView tvContent) {
        this.tvContent = tvContent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("zc_data");
        tvContent.setText(data);
    }
}
