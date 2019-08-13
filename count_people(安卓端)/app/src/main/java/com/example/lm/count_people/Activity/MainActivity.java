package com.example.lm.count_people.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lm.count_people.R;
import com.example.lm.count_people.dto.User;
import com.example.lm.count_people.util.OKhttpUtil;
import com.google.gson.Gson;
import com.spark.submitbutton.SubmitButton;

import java.io.IOException;

import okhttp3.OkHttpClient;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    private SubmitButton login_bt = null;
    private SubmitButton register_bt = null;
    private EditText login_user = null;
    private EditText login_password = null;
    private TextView forget_password = null;
    private User user;
    private Handler handler;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_user = findViewById(R.id.login_user);
        login_password = findViewById(R.id.login_password);
        login_bt = findViewById(R.id.login_bt);
        register_bt = findViewById(R.id.register_bt);

        forget_password = findViewById(R.id.forget_password);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        Toast.makeText(MainActivity.this,"用户不存在",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,FgFunctionActivity.class);
                        String usernanme = user.getUsername();
                        Log.i(TAG,"用户名是"+usernanme);
                        intent.putExtra("username", usernanme);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this,"异常",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User( login_user.getText().toString().trim(), login_password.getText().toString().trim());

                // Log.i(TAG, "onClick: "+user.getName());
                if (user.getUsername() == null || user.getUsername().equals("")) {
                    Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (user.getPassword() == null || user.getPassword().equals("")) {
                    Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    //login();
                    new Thread(runnable).start();
                }
            }
        });

        register_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegistActivity.class);
                startActivity(intent);
            }
        });


        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgetPasseordActivity.class);
                startActivity(intent);
            }
        });
    }

    Runnable runnable = new Runnable() {
            @Override
        public void run() {
            try {
                Log.i(TAG, "run: ");
                OkHttpClient client = new OkHttpClient();
                Gson gson = new Gson();
                String params = gson.toJson(user);
                String result = OKhttpUtil.postURL(URL + "user/loginUser",params,client);
                Log.i(TAG, "run: "+result);
                Message msg = new Message();
                Log.i(TAG, "run: "+result);
                msg.what = Integer.parseInt(result);

                handler.sendMessage(msg);
                Log.i(TAG, "onClick: "+result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}
