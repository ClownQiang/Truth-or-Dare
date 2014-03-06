package com.example.RealWordAndBigAdventure_Beta.Screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.*;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.RealWordAndBigAdventure_Beta.MyActivity;
import com.example.RealWordAndBigAdventure_Beta.R;

/**
 * Created with IntelliJ IDEA.
 * User: daizhiqiang
 * Date: 13-11-16
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */

/*
 *开门动画界面，这里是对android动画的应用,关于Animation不懂，请戳[http://www.cnblogs.com/feisky/archive/2010/01/11/1644482.html]
 */
public class DoorOpenActivity extends Activity {
    private ImageView mLeft;
    private ImageView mRight;
    private TextView mText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dooropen);

        mLeft = (ImageView)findViewById(R.id.imageLeft);
        mRight = (ImageView)findViewById(R.id.imageRight);
        mText = (TextView)findViewById(R.id.anim_text);


        //这里设置了三种动画效果，分别为文字，以及两张图片
        AnimationSet anim = new AnimationSet(true);
        //这个是平移动画效果
        TranslateAnimation mytranslateanim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
        //动画效果时间为2000毫秒
        mytranslateanim.setDuration(2000);
        //这个是设置为相对于起始时间延迟800毫秒
        anim.setStartOffset(800);
        anim.addAnimation(mytranslateanim);
        //设置为保留在终止位置，否则将将在动画结束时回到最初位置，不懂的可以将这行代码注释后看看效果
        anim.setFillAfter(true);
        //启动动画效果
        mLeft.startAnimation(anim);

        AnimationSet anim1 = new AnimationSet(true);
        TranslateAnimation mytranslateanim1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,+1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
        mytranslateanim1.setDuration(1500);
        anim1.addAnimation(mytranslateanim1);
        anim1.setStartOffset(800);
        anim1.setFillAfter(true);
        mRight.startAnimation(anim1);

        AnimationSet anim2 = new AnimationSet(true);
        //这个是缩放动画效果
        ScaleAnimation myscaleanim = new ScaleAnimation(1f,3f,1f,3f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        myscaleanim.setDuration(1000);
        //这个是渐变动画效果
        AlphaAnimation myalphaanim = new AlphaAnimation(1,0.0001f);
        myalphaanim.setDuration(1500);
        anim2.addAnimation(myscaleanim);
        anim2.addAnimation(myalphaanim);
        anim2.setFillAfter(true);
        mText.startAnimation(anim2);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent (DoorOpenActivity.this,MyActivity.class);
                startActivity(intent);
                DoorOpenActivity.this.finish();
            }
        }, 2300);
    }
}