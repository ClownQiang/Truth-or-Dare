package com.example.RealWordAndBigAdventure_Beta.Screen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.RealWordAndBigAdventure_Beta.MyActivity;
import com.example.RealWordAndBigAdventure_Beta.R;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daizhiqiang
 * Date: 13-11-12
 * Time: 下午11:41
 * To change this template use File | Settings | File Templates.
 */
/*
 *这里是继承了PagerAdapter的自定义的ViewPageAdapter,
 * 这个是适配器，如果不懂请戳这里[http://blog.csdn.net/wangjinyu501/article/details/8169924],
 */
public class ViewPageAdapter extends PagerAdapter {

    private List<View> views;
    private Activity activity;
    //这里是判断是否是第一次进入
    private static final String ISFIRSTIN = "first_in";

    //定义的构造函数，在GuideActivity中有调用
    public ViewPageAdapter(List<View> views,Activity activity) {
        this.views = views;
        this.activity = activity;
    }

    //删除页卡
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    // 获得当前界面数，比如现在为3个
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    // 初始化position位置的界面,来实例化页卡
    @Override
    public Object instantiateItem(View container, int position) {
        //添加也卡
        ((ViewPager) container).addView(views.get(position), 0);
        Log.d("Clownxiaoqiang","view:"+views.size());
        if (position == views.size() - 1) {
            Button button = (Button) container
                    .findViewById(R.id.startBtn);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 设置已经引导
                    setGuided();
                    goHome();

                }

            });
        }
        return views.get(position);
    }

    /*
     *这个方法是从引导界面跳转到开门动画界面
     */
    private void goHome() {
        // 跳转
        Intent intent = new Intent();
        intent.setClass(activity,DoorOpenActivity.class);
        activity.startActivity(intent);
        //这里的overridePendingTransition是设置跳转是的动画效果，fade_in,fade_out为渐入渐出
        activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        //跳转后结束掉这个Activity
        activity.finish();
    }

    /**
     *
     * method desc：设置已经引导过了，下次启动不用再次引导，
     * 这了用到了SharePreferences这个数据存储方式，不懂的请戳[http://xusaomaiss.iteye.com/blog/378524]
     * 这里将isFirstin设置为false,表示已经进来过了，下次启动不用再进入引导界面
     */
    private void setGuided() {
        SharedPreferences preferences = activity.getSharedPreferences(
                ISFIRSTIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // 存入数据
        editor.putBoolean("isFirstin", false);
        // 提交修改
        editor.commit();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    /*保存与这个适配器(Adapter)相关的任何实例状态，如果当前的UI状态需要被重建，应该恢复相关的任何实例的状态*/
    @Override
    public Parcelable saveState() {
        return null;
    }
}
