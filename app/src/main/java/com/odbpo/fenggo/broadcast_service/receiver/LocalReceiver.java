package com.odbpo.fenggo.broadcast_service.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class LocalReceiver extends BroadcastReceiver {

    private TextView tvContent;

    public LocalReceiver(TextView tvContent) {
        this.tvContent = tvContent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("zc_local_data");
        tvContent.setText(data);
    }
}
