package com.example.RealWordAndBigAdventure_Beta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.*;
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

        bigadventure_tv = (TextView)findViewById(R.id.bigadventure_textview);
        topview = (ImageView)findViewById(R.id.bigadventure_topview);
        bottomview = (ImageView)findViewById(R.id.bigadventure_bottomview);
        relativeLayout = (RelativeLayout)findViewById(R.id.big_rl);
        shakeChange = new ShakeChange(BigAdventure.this,bigadventure_tv,2,topview,bottomview);
        textRead = new TextRead(BigAdventure.this,2);

        linearLayout = (LinearLayout)findViewById(R.id.linearlayout_bottom);

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
        /*b_backbutton = (Button)findViewById(R.id.b_backButton);
        b_sharebutton = (Button)findViewById(R.id.b_shareButton);

        b_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BigAdventure.this.finish();
            }
        });

        b_sharebutton.setOnClickListener(new View.OnClickListener() {
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