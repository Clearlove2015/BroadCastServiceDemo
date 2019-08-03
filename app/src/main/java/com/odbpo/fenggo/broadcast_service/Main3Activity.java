package com.odbpo.fenggo.broadcast_service;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.odbpo.fenggo.broadcast_service.receiver.MyReceiver;
import com.odbpo.fenggo.broadcast_service.receiver.ReceiverConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.send_broadcast)
    Button sendBroadcast;

    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        initReceiver();
    }

    private void initReceiver() {
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ReceiverConstant.TEST_BROADCAST);
        //registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(receiver);
    }

    @OnClick(R.id.send_broadcast)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setAction(ReceiverConstant.TEST_BROADCAST);
        sendBroadcast(intent);//发送广播
    }
}
