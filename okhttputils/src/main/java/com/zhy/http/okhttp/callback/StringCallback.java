package com.zhy.http.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback<String[]> {
    @Override
    public String[] parseNetworkResponse(Response response, int id) throws IOException {
        String string = response.body().string();
        String cookies = response.header("Set-Cookie");
        String[] cookie = cookies.split(";");
        String[] str = new String[]{string,cookie[0]};
        return str;
    }
}

