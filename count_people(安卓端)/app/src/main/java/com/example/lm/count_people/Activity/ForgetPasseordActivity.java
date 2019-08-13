package com.example.lm.count_people.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lm.count_people.R;
import com.example.lm.count_people.dto.User;
import com.example.lm.count_people.util.OKhttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;

import static com.example.lm.count_people.Activity.LoginActivity.URL;

public class ForgetPasseordActivity extends AppCompatActivity {

    private EditText f_user = null;
    private EditText f_password = null;
    private EditText fc_password = null;
    private Button change = null;
    private User user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        f_user = findViewById(R.id.f_user);
        f_password = findViewById(R.id.f_password);
        fc_password = findViewById(R.id.fc_password);
        change = findViewById(R.id.change);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = f_user.getText().toString();
                String password = f_password.getText().toString();
                String confirm_password = fc_password.getText().toString();
                if(!password.equals(confirm_password))
                {
                    Toast.makeText(ForgetPasseordActivity.this,"两次密码不一致，请重新重新输入密码",Toast.LENGTH_SHORT).show();
                    f_password.setText("");
                    fc_password.setText("");
                }

                user = new User(username,password);
                new Thread(runnable).start();

                Toast.makeText(ForgetPasseordActivity.this,"密码已修改，请返回登录",Toast.LENGTH_SHORT).show();

            }
        });
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {

                OkHttpClient client = new OkHttpClient();
                Gson gson = new Gson();
                String params = gson.toJson(user);
                String result = OKhttpUtil.postURL(URL + "user/forget_password",params,client);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };



}
