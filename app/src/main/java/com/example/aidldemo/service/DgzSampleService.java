package com.example.aidldemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class DgzSampleService extends Service {
    public static final String TAG = "DgzSampleService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: " + intent);
        return binder;
    }

    private final IDgzSample.Stub binder = new IDgzSample.Stub() {
        @Override
        public void sleep(int milliSec) throws RemoteException {
            Log.d(TAG, "API sleep " + Thread.currentThread());
            try {
                Thread.sleep(milliSec);
            } catch (InterruptedException e) {
                Log.e(TAG, "sleep: " + e);
                throw new RemoteException(e.toString());
            }
        }

        @Override
        public int plus(int a, int b) throws RemoteException {
            Log.d(TAG, "API plus " + Thread.currentThread());
            return a + b;
        }

        @Override
        public int getPid() throws RemoteException {
            Log.d(TAG, "API getPid");
            return android.os.Process.myPid();
        }

        @Override
        public void sleepOneWay(int milliSec) throws RemoteException {
            Log.d(TAG, "API sleepOneWay " + Thread.currentThread());
            try {
                Thread.sleep(milliSec);
            } catch (InterruptedException e) {
                Log.e(TAG, "sleepOneWay: " + e);
                throw new RemoteException(e.toString());
            }
        }
    };
}
