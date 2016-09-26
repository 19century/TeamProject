package com.qf.project.teamproject.fragment.zuiyoufragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.project.teamproject.R;
import com.qf.project.teamproject.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuanZhuFragment extends BaseFragment {


    public GuanZhuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_guan_zhu, container, false);
        return layout;
    }

}
