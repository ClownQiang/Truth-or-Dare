package com.example.RealWordAndBigAdventure_Beta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.RealWordAndBigAdventure_Beta.tools.Constant;

/**
 * Created by clownqiang on 14-10-2.
 */
public class AddActivity extends Activity {

    private int flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", Constant.BIGADVENTURE);

        if (flag == Constant.BIGADVENTURE) {
            Toast.makeText(this, "大冒险", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "真心话", Toast.LENGTH_SHORT).show();
        }

    }
}