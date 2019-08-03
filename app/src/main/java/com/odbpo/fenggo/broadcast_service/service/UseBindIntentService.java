package com.odbpo.fenggo.broadcast_service.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * IntentService用法
 */
public class UseBindIntentService extends IntentService {

    private MyBinder binder;
    private ServiceCall call;

    //注意构造方法的写法
    public UseBindIntentService() {
        super("UseBindIntentService");
        System.out.println("zc UseBindIntentService()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        System.out.println("zc onBind()");
        this.binder = new MyBinder();
        return binder;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("zc onHandleIntent()");

        int data = intent.getIntExtra("data", 0);

        //IntentService中直接耗时操作
        try {
            Thread.sleep(2000);
            //回调数据
            call.updateData(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setServiceCall(ServiceCall call) {
        this.call = call;
    }

    public interface ServiceCall {
        void updateData(int data);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("zc onUnbind()");
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {

        public UseBindIntentService getService() {
            return UseBindIntentService.this;
        }
    }

}
