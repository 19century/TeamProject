package com.qf.project.teamproject;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.qf.project.teamproject.ui.BaseActivity;
import com.qf.project.teamproject.ui.MainActivity;

public class SplashActivity extends BaseActivity implements Handler.Callback{

    private static final int GOMAIN = 1;
    private static final long DELAYTIME = 2*1000;
    protected ImageView splashCover;
    private Handler mHandler= new Handler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        splashCover = (ImageView) findViewById(R.id.splash_cover);
        mHandler.sendEmptyMessageDelayed(GOMAIN,DELAYTIME);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case GOMAIN:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }

        return false;
    }
}
