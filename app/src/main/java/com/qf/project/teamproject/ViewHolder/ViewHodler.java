package com.qf.project.teamproject.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/9/21.
 */
public class ViewHodler extends RecyclerView.ViewHolder {

    private HashMap<Integer,View> mCaChe;

    public ViewHodler(View itemView) {
        super(itemView);
        mCaChe=new HashMap<>();
    }

    public View getView(int resId){
        View view = null;
        if (mCaChe.containsKey(resId)) {
            view = mCaChe.get(resId);
        }else{
            view = itemView.findViewById(resId);
            mCaChe.put(resId,view);
        }
        return view;

    }
}
