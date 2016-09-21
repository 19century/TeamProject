package com.qf.project.teamproject.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.project.teamproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.xiaochuankeji.netcrypto.NetCrypto;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 *
 */
public class ShiPinFragment extends BaseFragment implements Handler.Callback {

    private Handler mHandler=new Handler(this);
    public static final String TAG=ShiPinFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_shipin,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initURL();
    }
    private void initURL() {
        OkHttpClient client = new OkHttpClient();

        Request.Builder builder = new Request.Builder();

        /*
        http://tbapi.ixiaochuan.cn/index/recommend?sign=73ccc6bafae5292bf4f3c6f885aa80a9
{
"offset": 0,
"filter": "video",
"tab": "video",
"direction": "down",
"h_av": "2.7.7",
"h_dt": 0,
"h_os": 22,
"h_model": "Google Nexus 4 - 5.1.0 - API 22 - 768x1280",
"h_did": "000000000000000_08:00:27",
"h_nt": 1,
"h_m": 6784113,
"h_ch": "qihu",
"h_ts": 1472869365440,
"token": "57c987f9277f283a49239622"
}

         */

        JSONObject json = new JSONObject();
        try {
            json.put("offset", 0);
            json.put("filter", "video");
            json.put("tab", "video");
            json.put("direction", "down");
            json.put("h_av", "2.7.7");
            json.put("h_dt", 0);
            //  json.put("h_os", Build.VERSION.SDK_INT);
            json.put("h_os", 22);
            // json.put("h_model", Build.MODEL);
            json.put("h_model", "Google Nexus 4 - 5.1.0 - API 22 - 768x1280");
            // json.put("h_did", "AD00E00E00DEE33_08:00:27");
            json.put("h_did", "000000000000000_08:00:27");
            json.put("h_nt", 1);
            json.put("h_m", 6784113);
            json.put("h_ch", "qihu");
            json.put("h_ts", System.currentTimeMillis());
            // json.put("token", "57c987f9277f283a49239622");
            json.put("token", "57c987f9277f283a49239622");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://tbapi.ixiaochuan.cn/index/recommend";

        url = NetCrypto.getRequestUrl(url, json);

        System.out.println("url = " + url);

        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), json.toString());

        final Request request = builder.url(url).post(body).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: " +Thread.currentThread().getName());//不是主线程
                String string = response.body().string();
//                System.out.println("string = " + string);
                Log.e(TAG, "onResponse: "+string );
                Message msg = mHandler.obtainMessage();
                msg.obj=string;
                mHandler.sendMessage(msg);

            }
        });

    }

    @Override
    public boolean handleMessage(Message msg) {
        String url = (String) msg.obj;
        return false;
    }
}
