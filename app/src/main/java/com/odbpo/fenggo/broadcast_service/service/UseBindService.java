package com.odbpo.fenggo.broadcast_service.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class UseBindService extends Service {

    private MyBinder binder;
    private ServiceCall call;

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("zc onBind()");
        this.binder = new MyBinder();
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("zc onStartCommand()");
        int data = intent.getIntExtra("data", 0);
        //Service中执行耗时操作必须新开一个线程，此处注意与IntentService区别
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    call.updateData(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    public void setServiceCall(ServiceCall call){
        this.call = call;
    }

    public interface ServiceCall{
        void updateData(int data);
    }

    public class MyBinder extends Binder{
        public UseBindService getService(){
            return UseBindService.this;
        }
    }
}
