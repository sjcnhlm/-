package com.example.lm.count_people.Fragemnt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.lm.count_people.Activity.LoginActivity;
import com.example.lm.count_people.Activity.MainActivity;
import com.example.lm.count_people.R;
import com.example.lm.count_people.util.OKhttpUtil;

import java.io.IOException;

import okhttp3.OkHttpClient;

public class FragmentLightControl  extends Fragment {
    private ToggleButton Illumination_fg = null;
    private static final String TAG = "FragmentLightControl";
    public FragmentLightControl() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.light_control, container, false);

        Illumination_fg = view.findViewById(R.id.Illumination_fg);
        Illumination_fg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(runnable).start();
            }
        });
        return view;
    }





    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int count = 0;

            Log.i(TAG, "run: --------------------"+Illumination_fg.getText());
            if(Illumination_fg.getText().equals("开启")){
                count = count + 1;
                Log.i(TAG, "run: 第一个按钮状态"+Illumination_fg.isClickable());
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
