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

/*
 * 这个是利用了java的文件读取，因为利用数据库存储数据可能不是很方便，所以将文件存在文本中，然后一条一条读取，这个大家可以作为以后
 * 开发的一点小点子，文件存在assets，分别为真心话和大冒险的文件，大家可以自己添加。。。
 * java文件读取，请戳[http://blog.csdn.net/smartcat86/article/details/4085739]
 * java在android中的读取[http://blog.csdn.net/ztp800201/article/details/7322110]
 */
public class TextRead {

    private Context context;
    private int judgement;
    //构造函数
    public TextRead(Context context,int judgement) {
        this.context = context;
        this.judgement = judgement;
    }

    //
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
