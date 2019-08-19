package com.odbpo.fenggo.broadcast_service;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.odbpo.fenggo.broadcast_service.receiver.LocalReceiver;
import com.odbpo.fenggo.broadcast_service.receiver.CommonReceiver;
import com.odbpo.fenggo.broadcast_service.receiver.ReceiverConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.send_broadcast)
    Button sendBroadcast;
    @BindView(R.id.send_local_broadcast)
    Button sendLocalBroadcast;

    private CommonReceiver commonReceiver;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        initReceiver();
    }

    private void initReceiver() {
        //注册普通广播
        commonReceiver = new CommonReceiver(tvContent);
        IntentFilter commonFilter = new IntentFilter();
        commonFilter.addAction(ReceiverConstant.COMMON_BROADCAST);
        registerReceiver(commonReceiver, commonFilter);

        //注册应用内广播
        localReceiver = new LocalReceiver(tvContent);
        IntentFilter localFilter = new IntentFilter();
        localFilter.addAction(ReceiverConstant.LOCAL_BROADCAST);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(localReceiver,localFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(commonReceiver);//注销普通广播
        localBroadcastManager.unregisterReceiver(localReceiver);//注销应用内广播
    }

    @OnClick({R.id.send_broadcast, R.id.send_local_broadcast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_broadcast:
                Intent commonIntent = new Intent();
                commonIntent.putExtra("zc_data","发送普通广播数据：1000");
                commonIntent.setAction(ReceiverConstant.COMMON_BROADCAST);
                sendBroadcast(commonIntent);//发送广播
                break;
            case R.id.send_local_broadcast:
                Intent localIntent = new Intent();
                localIntent.putExtra("zc_local_data","发送应用内广播数据：2000");
                localIntent.setAction(ReceiverConstant.LOCAL_BROADCAST);
                localBroadcastManager.sendBroadcast(localIntent);
                break;
        }
    }
}
