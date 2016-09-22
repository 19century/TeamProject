package com.qf.project.teamproject.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.qf.project.teamproject.R;
import com.qf.project.teamproject.adapter.ShiPinAdapter;
import com.qf.project.teamproject.customview.PullToRefreshRecyclerView;
import com.qf.project.teamproject.model.FakeData;
import com.qf.project.teamproject.model.ShiPinData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class ShiPinFragment extends BaseFragment implements Handler.Callback,PullToRefreshBase.OnRefreshListener2 {

    private Handler mHandler=new Handler(this);
    public static final String TAG=ShiPinFragment.class.getSimpleName();
    protected PullToRefreshRecyclerView pull2Refresh;
    protected RecyclerView recyclerView;
    protected ShiPinAdapter adapter;


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
        initView();
    }

    private void initView() {
        pull2Refresh = ((PullToRefreshRecyclerView) layout.findViewById(R.id.pull2refresh_shipin));
        pull2Refresh.setMode(PullToRefreshBase.Mode.BOTH);
        pull2Refresh.setOnRefreshListener(this);

        //设置布局文件
        recyclerView = pull2Refresh.getRefreshableView();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //设置适配器
        //造假数据
//        final List<FakeData> data=new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            FakeData fakeData = new FakeData();
//            fakeData.setArrawNum("点赞"+i);
//            fakeData.setCount("数量"+i);
//            fakeData.setName("张三"+i);
//            fakeData.setPinNum("评价"+i);
//            fakeData.setShareNum("分享数"+i);
//            fakeData.setTime("time"+i);
//            fakeData.setTitle1("大标题"+i);
//            fakeData.setTitle2("小标题"+i);
//            fakeData.setTouxiang("http://img0.imgtn.bdimg.com/it/u=3468736256,2070128096&fm=21&gp=0.jpg");
//            fakeData.setImage("http://img6.faloo.com/Picture/0x0/0/183/183390.jpg");
//            data.add(fakeData);
//        }
        adapter = new ShiPinAdapter(getActivity(),null);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e(TAG, "onScrollStateChanged: "+"newState"+newState );
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Log.e(TAG, "onScrolled: "+"dx:"+dx+"dy:"+dy +Thread.currentThread().getName());
                adapter.setDy(dy);
            }
        });


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
            json.put("filter", "all");
            json.put("tab", "rec");
            json.put("direction", "down");
            json.put("h_av", "2.7.7");
            json.put("h_dt", 0);
            json.put("h_os", 22);
            json.put("h_model", "Google Nexus 4 - 5.1.0 - API 22 - 768x1280");
            json.put("h_did", "000000000000000_08:00:27");
            json.put("h_nt", 1);
            json.put("h_m", 6784113);
            json.put("h_ch", "qihu");
            json.put("h_ts", System.currentTimeMillis());
            json.put("token", "57e24137277f285b6f140aee");
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
                Gson gson = new Gson();
                ShiPinData shiPinData = gson.fromJson(string, ShiPinData.class);
                ShiPinData.DataBean data = shiPinData.getData();
                List<ShiPinData.DataBean.ListBean> list = data.getList();
                Message msg = mHandler.obtainMessage();
                msg.obj=list;
                mHandler.sendMessage(msg);

            }
        });

    }

    @Override
    public boolean handleMessage(Message msg) {
        List<ShiPinData.DataBean.ListBean> data = (List<ShiPinData.DataBean.ListBean>) msg.obj;
        adapter.addData(data);
        return false;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        Log.e(TAG, "onPullDownToRefresh: "+"下拉刷新" );
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        Log.e(TAG, "onPullUpToRefresh: "+"上拉加载" );
    }



}
