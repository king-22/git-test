package com.example.jyotirmay.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class NotePadActivity extends AppCompatActivity {
    EditText ET_CUS ;
    SharedPreferences sharedPreferences;
    String notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        sharedPreferences=getPreferences(MODE_PRIVATE);
        ET_CUS = (EditText) findViewById(R.id.et_cus);


        ET_CUS.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                notes = ET_CUS.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("notes",notes);
                editor.commit();

                return false;
            }
        });
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String saved_notes=sharedPref.getString("notes","");
        ET_CUS.setText(saved_notes);






    }
}
