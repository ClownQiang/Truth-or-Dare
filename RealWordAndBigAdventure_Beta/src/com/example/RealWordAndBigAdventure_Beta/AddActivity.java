package com.example.RealWordAndBigAdventure_Beta;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.RealWordAndBigAdventure_Beta.tools.Constant;

/**
 * Created by clownqiang on 14-10-2.
 */
public class AddActivity extends Activity implements View.OnClickListener {

    private TextView textView;
    private EditText editText;
    private Button addButton;
    private int flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", Constant.BIGADVENTURE);

        initFindView();

        if (flag == Constant.BIGADVENTURE) {
            textView.setText("添加自己的大冒险行动");
        } else {
            textView.setText("添加自己的真心话问题");
        }

        addButton.setOnClickListener(this);

    }

    private void initFindView() {
        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.editText);
        addButton = (Button) findViewById(R.id.addButton);
    }

    @Override
    public void onClick(View view) {
        String addText = editText.getText().toString().trim();
        if (addText.isEmpty()) {
            Toast.makeText(AddActivity.this, "输入最好不为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("ClownQiang", addText);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                AddActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}