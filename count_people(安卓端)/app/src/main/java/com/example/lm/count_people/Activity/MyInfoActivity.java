package com.example.lm.count_people.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.example.lm.count_people.R;
import com.example.lm.count_people.dto.Sensor;
import com.example.lm.count_people.dto.User;
import com.example.lm.count_people.util.OKhttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;


public class MyInfoActivity extends AppCompatActivity {

    private static final String TAG= "MyInfoActivity" ;

    private EditText user_info = null;
    private EditText role_info = null;
    private EditText phone_info = null;

    private String username;
    private Handler handler;

    private User user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);


        user_info  = findViewById(R.id.user_info);
        role_info  = findViewById(R.id.role_info);
        phone_info  = findViewById(R.id.phone_info);


        username = getIntent().getStringExtra("username");


        new Thread(runnable).start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                user_info.setText(username);

                Bundle bundle=msg.getData();
                String res=bundle.getString("res");
                Log.i(TAG, "res: "+res);

                Type type=new TypeToken<User>(){}.getType();
                Gson gson=new Gson();
                user= gson.fromJson(res,type);
                Log.i(TAG, "user: "+user);

                role_info.setText(user.getRole());
                phone_info.setText(user.getPhonenum());



            }

        };

    }

    Runnable runnable = new Runnable() {


        @Override
        public void run() {
            try {
                Log.i(TAG, "run: ");
                OkHttpClient client = new OkHttpClient();
                Gson gson = new Gson();
                String result = OKhttpUtil.getURL(LoginActivity.URL + "user/getUserByName?username="+username,client);
                Log.i(TAG, "run: "+result);

                Message msg = new Message();
                Bundle bundle=new Bundle();
                bundle.putString("res",result);
                msg.setData(bundle);
                handler.sendMessage(msg);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
