package com.odbpo.fenggo.broadcast_service;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.odbpo.fenggo.broadcast_service.receiver.MyReceiver;
import com.odbpo.fenggo.broadcast_service.receiver.ReceiverConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.send_broadcast)
    Button sendBroadcast;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initReceiver();
    }

    private void initReceiver() {
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ReceiverConstant.TEST_BROADCAST);
        //registerReceiver(receiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(receiver);
    }

    @OnClick({R.id.btn_next, R.id.send_broadcast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                startActivity(new Intent(this,Main3Activity.class));
                break;
            case R.id.send_broadcast:
                Intent intent = new Intent();
                intent.setAction(ReceiverConstant.TEST_BROADCAST);
                sendBroadcast(intent);//发送广播
                break;
        }
    }
}
