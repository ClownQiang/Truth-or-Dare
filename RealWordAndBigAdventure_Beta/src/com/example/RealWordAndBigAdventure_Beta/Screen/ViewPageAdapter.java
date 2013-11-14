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
public class ViewPageAdapter extends PagerAdapter {

    private List<View> views;
    private Activity activity;
    private static final String ISFIRSTIN = "first_in";

    public ViewPageAdapter(List<View> views,Activity activity) {
        this.views = views;
        this.activity = activity;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }

    // 获得当前界面数
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    // 初始化arg1位置的界面
    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(views.get(arg1), 0);
        Log.d("Clownxiaoqiang","view:"+views.size());
        if (arg1 == views.size() - 1) {
            Button button = (Button) arg0
                    .findViewById(R.id.start);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 设置已经引导
                    setGuided();
                    goHome();

                }

            });
        }
        return views.get(arg1);
    }

    private void goHome() {
        // 跳转
        Intent intent = new Intent(activity, MyActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     *
     * method desc：设置已经引导过了，下次启动不用再次引导
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

    @Override
    public Parcelable saveState() {
        return null;
    }
}
