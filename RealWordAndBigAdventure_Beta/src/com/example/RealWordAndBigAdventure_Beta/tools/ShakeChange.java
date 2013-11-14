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
public class ShakeChange {
    private SensorManager sensorManager;
    private Vibrator vibrator;
    private int shakenum = 15 ;
    private static  final int SENSOR_SHAKE = 10;
    private Context context;
    private TextView textView;
    private TextRead textRead;

    public ShakeChange(Context context,TextView textView,int judgement) {
        this.context = context;
        this.textView = textView;
        textRead = new TextRead(context,judgement);
        init();
        Log.d("TAG","in ShakeChange");
    }

    private void init(){
        sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
    }

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

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];
            Log.d("TAG", "x-->" + x + "y-->" + y + "z-->" + z);
            if(Math.abs(x)>shakenum || Math.abs(y)>shakenum || Math.abs(z)>shakenum){
                vibrator.vibrate(200);
                Message msg = new Message();
                msg.what = SENSOR_SHAKE;
                handler.sendMessage(msg);
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
                    try {
                        textView.setText(textRead.LineRead());
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    Log.d("TAG", "检测到摇晃，执行操作！");
                    break;
            }
        }
    };
}
