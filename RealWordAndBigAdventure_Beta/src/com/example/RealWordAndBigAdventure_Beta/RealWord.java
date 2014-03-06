package com.example.RealWordAndBigAdventure_Beta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;
import com.example.RealWordAndBigAdventure_Beta.TextRead.TextRead;
import com.example.RealWordAndBigAdventure_Beta.tools.ShakeChange;

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
public class RealWord extends Activity {

    private TextView realword_tv;
    private ShakeChange shakeChange;
    private ImageView topview,bottomview;
    private TextRead textRead;
    private RelativeLayout relativeLayout;
    //private Button back_bt,share_bt;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.realword);

        realword_tv = (TextView)findViewById(R.id.realword_textview);
        topview = (ImageView)findViewById(R.id.realword_topview);
        bottomview = (ImageView)findViewById(R.id.realword_bottomview);
        relativeLayout = (RelativeLayout)findViewById(R.id.real_rl);

        /*back_bt = (Button)findViewById(R.id.r_backButton);
        share_bt = (Button)findViewById(R.id.r_shareButton);*/
        shakeChange = new ShakeChange(RealWord.this,realword_tv,1,topview,bottomview);
        textRead = new TextRead(RealWord.this,1);

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                shakeChange.StartAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            realword_tv.setText(textRead.LineRead());
                        } catch (IOException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }
                }, 1000);
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        /*back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealWord.this.finish();
            }
        });

        share_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "请选择"));
            }
        });*/
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

}