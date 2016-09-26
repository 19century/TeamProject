package com.qf.project.teamproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.project.teamproject.R;
import com.qf.project.teamproject.adapter.ZuiYouViewPagerAdapter;
import com.qf.project.teamproject.fragment.zuiyoufragment.GuanZhuFragment;
import com.qf.project.teamproject.fragment.zuiyoufragment.TuiJianFragment;

import java.util.ArrayList;

/**
 *
 *最右界面
 */
public class ZuiYouFragment extends BaseFragment {
    public static final String TAG=ZuiYouFragment.class.getSimpleName();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments;
    private String [] data;
    private ZuiYouViewPagerAdapter mPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_zuiyou,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {

        mFragments = new ArrayList<>();
        String[] tabTitle = {"推荐", "关注"};
        mFragments.add(new TuiJianFragment());//推荐页面
        mFragments.add(new GuanZhuFragment());//关注页面
        mTabLayout = (TabLayout) layout.findViewById(R.id.zuiyou_tablayout);
        mViewPager = (ViewPager) layout.findViewById(R.id.zuoyou_ViewPager);
        mPagerAdapter = new ZuiYouViewPagerAdapter(getChildFragmentManager(),mFragments,tabTitle);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }
}
