package com.qf.project.teamproject.ui;


import android.os.Handler;
import android.os.Message;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;

import com.qf.project.teamproject.R;
import com.qf.project.teamproject.fragment.FaXianFragment;
import com.qf.project.teamproject.fragment.ShiPinFragment;
import com.qf.project.teamproject.fragment.WoDeFragment;
import com.qf.project.teamproject.fragment.XiaoXiFragment;
import com.qf.project.teamproject.fragment.ZuiYouFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import cn.xiaochuankeji.netcrypto.NetCrypto;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements Handler.Callback, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Handler mHandler=new Handler(this);
    protected RadioGroup mainRadioGroup;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initURL();
        initView();
    }

    private void initView() {
        mainRadioGroup = (RadioGroup) findViewById(R.id.main_radioGroup);
        mainRadioGroup.setOnCheckedChangeListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mFragment= new ZuiYouFragment();
        transaction.add(R.id.main_frameLayout,mFragment,ZuiYouFragment.TAG);
        transaction.commit();
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
            json.put("filter", "all");
            json.put("tab", "rec");
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
            json.put("token", "57de4f00277f2858c4745464");
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_rb_zuiyou:
                switchPages(ZuiYouFragment.TAG,ZuiYouFragment.class);
                break;
            case R.id.main_rb_shipin:
                switchPages(ShiPinFragment.TAG,ShiPinFragment.class);
                break;
            case R.id.main_rb_faxian:
                switchPages(FaXianFragment.TAG,FaXianFragment.class);
                break;
            case R.id.main_rb_xiaoxi:
                switchPages(XiaoXiFragment.TAG,XiaoXiFragment.class);
                break;
            case R.id.main_rb_wode:
                switchPages(WoDeFragment.TAG,WoDeFragment.class);
                break;
        }

    }

    private void switchPages(String tag,Class<? extends android.support.v4.app.Fragment> cls) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(mFragment);
        mFragment= fm.findFragmentByTag(tag);
        if(mFragment!=null){
            transaction.show(mFragment);
        }else{

            try {
                mFragment=cls.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.main_frameLayout,mFragment,tag);
        }
        transaction.commit();


    }
}
