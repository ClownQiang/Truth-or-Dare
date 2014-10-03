package com.example.RealWordAndBigAdventure_Beta.TextRead;


import com.orm.SugarRecord;

/**
 * Created by clownqiang on 14-10-2.
 */
public class RealWord extends SugarRecord<RealWord> {
    String num;
    String item;

    public RealWord() {
    }

    public RealWord(String item, String num) {
        this.item = item;
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public String getItem() {
        return item;
    }
}
