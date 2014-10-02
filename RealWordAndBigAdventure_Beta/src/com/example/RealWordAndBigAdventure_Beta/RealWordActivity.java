package com.example.RealWordAndBigAdventure_Beta;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;
import cn.waps.AppConnect;
import com.example.RealWordAndBigAdventure_Beta.TextRead.TextRead;
import com.example.RealWordAndBigAdventure_Beta.tools.Constant;
import com.example.RealWordAndBigAdventure_Beta.tools.ShakeChange;
import com.example.RealWordAndBigAdventure_Beta.tools.Utils;
import com.umeng.fb.FeedbackAgent;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: daizhiqiang
 * Date: 13-11-12
 * Time: 下午12:44
 * To change this template use File | Settings | File Templates.
 */

/*
 * 进入后的真心话界面
 */
public class RealWordActivity extends Activity {

    private TextView realword_tv;
    private ShakeChange shakeChange;
    private ImageView topview, bottomview;
    private TextRead textRead;
    private String showText;
    private RelativeLayout relativeLayout;
    //private Button back_bt,share_bt;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realword);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("真心话");

        AppConnect.getInstance(this);

        realword_tv = (TextView) findViewById(R.id.realword_textview);
        topview = (ImageView) findViewById(R.id.realword_topview);
        bottomview = (ImageView) findViewById(R.id.realword_bottomview);
        relativeLayout = (RelativeLayout) findViewById(R.id.real_rl);

        shakeChange = new ShakeChange(RealWordActivity.this, realword_tv, 1, topview, bottomview);
        textRead = new TextRead(RealWordActivity.this, 1);

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                shakeChange.StartAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            showText = textRead.LineRead();
                            realword_tv.setText(showText);
                        } catch (IOException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }
                }, 1000);
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        AppConnect.getInstance(this).initAdInfo();
        //设置迷你广告背景颜色
        AppConnect.getInstance(this).setAdBackColor(Color.argb(50, 120, 240, 120)); //设置迷你广告广告语颜色
        AppConnect.getInstance(this).setAdForeColor(Color.YELLOW); //若未设置以上两个颜色,则默认为黑底白字
        LinearLayout miniLayout = (LinearLayout) findViewById(R.id.miniAdLinearLayout);
        AppConnect.getInstance(this).showMiniAd(this, miniLayout, 10); //默认 10 秒切换一次广告
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                this.finish();
                return true;
            case R.id.menu_add:
                Intent intent = new Intent(this, AddActivity.class);
                intent.putExtra("flag", Constant.REALWORLD);
                startActivity(intent);
                return true;
            case R.id.menu_share:
                Utils.shareSNS(showText, this, Constant.REALWORLD);
                return true;
            case R.id.menu_feedback:
                FeedbackAgent feedbackAgent = new FeedbackAgent(this);
                feedbackAgent.startFeedbackActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_share, menu);
        return true;
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