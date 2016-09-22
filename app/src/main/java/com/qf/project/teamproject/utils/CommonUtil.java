package com.qf.project.teamproject.utils;

import android.text.format.DateFormat;

/**
 * Created by Rock on 2016/9/7.
 */
public class CommonUtil {
    /**
     * 时间转换
     * @param position  当前播放的时间  默认毫秒
     * @return
     */
    public static CharSequence parseTime(int position){
        return DateFormat.format("mm:ss", position);
    }

}
