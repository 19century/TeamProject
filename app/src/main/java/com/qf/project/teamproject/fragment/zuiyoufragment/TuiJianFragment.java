package com.qf.project.teamproject.fragment.zuiyoufragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.xiaochuankeji.netcrypto.NetCrypto;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.qf.project.teamproject.R;
import com.qf.project.teamproject.adapter.ShiPinAdapter;
import com.qf.project.teamproject.customview.PullToRefreshRecyclerView;
import com.qf.project.teamproject.fragment.BaseFragment;
import com.qf.project.teamproject.model.ShiPinDataNew;
import com.qf.project.teamproject.ui.ShiPinPlayerActivity;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuiJianFragment extends BaseFragment implements Handler.Callback,PullToRefreshBase.OnRefreshListener2 ,ShiPinAdapter.OnShiPinItemClickListener {


    private static final int UPDATE_DATA = 0;
    private static final int ADD_DATA = 1;
    private Handler mHandler=new Handler(this);
    public static final String TAG=TuiJianFragment.class.getSimpleName();
    protected PullToRefreshRecyclerView pull2Refresh;
    protected RecyclerView recyclerView;
    protected ShiPinAdapter adapter;
    public TuiJianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout=inflater.inflate(R.layout.fragment_tui_jian, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initURL(UPDATE_DATA);
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
        adapter = new ShiPinAdapter(getActivity(),null);
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
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


    private void initURL(final int state) {
        OkHttpClient client = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
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
            json.put("token", "57e38a54277f2879292ea464");
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
                ShiPinDataNew shiPinData = gson.fromJson(string, ShiPinDataNew.class);
                ShiPinDataNew.DataBean data = shiPinData.getData();
                List<ShiPinDataNew.DataBean.ListBean> list = data.getList();
                Message msg = mHandler.obtainMessage();
                msg.obj=list;
                msg.what=state;
                mHandler.sendMessage(msg);

            }
        });

    }

    @Override
    public boolean handleMessage(Message msg) {
        List<ShiPinDataNew.DataBean.ListBean> data = (List<ShiPinDataNew.DataBean.ListBean>) msg.obj;
        switch (msg.what) {
            case UPDATE_DATA:
                adapter.updateData(data);
                break;
            case ADD_DATA:
                adapter.addData(data);
                break;
        }
        pull2Refresh.onRefreshComplete();
        return false;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        Log.e(TAG, "onPullDownToRefresh: "+"下拉刷新" );
        initURL(UPDATE_DATA);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        Log.e(TAG, "onPullUpToRefresh: "+"上拉加载" );
        initURL(ADD_DATA);
    }


    @Override
    public void onShiPinItemClick(int position,int what, String data) {

        Log.e(TAG, "onShiPinItemClick: "+"position:"+position+"data:"+data );
        switch (what) {
            case 0:
                //跳转到视频播放界面 并传网址；
                Intent intent = new Intent(getActivity(), ShiPinPlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("PLAY_URL",data);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 5://分享
                showShare();
                break;
        }

    }

    private void showShare() {
        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getActivity());
    }



}
