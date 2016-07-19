package com.example.jyotirmay.utilities;

import java.util.ArrayList;

/**
 * Created by jyotirmay on 11-07-2016.
 */
public class Utils {
    public static class Util {
        String util_name;
        int img_id;

        public Util(String util_name, int img_id) {
            this.util_name = util_name;
            this.img_id = img_id;
        }
    }


    public static ArrayList<Util> getUtils() {
        ArrayList<Utils.Util> UTIL = new ArrayList<>(10);
        UTIL.add(new Util("Caculator",R.drawable.uti1));
        UTIL.add(new Util("Stopwatch",R.drawable.uti1));
        UTIL.add(new Util("AlarmClock",R.drawable.uti1));
        UTIL.add(new Util("Calender",R.drawable.uti1));
        UTIL.add(new Util("Flashlight",R.drawable.uti1));
        UTIL.add(new Util("Converter",R.drawable.uti1));
        UTIL.add(new Util("Timer",R.drawable.uti1));
        UTIL.add(new Util("Notepad",R.drawable.uti1));
        UTIL.add(new Util("Dictionary",R.drawable.uti1));
        UTIL.add(new Util("ETC",R.drawable.uti1));
        return UTIL;

    }


}
