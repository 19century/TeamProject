package com.qf.project.teamproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by XCQ on 16/9/22.
 */
public class ZuiYouViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> data;
    private String[] tabTitle;
    public ZuiYouViewPagerAdapter(FragmentManager fm,List<Fragment> data,String[] tabTitle) {
        super(fm);
        if (data!=null) {
            this.data=data;
        }
        this.tabTitle=tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;

    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
