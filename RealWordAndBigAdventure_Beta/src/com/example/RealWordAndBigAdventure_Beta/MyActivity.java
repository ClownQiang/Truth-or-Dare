package com.example.RealWordAndBigAdventure_Beta;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.RealWordAndBigAdventure_Beta.tools.DialogCreate;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

/*
 * 这里为进入后的主界面，关于下面的注释代码大家不用管。
 */
public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private ImageView bottle,shadow;
    private Button reset_bt,realword_bt,bigadventure_bt;
    private DialogCreate dialogCreate,exit_dialog;
    private RelativeLayout relativeLayoutTop;
    private LinearLayout linearlayout_bottom;
    private boolean flag_intent = false;
    private boolean flag_bottom_bt = false;
    //位移坐标
    private static float X_b=0,X_e=-0.05f,Y_b=0,Y_e=0.04f;
    //是否在旋转
    private static boolean pic_isRotating = false;
    //角度
    private static int angle = 0,randomangle=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);


        //Umeng自动更新
        UmengUpdateAgent.update(this);

        realword_bt = (Button)findViewById(R.id.realword_bt);
        bigadventure_bt = (Button)findViewById(R.id.bigadventure_bt);
        bottle = (ImageView)findViewById(R.id.bottle);
        shadow = (ImageView)findViewById(R.id.shadow);
        relativeLayoutTop = (RelativeLayout)findViewById(R.id.relativelayout_top);
        linearlayout_bottom = (LinearLayout)findViewById(R.id.linearlayout_bottom);

        //生成两个自定义对话框(Dialog)
        dialogCreate = new DialogCreate(MyActivity.this,relativeLayoutTop,realword_bt,bigadventure_bt,linearlayout_bottom,bottle);
        exit_dialog = new DialogCreate(MyActivity.this,MyActivity.this);

        //Bottle图片旋转设置
        bottle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //当中间的酒瓶没有旋转时，才能旋转，在旋转是不能再次点击
                if (pic_isRotating == false){
                    pic_isRotating = true;
                    //随机一个旋转角度
                    randomangle = (int) (Math.random()*2430)+1020;
                    Log.d("TAG_angle", "randomangle--->" + randomangle);
                    //bottle的图片旋转
                    //从那个角度开始旋转，旋转多少度，X轴的旋转方式和值，Y轴的旋转方式和值
                    AnimationSet animationSet = new AnimationSet(true);
                    RotateAnimation rotateAnimation = new RotateAnimation(angle, randomangle,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.35f);
                    //shadow图片旋转
                    AnimationSet animationSet_s = new AnimationSet(true);
                    RotateAnimation rotateAnimation_s = new RotateAnimation(angle,randomangle,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.35f);

                    //保证下一次在旋转前一次位置旋转
                    animationSet.setFillAfter(true);
                    animationSet_s.setFillAfter(true);

                    angle = randomangle%360;
                    rotateAnimation.setDuration(randomangle+1000);
                    rotateAnimation_s.setDuration(randomangle+1000);
                    Log.d("Clownxiaoqiang","repeatCount:"+randomangle/180);

                    animationSet.addAnimation(rotateAnimation);
                    animationSet_s.addAnimation(rotateAnimation_s);

                    bottle.startAnimation(animationSet);
                    shadow.startAnimation(animationSet_s);

                    rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }

                        @Override
                        //保证旋转过程中不会受到点击影响
                        public void onAnimationEnd(Animation animation) {
                            pic_isRotating = false;
                            //flag_bottom_bt是判断点击的再来一次，还是接受挑战
                            flag_bottom_bt = dialogCreate.isFlag_islighting();
                            if(flag_bottom_bt == false){
                                dialogCreate.ShowDialog();
                            }
                            //To change body of implemented methods use File | Settings | File Templates.
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }
                    });
                }
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        //真心话按钮
        realword_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MyActivity.this,RealWord.class);
                flag_intent = true;
                MyActivity.this.startActivity(intent);
            }
        });
        //大冒险按钮
        bigadventure_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MyActivity.this,BigAdventure.class);
                flag_intent = true;
                MyActivity.this.startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        if(flag_intent == true){
            linearlayout_bottom.setVisibility(View.INVISIBLE);
            relativeLayoutTop.setBackgroundResource(R.drawable.dengguang);
            dialogCreate = new DialogCreate(MyActivity.this,relativeLayoutTop,realword_bt,bigadventure_bt,linearlayout_bottom,bottle);
            flag_intent = false;
            flag_bottom_bt = false;
        }
        MobclickAgent.onResume(this);
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
    }

    //覆写暂停函数
    @Override
    protected void onPause() {
        dialogCreate.dismiss();
        MobclickAgent.onPause(this);
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
    }

    //对点击back键作出反应
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (pic_isRotating == false){
               exit_dialog.ExitDialog();
            }
        }
        return true;    //To change body of overridden methods use File | Settings | File Templates.
    }
}
