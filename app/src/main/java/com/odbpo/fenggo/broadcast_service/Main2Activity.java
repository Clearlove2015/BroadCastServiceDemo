package com.odbpo.fenggo.broadcast_service;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.odbpo.fenggo.broadcast_service.receiver.CommonReceiver;
import com.odbpo.fenggo.broadcast_service.receiver.LocalReceiver;
import com.odbpo.fenggo.broadcast_service.receiver.ReceiverConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private CommonReceiver commonReceiver;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initReceiver();
    }

    private void initReceiver() {
        //注册普通广播
        commonReceiver = new CommonReceiver(tvContent);
        IntentFilter commonFilter = new IntentFilter();
        commonFilter.addAction(ReceiverConstant.COMMON_BROADCAST);
        registerReceiver(commonReceiver,commonFilter);

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

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        startActivity(new Intent(this, Main3Activity.class));
    }
}
