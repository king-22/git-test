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
        ArrayList<Utils.Util> UTIL = new ArrayList<>(9);
        UTIL.add(new Util("Caculator",R.drawable.calculator));
        UTIL.add(new Util("Stopwatch",R.drawable.stopwatch));
        UTIL.add(new Util("AlarmClock",R.drawable.alarm));
        UTIL.add(new Util("To-Do List",R.drawable.todo));
        UTIL.add(new Util("Notepad",R.drawable.notepad));
        UTIL.add(new Util("Flashlight",R.drawable.flashlight));
        UTIL.add(new Util("Calender",R.drawable.calender));
        UTIL.add(new Util("Dictionary",R.drawable.dictionary));
        UTIL.add(new Util("ETC",R.drawable.etc));
        return UTIL;

    }


}
