package com.example.RealWordAndBigAdventure_Beta.Screen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import com.example.RealWordAndBigAdventure_Beta.MyActivity;
import com.example.RealWordAndBigAdventure_Beta.R;


/**
 * Created with IntelliJ IDEA.
 * User: daizhiqiang
 * Date: 13-11-12
 * Time: 下午11:09
 * To change this template use File | Settings | File Templates.
 */
public class FlashActivity extends Activity {

    private boolean isFirstin = true;
    private static final int GO_HOME = 199496;
    private static final int GO_GUIDE = 1019439568;
    private static final int DELAY = 3000;
    private static final String ISFIRSTIN = "first_in";

    //handle跳转页面
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
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

        init();
    }

    private void init(){
        //使用SharedPreferences保存界面信息
        SharedPreferences sharedPreference = getSharedPreferences(ISFIRSTIN,MODE_PRIVATE);
        //第一次写入
        isFirstin = sharedPreference.getBoolean("isFirstin",true);
        if(!isFirstin){
            handler.sendEmptyMessageDelayed(GO_HOME, DELAY);
        }else{
            handler.sendEmptyMessageDelayed(GO_GUIDE, DELAY);
        }
    }
    private void GoHome(){
        Intent intent = new Intent();
        intent.setClass(FlashActivity.this, MyActivity.class);
        FlashActivity.this.startActivity(intent);
        FlashActivity.this.finish();
    }

    private void GoGuide(){
        Intent intent = new Intent();
        intent.setClass(FlashActivity.this,GuideActivity.class);
        FlashActivity.this.startActivity(intent);
        FlashActivity.this.finish();
    }
}