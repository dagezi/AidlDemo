// IDgzSample.aidl
package com.example.aidldemo.service;

interface IDgzSample {
    void sleep(int milliSec);

    oneway void sleepOneWay(int milliSec);

    int plus(int a, int b);

    int getPid();
}