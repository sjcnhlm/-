package com.example.lm.count_people.util;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OKhttpUtil {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    /**
     * get请求URL
     *
     * @param client
     * @param url
     * @return
     * @throws IOException
     */
    public static String getURL(String url, OkHttpClient client) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    public static String postURL(String url, String json, OkHttpClient client) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}