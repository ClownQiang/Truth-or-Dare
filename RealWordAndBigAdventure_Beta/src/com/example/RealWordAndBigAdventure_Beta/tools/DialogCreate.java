package com.example.RealWordAndBigAdventure_Beta.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.RealWordAndBigAdventure_Beta.R;

/**
 * Created with IntelliJ IDEA.
 * User: daizhiqiang
 * Date: 13-11-12
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
public class DialogCreate extends AlertDialog{

    private Context context;
    private Activity activity;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private Button rotateagain,comeon,yes_bt,no_bt;
    private Button realword_bt,bigadventure_bt;
    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;
    private ImageView imageView;
    private boolean flag_islighting = false;

    public DialogCreate(Context context,Activity activity) {
        super(context);
        this.context = context;
        this.activity = activity;
    }

    public DialogCreate(Context context,RelativeLayout relativeLayout,Button realword_bt,Button bigadventure_bt,
                        LinearLayout linearLayout,ImageView imageView) {
        super(context);
        this.context = context;
        this.relativeLayout = relativeLayout;
        this.realword_bt = realword_bt;
        this.bigadventure_bt = bigadventure_bt;
        this.linearLayout = linearLayout;
        this.imageView = imageView;
    }

    public void ShowDialog(){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog,null);
        //这里可以写对控件的使用
        rotateagain = (Button)layout.findViewById(R.id.rotateagain);
        comeon = (Button)layout.findViewById(R.id.comeon);

        rotateagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_islighting = false;
                alertDialog.dismiss();
            }
        });

        comeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackgroundResource(R.drawable.anniudg);
                linearLayout.setVisibility(View.VISIBLE);
                flag_islighting =true;
                alertDialog.dismiss();
            }
        });
        builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        Window dialogWindow = alertDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = 250;
        lp.height = 300;
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.BOTTOM);
        alertDialog.show();
    }

    public void ExitDialog(){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.exit_dialog,null);

        yes_bt = (Button)layout.findViewById(R.id.yes_bt);
        no_bt = (Button)layout.findViewById(R.id.no_bt);

        yes_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });

        no_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        alertDialog = builder.create();
        Window dialogWindow = alertDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = 330;
        lp.height = 230;
        dialogWindow.setAttributes(lp);
        alertDialog.show();
    }

    public boolean isFlag_islighting() {
        return flag_islighting;
    }
}
