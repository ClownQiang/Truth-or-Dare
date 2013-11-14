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

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private ImageView bottle,shadow;
    private Button reset_bt,realword_bt,bigadventure_bt;
    private DialogCreate dialogCreate;
    private RelativeLayout relativeLayoutTop;
    private LinearLayout linearlayout_bottom;
    //位移坐标
    private static float X_b=0,X_e=-0.05f,Y_b=0,Y_e=0.04f;
    //是否在旋转
    private static boolean pic_isRotating = false;
    //private static boolean reset_IsClicking = false;
    private static boolean shadow_isRotating = false;
    //角度
    private static int angle = 0,randomangle=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);


        //reset_bt = (Button)findViewById(R.id.reset);
        realword_bt = (Button)findViewById(R.id.realword_bt);
        bigadventure_bt = (Button)findViewById(R.id.bigadventure_bt);
        bottle = (ImageView)findViewById(R.id.bottle);
        shadow = (ImageView)findViewById(R.id.shadow);
        relativeLayoutTop = (RelativeLayout)findViewById(R.id.relativelayout_top);
        linearlayout_bottom = (LinearLayout)findViewById(R.id.linearlayout_bottom);

        dialogCreate = new DialogCreate(MyActivity.this,relativeLayoutTop,realword_bt,bigadventure_bt,linearlayout_bottom);

        //Bottle图片旋转设置
        bottle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (pic_isRotating == false){
                    pic_isRotating = true;
                    randomangle = (int) (Math.random()*3600)+1000;
                    Log.d("TAG_angle", "randomangle--->" + randomangle);
                    //bottle的图片旋转
                    //从那个角度开始旋转，旋转多少度，X轴的旋转方式和值，Y轴的旋转方式和值
                    AnimationSet animationSet = new AnimationSet(true);
                    RotateAnimation rotateAnimation = new RotateAnimation(angle, randomangle,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.35f);
                    //shadow图片旋转
                    AnimationSet animationSet_s = new AnimationSet(true);
                    //阴影变化
                    TranslateAnimation translateAnimation_b = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF,X_b,Animation.RELATIVE_TO_SELF,X_e,
                            Animation.RELATIVE_TO_SELF,Y_b,Animation.RELATIVE_TO_SELF,Y_e);
                    RotateAnimation rotateAnimation_s = new RotateAnimation(angle,randomangle,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.35f);

                    //保证下一次在旋转前一次位置旋转
                    animationSet.setFillAfter(true);
                    animationSet_s.setFillAfter(true);
                    //加速度设置为匀速
                    //LinearInterpolator linearInterpolator = new LinearInterpolator();

                    angle = randomangle%360;
                    rotateAnimation.setDuration(randomangle*3);
                    rotateAnimation_s.setDuration(randomangle*3);
                    /*rotateAnimation.setInterpolator(linearInterpolator);
                    rotateAnimation_s.setInterpolator(linearInterpolator);*/
                    translateAnimation_b.setDuration(180*3);
                    translateAnimation_b.setRepeatCount(randomangle/180-3);
                    Log.d("Clownxiaoqiang","repeatCount:"+randomangle/180);
                    translateAnimation_b.setRepeatMode(Animation.REVERSE);

                    animationSet.addAnimation(rotateAnimation);
                    animationSet_s.addAnimation(rotateAnimation_s);
                    animationSet_s.addAnimation(translateAnimation_b);

                    bottle.startAnimation(animationSet);
                    shadow.startAnimation(animationSet_s);

                    translateAnimation_b.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Log.d("Clownxiaoqiang","in the animation end");
                            if ((((randomangle/180))%2) != 0){
                                if(X_b==-0.05f){
                                    X_b = 0;
                                    X_e = -0.05f;
                                    Y_b = 0;
                                    Y_e = 0.04f;
                                }else{
                                    Log.d("Clownxiaoqiang",(randomangle/180)%2+"");
                                    X_b = -0.05f;
                                    X_e = 0;
                                    Y_b = 0.04f;
                                    Y_e = 0;
                                }
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }
                    });
                    rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }

                        @Override
                        //保证旋转过程中不会受到点击影响
                        public void onAnimationEnd(Animation animation) {
                            pic_isRotating = false;
                            dialogCreate.ShowDialog();
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

        //重置按钮
       /* reset_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reset_IsClicking == false){
                    reset_IsClicking = true;
                    AnimationSet animationSet = new AnimationSet(true);
                    RotateAnimation rotateAnimation = new RotateAnimation(angle, 360,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.35f);
                    //从那个角度开始旋转，旋转多少度，X轴的旋转方式和值，Y轴的旋转方式和值
                    animationSet.setFillAfter(true);
                    //保证下一次在旋转前一次位置旋转
                    rotateAnimation.setDuration(angle+1000);
                    animationSet.addAnimation(rotateAnimation);
                    bottle.startAnimation(animationSet);
                    shadow.startAnimation(animationSet);
                    rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            angle = 0;
                            reset_IsClicking = false;
                            //To change body of implemented methods use File | Settings | File Templates.
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }
                    });
                }
            }
        });*/

        //真心话按钮
        realword_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MyActivity.this,RealWord.class);
                MyActivity.this.startActivity(intent);
            }
        });

        bigadventure_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MyActivity.this,BigAdventure.class);
                MyActivity.this.startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        linearlayout_bottom.setVisibility(View.INVISIBLE);
        relativeLayoutTop.setBackgroundResource(R.drawable.dengguang);
        Log.d("Clownxiaoqiang","in onResume");
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog alertDialog = new AlertDialog.Builder(MyActivity.this).
            setTitle("提示！！").
            setMessage("再玩一会儿吧？:)").
            setPositiveButton("好的！",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).
            setNegativeButton("不了，亲！",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    angle = 0;
                    MyActivity.this.finish();
                }
            }).create();
            alertDialog.show();
        }
        return super.onKeyDown(keyCode, event);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
