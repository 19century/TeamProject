package com.qf.project.teamproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qf.project.teamproject.R;
import com.qf.project.teamproject.ViewHolder.ViewHodler;
import com.qf.project.teamproject.fragment.ShiPinFragment;

import com.qf.project.teamproject.model.ShiPinDataNew;
import com.qf.project.teamproject.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class ShiPinAdapter extends RecyclerView.Adapter<ViewHodler> implements View.OnClickListener {

    private static final String TAG = ShiPinFragment.class.getSimpleName();
    private static final int JUMP2PLAY = 0;
    private static final int SHARESDK = 5;
    private  List<ShiPinDataNew.DataBean.ListBean> data ;
    //缺少数据
    private LayoutInflater inflater;
    private RecyclerView recyclerView;
    private int dy;

    private OnShiPinItemClickListener listener;

    public void setListener(OnShiPinItemClickListener listener) {
        this.listener = listener;
    }

    public void setDy(int dy) {
        this.dy += dy;
        Log.e(TAG, "setDy: "+dy );
    }

    private Context context;
    public ShiPinAdapter(Context context, List<ShiPinDataNew.DataBean.ListBean> data) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        if (data!=null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.shipin_recyclerview_item, parent, false);
        return new ViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {

        TextView topTitle1 = (TextView) holder.getView(R.id.shipin_item_topTitle1);
        TextView topTitle2 = (TextView) holder.getView(R.id.shipin_item_topTitle2);
        ImageView image = (ImageView) holder.getView(R.id.shipin_item_image);
        TextView time = (TextView) holder.getView(R.id.shipin_item_time);
        TextView count = (TextView) holder.getView(R.id.shipin_item_count);
        ImageView touxiang = (ImageView) holder.getView(R.id.shipin_item_touxiang);
        TextView name = (TextView) holder.getView(R.id.shipin_item_name);
        TextView shareNum = (TextView) holder.getView(R.id.shipin_item_sharenum);
        TextView pinNum = (TextView) holder.getView(R.id.shipin_item_pinnum);
        ListView listView = (ListView) holder.getView(R.id.shipin_item_listview);

        RelativeLayout arraw = (RelativeLayout) holder.getView(R.id.shipin_item_updownArraw);

        TextView arrawNum = (TextView) holder.getView(R.id.shipin_item_arrawNum);
        //上箭头 需要添加监听
        ImageView upArraw = (ImageView) holder.getView(R.id.shipin_item_upArraw);
        //下箭头需要添加监听
        ImageView downArraw = (ImageView) holder.getView(R.id.shipin_item_downArraw);

        //加载数据
        ShiPinDataNew.DataBean.ListBean listBean = data.get(position);
        topTitle1.setText(listBean.getContent());
        topTitle2.setText(listBean.getTopic().getTopic());
//        time.setText();
//        count.setText();
        name.setText(listBean.getMember().getName());
//        shareNum.setText(listBean.getShare());
//        pinNum.setText();
//        arrawNum.setText();

        //加载图片

        Picasso.with(context)
                .load("http://tbfile.ixiaochuan.cn/img/view/id/"+listBean.getImgs().get(0).getId()+"/sz/480x120")
                .placeholder(R.mipmap.icon_follow_empty)
                .error(R.mipmap.icon_comment_empty)
                .into(image);
        http://tbfile.ixiaochuan.cn/account/avatar/id/1536215
        Picasso.with(context)
                .load("http://tbfile.ixiaochuan.cn/account/avatar/id/"+listBean.getMember().getAvatar())
                .placeholder(R.mipmap.icon_follow_empty)
                .error(R.mipmap.icon_comment_empty)
                .transform(new CircleTransform())
                .into(touxiang);


        //添加点击事件
        image.setTag(position);
        image.setOnClickListener(this);

        touxiang.setTag(position);
        touxiang.setOnClickListener(this);

        shareNum.setTag(position);
        shareNum.setOnClickListener(this);

        topTitle1.setTag(position);
        topTitle1.setOnClickListener(this);

        topTitle2.setTag(position);
        topTitle2.setOnClickListener(this);

        pinNum.setTag(position);
        pinNum.setOnClickListener(this);

        upArraw.setTag(position);
        upArraw.setOnClickListener(this);

        downArraw.setTag(position);
        downArraw.setOnClickListener(this);


    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }


    public void updateData(List<ShiPinDataNew.DataBean.ListBean> data) {
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addData(List<ShiPinDataNew.DataBean.ListBean> data) {
        if (data!=null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView=recyclerView;
    }

    @Override
    public void onClick(View v) {
        Integer position = (Integer) v.getTag();
        switch (v.getId()) {
            case R.id.shipin_item_image: //0
//                Integer position1 = (Integer) v.getTag();
//                Log.e(TAG, "onClick: image "+position1 );
                //跳视频播放界面
                if (listener!=null) {
                    listener.onShiPinItemClick(position,JUMP2PLAY,"http://lxqncdn.miaopai.com/stream/LCXU7zXtSLgBojQPBOCKGA__.mp4?ssig=83329c2e8a3451587fb33c0abf081dc5&time_stamp=1474533568000");
                }
                break;
            case R.id.shipin_item_topTitle1:
            case R.id.shipin_item_touxiang:
            case R.id.shipin_item_pinnum:  //1
//                Integer position2 = (Integer) v.getTag();
//                Log.e(TAG, "onClick: title1 "+position2 );
                //跳详情界面
                break;

            case R.id.shipin_item_topTitle2://2
//                Integer position3 = (Integer) v.getTag();
//                Log.e(TAG, "onClick: title2 "+position3 );
                //跳个人主页
                break;
            case R.id.shipin_item_upArraw://3
//                Integer position4 = (Integer) v.getTag();
//                Log.e(TAG, "onClick: upoarraw "+position4 );
                //点一下顶 箭头颜色变深，变深后再点跳转已经顶过的人的界面
                break;
            case R.id.shipin_item_downArraw://4
//                Integer position5 = (Integer) v.getTag();
//                Log.e(TAG, "onClick: downarraw "+position5 );
                //点一下踩 箭头颜色变深，变深后再点跳转已经踩过的人的界面
                break;

            case R.id.shipin_item_sharenum://5
//                Integer position6 = (Integer) v.getTag();
//                Log.e(TAG, "onClick: sharenum "+position6 );
                //弹出分享
                if (listener!=null) {
                    listener.onShiPinItemClick(position,SHARESDK,"");
                }
                break;

        }
    }

    public interface OnShiPinItemClickListener{
        void onShiPinItemClick(int position,int what,String data);
    }

}
