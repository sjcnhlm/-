package com.example.lm.count_people.Activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lm.count_people.R;
import com.example.lm.count_people.dto.User;
import com.example.lm.count_people.util.OKhttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

import static com.example.lm.count_people.Activity.LoginActivity.URL;

public class RegistActivity extends AppCompatActivity {
    private static final String TAG = "RegistActivity";
    private EditText regist_user = null;
    private EditText regist_password = null;
    private EditText confirm_password = null;
    private Spinner spinner = null;
    private EditText regist_phone = null;
    private Button regist = null;

    private User user;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;


    String text_role = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        regist_user = findViewById(R.id.regist_user);
        regist_password = findViewById(R.id.regist_password);
        confirm_password = findViewById(R.id.confirm_password);
        spinner = findViewById(R.id.spinner);
        regist_phone = findViewById(R.id.regist_phone);
        regist = findViewById(R.id.regist);


        data_list = new ArrayList<String>();
        data_list.add("教师");
        data_list.add("学生");

        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int i, long l) {

                text_role = spinner.getItemAtPosition(i).toString();


            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });


        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = regist_user.getText().toString();
                String password = regist_password.getText().toString();
                String C_password = confirm_password.getText().toString();
                String phone_num = regist_phone.getText().toString();

                user = new User(username,password,text_role,phone_num);
                if(!password.equals(C_password))
                {
                    Toast.makeText(RegistActivity.this,"两次密码不一致，请重新重新输入密码",Toast.LENGTH_SHORT).show();
                    regist_password.setText("");
                    confirm_password.setText("");
                    return;
                }
                else if(username == null || username.equals("") )
                {
                    Toast.makeText(RegistActivity.this,"用户名为空，请重新输入",Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(password == null || password.equals("") )
                {
                    Toast.makeText(RegistActivity.this,"密码为空，请重新输入",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    new Thread(runnable).start();
                    Toast.makeText(RegistActivity.this,"注册成功，请返回登录",Toast.LENGTH_SHORT).show();
                }
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
                String result = OKhttpUtil.postURL(URL + "user/regist",params,client);
                Log.i(TAG, "run: "+result);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}