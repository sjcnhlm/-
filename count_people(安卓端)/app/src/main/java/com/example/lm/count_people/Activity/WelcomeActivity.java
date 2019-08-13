package com.example.lm.count_people.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lm.count_people.R;

public class WelcomeActivity extends AppCompatActivity implements Runnable {
    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new  Thread(this).start();
        setContentView(R.layout.activity_welcom);
        //启动一个延迟线程

    }
    public void run(){
        try{
            /**
             * 延迟1秒时间
             */
            Thread.sleep(1000);
            SharedPreferences preferences= getSharedPreferences("count", 0); // 存在则打开它，否则创建新的Preferences
            int count = preferences.getInt("count", 0); // 取出数据

            Log.i(TAG, "count的值是: "+count);


            /**
             *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
             */
            if (count == 0) {
                Intent intent1 = new Intent();
                intent1.setClass(WelcomeActivity.this,GuideActivity.class);
                startActivity(intent1);
            } else {
                Intent intent2 = new Intent();
                intent2.setClass(WelcomeActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
            }
            finish();
            //实例化Editor对象
            SharedPreferences.Editor editor = preferences.edit();
            //存入数据
            editor.putInt("count", 1); // 存入数据
            //提交修改
            editor.commit();
        } catch (InterruptedException e) {

        }


    }
}
