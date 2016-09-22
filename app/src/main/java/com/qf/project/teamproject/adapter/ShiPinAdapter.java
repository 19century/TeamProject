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
import com.qf.project.teamproject.model.FakeData;
import com.qf.project.teamproject.model.ShiPinData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class ShiPinAdapter extends RecyclerView.Adapter<ViewHodler> {

    private static final String TAG = ShiPinFragment.class.getSimpleName();
    private  List<ShiPinData.DataBean.ListBean> data ;
    //缺少数据
    private LayoutInflater inflater;

    private int dy;

    public void setDy(int dy) {
        this.dy += dy;
        Log.e(TAG, "setDy: "+dy );
    }

    private Context context;
    public ShiPinAdapter(Context context, List<ShiPinData.DataBean.ListBean> data) {
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
        ShiPinData.DataBean.ListBean listBean = data.get(position);
        topTitle1.setText(listBean.getContent());
        topTitle2.setText(listBean.getTopic().getTopic());
//        time.setText();
//        count.setText();
        name.setText(listBean.getMember().getName());
        shareNum.setText(listBean.getShare());
//        pinNum.setText();
//        arrawNum.setText();

        //加载图片
//        Picasso.with(context)
//                .load(data.get(position).getImage())
//                .placeholder(R.mipmap.icon_follow_empty)
//                .error(R.mipmap.icon_comment_empty)
//                .into(image);
//        Picasso.with(context)
//                .load(data.get(position).getTouxiang())
//                .placeholder(R.mipmap.icon_follow_empty)
//                .error(R.mipmap.icon_comment_empty)
//                .into(touxiang);


    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }


    public void addData(List<ShiPinData.DataBean.ListBean> data) {
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
}
