package com.example.aidldemo.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log


class DgzSampleClient {
    val TAG = "DgzSampleClient"

    val connection = Connection()

    fun bind(ctx: Context) {
        Log.d(TAG, "bind");
        val intent = Intent(ctx, DgzSampleService::class.java)
        ctx.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun sleep(ms: Int) {
        connection.sleep(ms)
    }

    fun sleepOneWay(ms: Int) {
        connection.sleepOneWay(ms)
    }

    class Connection: ServiceConnection {
        val TAG = "DgzSampleConnection"

        lateinit var iDgzSample: IDgzSample
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            Log.d(TAG, "onSerivceConnected")
            iDgzSample = IDgzSample.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            Log.d(TAG, "onServiceDisconnected")
        }

        fun sleep(ms: Int) {
            Log.d(TAG, "sleep")
            iDgzSample.sleep(ms)
            Log.d(TAG, "sleep: end")
        }

        fun sleepOneWay(ms :Int) {
            Log.d(TAG, "sleepOneWay")
            iDgzSample.sleepOneWay(ms)
            Log.d(TAG, "sleepOneWay: end")
        }
    }
}