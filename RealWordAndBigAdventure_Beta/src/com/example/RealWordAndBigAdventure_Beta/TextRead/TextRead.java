package com.example.RealWordAndBigAdventure_Beta.TextRead;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: daizhiqiang
 * Date: 13-11-12
 * Time: 下午12:41
 * To change this template use File | Settings | File Templates.
 */
public class TextRead {

    private Context context;
    private int judgement;
    public TextRead(Context context,int judgement) {
        this.context = context;
        this.judgement = judgement;
    }

    private String readLineNumber(int lineNumber) throws IOException {
        String line;
        String s = null;
        BufferedReader br = null;
        int i = -1;
        if (judgement == 1){
            InputStream stream = context.getAssets().open("tips_realword.txt");
            br = new BufferedReader(new InputStreamReader(stream));
        }else{
            InputStream stream = context.getAssets().open("tips_bigadventure.txt");
            br = new BufferedReader(new InputStreamReader(stream));
        }
            while ((line = br.readLine()) != null) {
                i++;
                if(lineNumber == i){
                    s = line;
                }
        }
        Log.d("TAG", "s--->" + s);
        br.close();
        return s;
    }

    public String LineRead ()throws IOException {
        String s = null;
        BufferedReader br = null;
        int i = -1;
        Log.d("TAG","before FileRead");
        try {
            if (judgement == 1){
                InputStream stream = context.getAssets().open("tips_realword.txt");
                br = new BufferedReader(new InputStreamReader(stream));
            }else {
                InputStream stream = context.getAssets().open("tips_bigadventure.txt");
                br = new BufferedReader(new InputStreamReader(stream));
            }
                String line = null;
                while ((line = br.readLine()) != null) {
                    i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        int randomnum = (int) (Math.random()*i);
        Log.d("TAG","random--->"+randomnum);
        s = readLineNumber(randomnum);
        return s;
    }
}
