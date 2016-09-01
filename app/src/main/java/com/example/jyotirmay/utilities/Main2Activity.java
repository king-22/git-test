package com.example.jyotirmay.utilities;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentUris;
import android.content.Intent;

import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends Activity {
    ListView li;

    ArrayList<Utils.Util> A ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        li = (ListView) findViewById(R.id.lv_1);
        A = Utils.getUtils();
        Utiladapter utiladapter = new Utiladapter(A);
        li.setAdapter(utiladapter);

        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position , long id) {
                switch (position){
                    case 0:
                        Intent cal = new Intent(Main2Activity.this,CALCActivity.class);
                        startActivity(cal);
                        break;
                    case 1:
                        Intent sto = new Intent(Main2Activity.this,Stopwatch_Activity.class);
                        startActivity(sto);
                        break;
                    case  2:
                        Intent alm = new Intent(Main2Activity.this , AlarmActivity.class);
                        startActivity(alm);
                        break;
                    case 6:
                        long startMillis=0;

                        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                        builder.appendPath("time");
                        ContentUris.appendId(builder, startMillis);
                        Intent intent = new Intent(Intent.ACTION_VIEW)
                                .setData(builder.build());
                        startActivity(intent);
                        break;
                    case 4 :
                        Intent ntp = new Intent(Main2Activity.this,NotePadActivity.class);
                        startActivity(ntp);
                        break;

                    case 5 :
                        Intent fls = new Intent(Main2Activity.this,FlashLightActivity.class);
                        startActivity(fls);
                        break;


                    default:
                        String name = ((TextView) view.findViewById(R.id.tv_utils)).getText().toString();
                        Toast.makeText(Main2Activity.this, name+" " + "Under"+" "+" Development", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class Utiladapter extends BaseAdapter{

        private ArrayList<Utils.Util> mA;

        class  ViewHolder{
            TextView Utilname;
            ImageView Utilimage;
        }
        public Utiladapter(ArrayList<Utils.Util> mA){
            this.mA = mA;

        }

        @Override
        public int getCount() {
            return mA.size();
        }

        @Override
        public Utils.Util getItem(int position) {
            return mA.get(position);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return (position%2);
        }

        @Override
        public View getView(int position, View convertview, ViewGroup viewGroup) {
            LayoutInflater LI = getLayoutInflater();
            ViewHolder viewholder;
            if(convertview == null){
                int pos = getItemViewType(position);
            if(pos==0){
            convertview = LI.inflate(R.layout.list_view1,null);  }
            else {
                convertview = LI.inflate(R.layout.list_view2,null);}
            viewholder = new ViewHolder();
            viewholder.Utilimage  = (ImageView) convertview.findViewById(R.id.img_utils);
            viewholder.Utilname  = (TextView) convertview.findViewById(R.id.tv_utils);
            convertview.setTag(viewholder);}
            else {
                viewholder = (ViewHolder) convertview.getTag(); }
            Utils.Util this_util = getItem(position);
            viewholder.Utilimage.setImageResource(this_util.img_id);
            viewholder.Utilname.setText(this_util.util_name);
            return convertview;
        }
    }


}
