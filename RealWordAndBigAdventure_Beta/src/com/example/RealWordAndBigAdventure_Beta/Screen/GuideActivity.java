package com.example.RealWordAndBigAdventure_Beta.Screen;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.RealWordAndBigAdventure_Beta.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daizhiqiang
 * Date: 13-11-12
 * Time: 下午11:39
 * To change this template use File | Settings | File Templates.
 */

/*根据名字可以明白，这里的是一个引导界面的Activity，实现了ViewPager.OnPageChangeListener接口，
 * 具体是什么样子，大家直接用一下就可以明白
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {

    //生成相对应的引用
    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;
    private List<View> viewList;
    //底部小点图片
    private ImageView[] dots;
    //当前下标
    private int currentIndex;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这一行可以使的我们界面没有顶部横栏，大家可以注释了跑起来看看效果
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guide);
        //我们将初始化View的函数放在了一起
        initViews();
        //初始化底部圆点的函数
        initDots();
    }

    private void initViews(){
        LayoutInflater layoutInflater = LayoutInflater.from(GuideActivity.this);
        viewList = new ArrayList<View>();
        // 初始化引导图片列表，加入的guiderview为引导界面的三个视图
        viewList.add(layoutInflater.inflate(R.layout.guiderview_1, null));
        viewList.add(layoutInflater.inflate(R.layout.guideview_2, null));
        viewList.add(layoutInflater.inflate(R.layout.guideview_3, null));

        // 初始化Adapter，将已经放入
        viewPageAdapter = new ViewPageAdapter(viewList, this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(viewPageAdapter);
        // 绑定回调
        viewPager.setOnPageChangeListener(this);
    }

    private void initDots(){
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        dots = new ImageView[viewList.size()];

        // 循环取得小点图片
        for (int i = 0; i < viewList.size(); i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);// 都设为灰色
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
    }

    private void setCurrentDot(int position) {
        if (position < 0 || position > viewList.size() - 1
                || currentIndex == position) {
            return;
        }

        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = position;
    }

    @Override
    //页面滑动时调用
    public void onPageScrolled(int i, float v, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    //页面选中时调用
    public void onPageSelected(int i) {
        setCurrentDot(i);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    //页面状态改变是调用
    public void onPageScrollStateChanged(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}