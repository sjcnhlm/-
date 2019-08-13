package com.example.lm.count_people.Activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lm.count_people.BroadcastReceiver.LoginOutBroadcastReceiver;
import com.example.lm.count_people.util.ActivityCollector;

public class BaseCompatActivity extends AppCompatActivity {

    protected LoginOutBroadcastReceiver locallReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 创建活动时，将其加入管理器中
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 注册广播接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("loginout");
        locallReceiver = new LoginOutBroadcastReceiver();
        registerReceiver(locallReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 取消注册广播接收器
        unregisterReceiver(locallReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 销毁活动时，将其从管理器中移除
        ActivityCollector.removeActivity(this);
    }


}
