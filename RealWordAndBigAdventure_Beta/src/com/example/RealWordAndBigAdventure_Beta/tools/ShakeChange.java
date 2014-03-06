package com.example.RealWordAndBigAdventure_Beta.tools;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.RealWordAndBigAdventure_Beta.TextRead.TextRead;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: daizhiqiang
 * Date: 13-11-13
 * Time: 下午4:53
 * To change this template use File | Settings | File Templates.
 */

/*
 * 这里是对摇晃手机的定义的类，主要有注册重力传感器
 */
public class ShakeChange {
    private SensorManager sensorManager;
    private Vibrator vibrator;
    private int shakenum = 15 ;
    private static  final int SENSOR_SHAKE = 10;
    private Context context;
    private TextView textView;
    private TextRead textRead;
    private long NowTime,LastTime;
    private ImageView topimagview,bottomimageview;

    //构造函数
    public ShakeChange(Context context,TextView textView,int judgement,ImageView topimagview,ImageView bottomimageview) {
        this.context = context;
        this.textView = textView;
        this.topimagview = topimagview;
        this.bottomimageview = bottomimageview;
        textRead = new TextRead(context,judgement);
        init();
        Log.d("TAG","in ShakeChange");
    }

    private void init(){
        sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
    }

    //在BigAdventure与RealWord中的onresume和onpause中调用
    public void onResume() {
        if (sensorManager != null) { // 注册监听器
            sensorManager.registerListener(sensorEventListener,
                    sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void onPause() {
        if (sensorManager != null) {// 取消监听器
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    //重力传感器监听
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            //这里的NowTime和LastTime是判断两次摇晃时间的间隔，不能连续摇晃
            NowTime = System.currentTimeMillis();
            if ((NowTime - LastTime) > 70) {
                //这里是获取传感器中的数据，为x,y,z三维坐标
                float x = values[0];
                float y = values[1];
                float z = values[2];
                Log.d("TAG", "x-->" + x + "y-->" + y + "z-->" + z);
                //这里根据数据大小，判断是否达到摇晃的标准
                if (Math.abs(x) > shakenum || Math.abs(y) > shakenum || Math.abs(z) > shakenum) {
                    Message msg = new Message();
                    msg.what = SENSOR_SHAKE;
                    //这里写动画开始
                    StartAnimation();
                    sensorManager.unregisterListener(this);
                    //这里开始震动
                    vibrator.vibrate(200);
                    handler.sendMessage(msg);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                textView.setText(textRead.LineRead());
                                vibrator.cancel();
                                sensorManager.registerListener(sensorEventListener,
                                        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                                        SensorManager.SENSOR_DELAY_NORMAL);
                            } catch (IOException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }
                    }, 1000);
                }
                LastTime = NowTime;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    };


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SENSOR_SHAKE:

                    Log.d("TAG", "检测到摇晃，执行操作！");
                    break;
            }
        }
    };

    //动画效果
    public void StartAnimation(){
        AnimationSet animdown = new AnimationSet(true);
        TranslateAnimation end_translateAnimation_down = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,+0.25f);
        end_translateAnimation_down.setDuration(500);
        TranslateAnimation end_translateAnimation_up = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-0.25f);
        end_translateAnimation_up.setDuration(500);
        end_translateAnimation_up.setStartOffset(500);
        animdown.addAnimation(end_translateAnimation_down);
        animdown.addAnimation(end_translateAnimation_up);
        bottomimageview.startAnimation(animdown);

        AnimationSet animup = new AnimationSet(true);
        TranslateAnimation top_translateAnimation_up = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-0.25f);
        top_translateAnimation_up.setDuration(500);
        TranslateAnimation top_translateAnimation_down = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,+0.25f);
        top_translateAnimation_down.setDuration(500);
        top_translateAnimation_down.setStartOffset(500);
        animup.addAnimation(top_translateAnimation_up);
        animup.addAnimation(top_translateAnimation_down);
        topimagview.startAnimation(animup);
        //下面写入相应的ImageView的使用Animatin

        top_translateAnimation_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //这里加入对于图片的操作
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //结束时调用读取一行文字

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}

