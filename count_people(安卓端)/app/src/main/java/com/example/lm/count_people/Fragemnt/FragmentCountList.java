package com.example.lm.count_people.Fragemnt;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.lm.count_people.R;
import com.example.lm.count_people.dto.VideoCount;
import com.example.lm.count_people.util.OKhttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;

import static com.example.lm.count_people.Activity.LoginActivity.URL;

public class FragmentCountList  extends ListFragment {


    private static final String TAG = "FragmentCountList";

    public static List<VideoCount> list;
    private ListView peopleCounts;
    private Handler handler;
    public FragmentCountList() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.count_list, container, false);
        peopleCounts =view.findViewById(android.R.id.list);



        return view;
    }

     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        new Thread(runnable).start();


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle=msg.getData();
                String res=bundle.getString("res");
                Log.i(TAG, "res: "+res);
                list=new ArrayList<>();
                Type type=new TypeToken<List<VideoCount>>(){}.getType();
                Gson gson=new Gson();
                list= gson.fromJson(res,type);
                for (VideoCount video_count :  list) {
                    Log.i(TAG, "list: "+list);
                }

                int id = 1;
                List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
                if( list != null) {
                    for ( VideoCount video_count : list) {
                        HashMap<String, Object> item = new HashMap<String, Object>();

                        item.put("id", id);
                        item.put("current_time", video_count.getCurrentTime());
                        item.put("current_people", video_count.getCurrentPeople());
                        id++;
                        data.add(item);
                        Log.i(TAG, "handleMessage: "+video_count);
                    }
                }
                //创建SimpleAdapter适配器将数据绑定到item显示控件上
                SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, R.layout.countlist_item,
                        new String[]{"id","current_time", "current_people"},
                        new int[]{R.id.count_id,R.id.current_time, R.id.peopleCounts});
                //实现列表的显示
                peopleCounts.setAdapter(adapter);

            }
        };





    }


    Runnable runnable = new Runnable() {

        final  long timeInterval = 30000;

        @Override
        public void run() {
            while(true){
                try {
                    OkHttpClient client = new OkHttpClient();
                    String result = OKhttpUtil.getURL(URL + "countpeople/countList",client);
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

}
