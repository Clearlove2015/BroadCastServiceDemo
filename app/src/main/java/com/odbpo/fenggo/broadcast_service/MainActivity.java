package com.odbpo.fenggo.broadcast_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.odbpo.fenggo.broadcast_service.receiver.IntentServiceReceiver;
import com.odbpo.fenggo.broadcast_service.receiver.MyReceiver;
import com.odbpo.fenggo.broadcast_service.receiver.ReceiverConstant;
import com.odbpo.fenggo.broadcast_service.receiver.ServiceReceiver;
import com.odbpo.fenggo.broadcast_service.service.UseBindIntentService;
import com.odbpo.fenggo.broadcast_service.service.UseBindService;
import com.odbpo.fenggo.broadcast_service.service.UseStartIntentService;
import com.odbpo.fenggo.broadcast_service.service.UseStartService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_start_service)
    Button btnStartService;
    @BindView(R.id.btn_start_intent_service)
    Button btnStartIntentService;
    @BindView(R.id.btn_bind_service)
    Button btnBindService;
    @BindView(R.id.btn_bind_intent_service)
    Button btnBindIntentService;

    private MyReceiver receiver;
    private ServiceReceiver serviceReceiver;
    private IntentServiceReceiver intentServiceReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initReceiver();
    }

    private void initReceiver() {
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ReceiverConstant.TEST_BROADCAST);
        registerReceiver(receiver, filter);//注册

        serviceReceiver = new ServiceReceiver();
        IntentFilter filter1 = new IntentFilter();
        filter1.addAction(ReceiverConstant.START_SERVICE);
        registerReceiver(serviceReceiver,filter1);

        intentServiceReceiver = new IntentServiceReceiver();
        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(ReceiverConstant.START_INTENT_SERVICE);
        registerReceiver(intentServiceReceiver,filter2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);//注销
        unregisterReceiver(serviceReceiver);
        unregisterReceiver(intentServiceReceiver);
    }


    @OnClick({R.id.btn_next, R.id.btn_start_service,R.id.btn_start_intent_service,
            R.id.btn_bind_service, R.id.btn_bind_intent_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                startActivity(new Intent(this, Main2Activity.class));
                break;
            case R.id.btn_start_service:
                useStartService();
                break;
            case R.id.btn_start_intent_service:
                useStartIntentService();
                break;
            case R.id.btn_bind_service:
                useBindService();
                break;
            case R.id.btn_bind_intent_service:
                useBindIntentService();
                break;
        }
    }

    private void useStartService() {
        Intent intent = new Intent(this, UseStartService.class);
        intent.putExtra("data",10);
        startService(intent);
    }

    private void useStartIntentService() {
        Intent intent = new Intent(this, UseStartIntentService.class);
        intent.putExtra("data",20);
        startService(intent);
    }

    private void useBindService() {
        Intent intent = new Intent(this, UseBindService.class);
        intent.putExtra("data", 100);
        startService(intent);//启动服务
        //注意：使用bind方式前必须使用startService()启动服务
        //使用bind方式（如果不先使用startService()启动服务直接使用bind，onStartCommand()方法不执行）
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                UseBindService.MyBinder binder = (UseBindService.MyBinder) iBinder;
                UseBindService service = binder.getService();
                service.setServiceCall(new UseBindService.ServiceCall() {
                    @Override
                    public void updateData(int data) {
                        //注意切换线程
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "回调数据：" + data, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    private void useBindIntentService() {
        Intent intent = new Intent(this, UseBindIntentService.class);
        intent.putExtra("data", 200);
        startService(intent);//启动服务
        //注意：使用bind方式前必须使用startService()启动服务
        //bind方式(注意：如果是IntentService，前面必须使用startService()先启动一次服务，否则onHandleIntent方法不执行)
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                UseBindIntentService.MyBinder binder = (UseBindIntentService.MyBinder) iBinder;
                UseBindIntentService service = binder.getService();
                service.setServiceCall(new UseBindIntentService.ServiceCall() {
                    @Override
                    public void updateData(int data) {
                        Toast.makeText(MainActivity.this, "回调数据：" + data, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

}
