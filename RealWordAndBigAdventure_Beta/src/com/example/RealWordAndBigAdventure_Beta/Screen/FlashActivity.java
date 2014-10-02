package com.example.RealWordAndBigAdventure_Beta.Screen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import cn.waps.AppConnect;
import com.example.RealWordAndBigAdventure_Beta.MyActivity;
import com.example.RealWordAndBigAdventure_Beta.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;


/**
 * Created with IntelliJ IDEA.
 * User: daizhiqiang
 * Date: 13-11-12
 * Time: 下午11:09
 * To change this template use File | Settings | File Templates.
 */
/*
 *这个为Flash界面，具体就是打开应用第一个出来的展示界面
 */
public class FlashActivity extends Activity {

    //这里设置的值，final表示不懂的，请脑补java基础知识
    private boolean isFirstin = true;
    //这里的值GO_HOME,GO_GUIDE表示的是去引导界面还是直接去主界面
    private static final int GO_HOME = 199496;
    private static final int GO_GUIDE = 1019439568;
    private static final int DELAY = 3000;
    private static final String ISFIRSTIN = "first_in";

    //handle跳转页面，关于handle不懂的，请戳[http://www.cnblogs.com/plokmju/p/android_handler.html]
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    GoHome();
                    break;
                case GO_GUIDE:
                    GoGuide();
                    break;
            }
            super.handleMessage(msg);    //To change body of overridden methods use File | Settings | File Templates.
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash);
        MobclickAgent.setDebugMode(true);
        FeedbackAgent feedbackAgent = new FeedbackAgent(this);
        feedbackAgent.sync();
        init();
    }

    //这里使用了前面的SharePreference里面的值
    private void init() {
        //使用SharedPreferences保存界面信息
        SharedPreferences sharedPreference = getSharedPreferences(ISFIRSTIN, MODE_PRIVATE);
        //第一次写入
        isFirstin = sharedPreference.getBoolean("isFirstin", true);
        //这里通过判断SharedPreferences的值，判断是否进入引导界面，如果为true,表示第一次进入，如果为false表示不用进入
        //这里的Delay为延迟时间,而发送的消息为GO_HOME或者GO_GUIDE，
        if (!isFirstin) {
            //使用sendEmptyMessageDelayed，发送一个消息，前面的handleMessage进行接收，然后对message进行判断
            handler.sendEmptyMessageDelayed(GO_HOME, DELAY);
        } else {
            handler.sendEmptyMessageDelayed(GO_GUIDE, DELAY);
        }
    }

    //直接去主界面，Intent传递，关于Intent不懂的，
    //请戳[http://www.cnblogs.com/feisky/archive/2010/01/16/1649081.html]
    private void GoHome() {
        Intent intent = new Intent();
        intent.setClass(FlashActivity.this, MyActivity.class);
        FlashActivity.this.startActivity(intent);
        FlashActivity.this.finish();
    }

    //直接去引导界面，Intent传递
    private void GoGuide() {
        Intent intent = new Intent();
        intent.setClass(FlashActivity.this, GuideActivity.class);
        FlashActivity.this.startActivity(intent);
        FlashActivity.this.finish();
    }
}