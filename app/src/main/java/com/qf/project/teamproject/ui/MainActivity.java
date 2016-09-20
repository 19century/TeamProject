package com.qf.project.teamproject.ui;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

public class MainActivity extends BaseActivity implements Handler.Callback {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Handler mHandler=new Handler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initURL();
    }

    private void initURL() {
        OkHttpClient client = new OkHttpClient();

        Request.Builder builder = new Request.Builder();

        /*
        http://tbapi.ixiaochuan.cn/topic/attention_list?sign=08b9d08d9bcf5b98dfe6746b164eb1d8
{
	"offset": 0,
	"h_av": "2.7.7",
	"h_dt": 0,
	"h_os": 22,
	"h_model": "Google Nexus 4 - 5.1.0 - API 22 - 768x1280",
	"h_did": "000000000000000_08:00:27",
	"h_nt": 1,
	"h_m": 6784113,
	"h_ch": "qihu",
	"h_ts": 1472870764645,
	"token": "57c987f9277f283a49239622"
}

         */

        JSONObject json = new JSONObject();
        try {
            json.put("offset", 0);
            json.put("h_av", "2.7.7");
            json.put("h_dt", 0);
            json.put("h_os", Build.VERSION.SDK_INT);
            json.put("h_model", Build.MODEL);
            json.put("h_did", "AD00E00E00DEE33_08:00:27");
            json.put("h_nt", 1);
            json.put("h_m",6784113);
            json.put("h_ch", "qihu");
            json.put("h_ts", System.currentTimeMillis());
            json.put("token", "57c987f9277f283a49239622");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://tbapi.ixiaochuan.cn/topic/attention_list";

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
                System.out.println("string = " + string);
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
