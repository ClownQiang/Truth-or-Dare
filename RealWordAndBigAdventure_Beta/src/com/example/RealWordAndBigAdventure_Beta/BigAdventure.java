package com.example.RealWordAndBigAdventure_Beta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.*;
import cn.waps.AppConnect;
import com.example.RealWordAndBigAdventure_Beta.TextRead.TextRead;
import com.example.RealWordAndBigAdventure_Beta.tools.ShakeChange;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: daizhiqiang
 * Date: 13-11-12
 * Time: 下午1:07
 * To change this template use File | Settings | File Templates.
 */
/*
 * 进入后的大冒险界面
 */
public class BigAdventure extends Activity {

    private TextView bigadventure_tv;
    private ShakeChange shakeChange;
    private LinearLayout linearLayout;
    private ImageView topview,bottomview;
    private TextRead textRead;
    private RelativeLayout relativeLayout;
    //private Button b_backbutton,b_sharebutton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bigadventure);

        AppConnect.getInstance(this);
        bigadventure_tv = (TextView)findViewById(R.id.bigadventure_textview);
        topview = (ImageView)findViewById(R.id.bigadventure_topview);
        bottomview = (ImageView)findViewById(R.id.bigadventure_bottomview);
        relativeLayout = (RelativeLayout)findViewById(R.id.big_rl);
        shakeChange = new ShakeChange(BigAdventure.this,bigadventure_tv,2,topview,bottomview);
        textRead = new TextRead(BigAdventure.this,2);

        linearLayout = (LinearLayout)findViewById(R.id.linearlayout_bottom);

        //设置触摸监听器，在触摸时调用
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                shakeChange.StartAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            bigadventure_tv.setText(textRead.LineRead());
                        } catch (IOException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }
                },1000);
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        AppConnect.getInstance(this).initAdInfo();
        //设置迷你广告背景颜色
        AppConnect.getInstance(this).setAdBackColor(Color.argb(50, 120, 240, 120)); //设置迷你广告广告语颜色
        AppConnect.getInstance(this).setAdForeColor(Color.YELLOW); //若未设置以上两个颜色,则默认为黑底白字
        LinearLayout miniLayout =(LinearLayout)findViewById(R.id.miniAdLinearLayout);
        AppConnect.getInstance(this).showMiniAd(this, miniLayout, 10); //默认 10 秒切换一次广告
    }

    @Override
    protected void onResume() {
        shakeChange.onResume();
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onPause() {
        shakeChange.onPause();
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppConnect.getInstance(this).close();
    }
}