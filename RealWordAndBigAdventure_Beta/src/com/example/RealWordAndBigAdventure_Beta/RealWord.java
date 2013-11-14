package com.example.RealWordAndBigAdventure_Beta;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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
public class RealWord extends Activity {

    private TextView realword_tv;
    private ShakeChange shakeChange;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.realword);

        realword_tv = (TextView)findViewById(R.id.realword_textview);
        shakeChange = new ShakeChange(RealWord.this,realword_tv,1);

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