package com.qf.project.teamproject.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

import com.qf.project.teamproject.R;
import com.qf.project.teamproject.utils.CommonUtil;
import com.qf.project.teamproject.utils.LightController;
import com.qf.project.teamproject.utils.VolumeController;
import com.qf.project.teamproject.widget.CustomVideoView;


public class ShiPinPlayerActivity extends AppCompatActivity implements Handler.Callback, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener {

    private static final int UPDATE_PROGRESS = 100;
    private static final String TAG = ShiPinPlayerActivity.class.getSimpleName();
    private CustomVideoView mVideoView;
    private MediaController controller;
    private CheckBox mPlay;
    private Handler mHandler;
    private TextView mCurrent;
    private SeekBar mProgress;
    private TextView mTotal;
    private CheckBox mFullScreen;
    private int oldHeight;
    /**
     * 横竖屏的标记
     */
    private boolean isLandscape;

    //刚按下时的x的位置
    private float startX;
    //刚按下时的y的位置
    private float startY;
    //最近一次的x的位置
    private float mLastX;
    //最近一次的y的位置
    private float mLastY;

    //滑动的临界值
    private int threshold = 10;

    //屏幕高度
    private int mScreenHeight;
    protected int mScreenWidth;
    protected View mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_pin_player);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String play_url = extras.getString("PLAY_URL");

        mVideoView = (CustomVideoView) findViewById(R.id.teach_video);
        // 设置触摸的监听
        mVideoView.setOnTouchListener(this);
        // 设置播放的路径
        mVideoView.setVideoPath(play_url);
//        mVideoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.dance);
        // 添加媒体控制
//        controller = new MediaController(this);
//        mVideoView.setMediaController(controller);
        // 播放
//        mVideoView.start();

        mHandler = new Handler(this);

        mPlay = (CheckBox) findViewById(R.id.teach_video_controller_play);
        mPlay.setOnCheckedChangeListener(this);
        mCurrent = (TextView) findViewById(R.id.teach_video_controller_current);

        mProgress = (SeekBar) findViewById(R.id.teach_video_controller_progress);
        mProgress.setOnSeekBarChangeListener(this);
        mTotal = (TextView) findViewById(R.id.teach_video_controller_total);

        mFullScreen = (CheckBox) findViewById(R.id.teach_video_controller_fullscreen);
        mFullScreen.setOnCheckedChangeListener(this);

        //获取屏幕宽高
        mScreenHeight=getResources().getDisplayMetrics().heightPixels;
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;

        //控制条
        mController = findViewById(R.id.teach_video_controller);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.teach_video_controller_play:
                if (isChecked) {
                    mVideoView.start();
                    // 发送一个消息让Handler通知 进度开始更新
                    mHandler.sendEmptyMessage(UPDATE_PROGRESS);
                    // 获取总的时间
                    int duration = mVideoView.getDuration();
                    // 将时长作为进度条的最大值
                    mProgress.setMax(duration);
                    // 设置总的时长
                    mTotal.setText(CommonUtil.parseTime(duration));
                } else {
                    mVideoView.pause();
                    // 将更新进度的消息移除
                    mHandler.removeMessages(UPDATE_PROGRESS);
                }
                break;
            case R.id.teach_video_controller_fullscreen:
                if (isChecked) {
                    // 记录旋转前 VideoView的高度
                    oldHeight = mVideoView.getHeight();
                    // 切换全屏
                    // 添加全屏标记
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    // 旋转屏幕 横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    ViewGroup.LayoutParams layoutParams = mVideoView.getLayoutParams();
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    mVideoView.setLayoutParams(layoutParams);
                } else {
                    // 旋转回来
                    // 清楚全屏标记
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    // 旋转屏幕为竖屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    // 设置高度
                    ViewGroup.LayoutParams params = mVideoView.getLayoutParams();
                    params.height = oldHeight;
                    mVideoView.setLayoutParams(params);
                }
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case UPDATE_PROGRESS:
                // 获取当前的播放进度
                int currentPosition = mVideoView.getCurrentPosition();
                // 将当前进度进行格式转换
                mCurrent.setText(CommonUtil.parseTime(currentPosition));
                // 将当前进度设置到SeekBar上
                mProgress.setProgress(currentPosition);
                mHandler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 1000);
                break;
        }
        return false;
    }

    /**
     * 当进度条改变的时候调用
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // 来自用户的改变  将播放器暂停
//        if (fromUser) {
//            mVideoView.pause();
//            mHandler.removeMessages(UPDATE_PROGRESS);
//        }
        if (fromUser) {
            mVideoView.seekTo(progress);
            mCurrent.setText(CommonUtil.parseTime(progress));
        }
    }

    /**
     * 当触摸到SeekBar的时候就会调用
     *
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * 当触摸结束，离开的时候调用
     *
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int progress = seekBar.getProgress();
        // 将VideoView的进度滑动到指定位置
        mVideoView.seekTo(progress);
        // 进度改变之后播放
//        mVideoView.start();
//        mHandler.sendEmptyMessage(UPDATE_PROGRESS);
//        mPlay.setChecked(true);
    }

    /**
     * 屏幕的配置参数发生改变的时候调用 屏幕旋转
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 竖屏
            Log.e(TAG, "onConfigurationChanged: 竖屏");
            isLandscape = false;
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 横屏
            Log.e(TAG, "onConfigurationChanged: 横屏");
            isLandscape = true;
        }
        super.onConfigurationChanged(newConfig);
    }

    // 触摸事件的回调函数
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //横屏
                if (isLandscape) {
                    float xDelta = x - mLastX;
                    float yDelta = y - mLastY;

                    if (Math.abs(xDelta)>Math.abs(yDelta)){
                        //x 进行处理
                        if (Math.abs(xDelta)>threshold) {
                            //滑动快进快退
                            if (xDelta>0) {
                                //快进
                                Log.e(TAG, "onTouch: "+"快进" );
                                goForward(xDelta);
                            }else {
                                //回退
                                Log.e(TAG, "onTouch: "+"回退" );
                                goBack(xDelta);
                            }
                        }

                    }else {
                        //y 处理
                        if (Math.abs(yDelta)>threshold) {
                            if (x>mScreenHeight/2) {
                                //控制音量
                                Log.e(TAG, "onTouch: "+"控制音量" );
                                if (yDelta>0) {
                                    //减小音量
                                    Log.e(TAG, "onTouch: "+"减小音量" );
                                    VolumeController.volumeDown(this,yDelta,mScreenWidth);
                                }else {
                                    //音量加
                                    Log.e(TAG, "onTouch: "+"增加音量" );
                                    VolumeController.volumeUp(this,yDelta,mScreenWidth);
                                }
                            }else {
                                //控制亮度
                                Log.e(TAG, "onTouch: "+"控制亮度" );
                                if (yDelta>0) {
                                    //亮度减小
                                    Log.e(TAG, "onTouch: "+"亮度减小" );
                                    LightController.lightDown(this,yDelta,mScreenWidth);
                                }else {
                                    //亮度加
                                    Log.e(TAG, "onTouch: "+"亮度加" );
                                    LightController.lightUp(this,yDelta,mScreenWidth);
                                }
                            }
                        }

                    }
                }
                mLastX=x;
                mLastY=y;
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(x-startX)<threshold&&Math.abs(y-startY)<threshold) {
                    showOrHideCntroller();
                }

                break;
        }

        return true;
    }

    private void showOrHideCntroller() {
        if (mController.getVisibility()== View.VISIBLE) {
            mController.setVisibility(View.GONE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.exit);
            mController.startAnimation(animation);
        }else if(mController.getVisibility()==View.GONE){
            mController.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.enter);
            mController.startAnimation(animation);
        }
    }


    /**
     * 快进
     * @param xDelta>0
     */
    private void goForward(float xDelta) {
        //获取当前进度
        int currentPosition = mVideoView.getCurrentPosition();
        //获取总的时长
        int duration = mVideoView.getDuration();

        double add = 0.3 * xDelta * duration / mScreenHeight;
        //变化后的值
        double changed = Math.min(add + currentPosition, duration);
        //设置当前进度
        mVideoView.seekTo(((int) changed));
    }

    /**
     * 快退
     * @param xDelta
     */
    private void goBack(float xDelta) {
        int currentPosition = mVideoView.getCurrentPosition();
        int duration = mVideoView.getDuration();
        double dec=0.3*xDelta*duration/mScreenHeight;
        double changed=Math.max(currentPosition+dec,0);
        mVideoView.seekTo(((int) changed));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK) {
            if (isLandscape) {
//                // 旋转回来
//                // 清楚全屏标记
//                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//                // 旋转屏幕为竖屏
//                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                // 设置高度
//                ViewGroup.LayoutParams params = mVideoView.getLayoutParams();
//                params.height = oldHeight;
//                mVideoView.setLayoutParams(params);
                mFullScreen.setChecked(false);

                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
