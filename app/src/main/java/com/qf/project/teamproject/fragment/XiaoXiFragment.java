package com.qf.project.teamproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.project.teamproject.R;

/**
 *
 *
 */
public class XiaoXiFragment extends BaseFragment {
    public static final String TAG=XiaoXiFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_xiaoxi,container,false);
        return layout;
    }
}
