package com.example.lm.count_people.Fragemnt;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.lm.count_people.Activity.LoginActivity;
import com.example.lm.count_people.R;
import com.example.lm.count_people.dto.Sensor;
import com.example.lm.count_people.util.OKhttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;

import okhttp3.OkHttpClient;



public class FragmentTemperControl  extends Fragment {

    private static final String TAG ="FragmentTemperControl" ;
    private TextView temp = null;
    private Sensor sensor;
    private Handler handler;

    private ToggleButton fengshan;
    public FragmentTemperControl() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.temper_control, container, false);
        temp = view.findViewById(R.id.temp);
        fengshan = view.findViewById(R.id.fengshan);
        fengshan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(runnable1).start();
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(runnable).start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String res = bundle.getString("res");
                Log.i(TAG, "res: " + res);

                Type type = new TypeToken<Sensor>() {
                }.getType();
                Gson gson = new Gson();
                sensor = gson.fromJson(res, type);
                Log.i(TAG, "Seneor: " + sensor);


                float tempure = sensor.getTemp();

                Log.i(TAG, "Seneor温度值: " + tempure);
                String show_tempure = tempure + "度";
                temp.setText(show_tempure);
            }

        };

    }


    Runnable runnable = new Runnable() {

        final  long timeInterval = 1000;

        @Override
        public void run() {
            while(true){
                try {
                    OkHttpClient client = new OkHttpClient();
                    String result = OKhttpUtil.getURL(LoginActivity.URL + "sensor/lastsensorInfo",client);
                    Message msg = new Message();
                    Bundle bundle=new Bundle();
                    bundle.putString("res",result);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                    Log.i(TAG, "onClick: "+result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try{
                    Thread.sleep(timeInterval);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    };


    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            int count = 0;

            Log.i(TAG, "run: --------------------"+fengshan.getText());
            if(fengshan.getText().equals("开启")){
                count = count + 2;
                Log.i(TAG, "run: 第一个按钮状态"+fengshan.isClickable());
            }


            OkHttpClient client = new OkHttpClient();
            try {
                OKhttpUtil.getURL(LoginActivity.URL + "/actator/controlLight?state="+count, client);
                Log.i(TAG, "run: --------"+count);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
