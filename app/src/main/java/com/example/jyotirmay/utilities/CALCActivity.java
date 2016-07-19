package com.example.jyotirmay.utilities;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CALCActivity extends Activity {
    public static final String TAG1 = "MainActivity";
    private EditText ET_var1;
    private TextView  TV_result , TV_op;
    private Button Btn_1, Btn_2, Btn_3 ,Btn_4,Btn_5,Btn_6,Btn_7,Btn_8,Btn_9,Btn_0,Btn_clear,Btn_equal,Btn_add,Btn_sub,Btn_mul,Btn_div;
    int a=0 , b=0 , c=0 , op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        ET_var1 = (EditText) findViewById(R.id.et_var1);
        TV_result = (TextView) findViewById(R.id.tv_result);
        TV_op = (TextView) findViewById(R.id.tv_op);
        Btn_0 =(Button) findViewById(R.id.btn_0);
        Btn_1=(Button) findViewById(R.id.btn_1);
        Btn_2 =(Button) findViewById(R.id.btn_2);
        Btn_3 =(Button) findViewById(R.id.btn_3);
        Btn_4 =(Button) findViewById(R.id.btn_4);
        Btn_5 =(Button) findViewById(R.id.btn_5);
        Btn_6 =(Button) findViewById(R.id.btn_6);
        Btn_7 =(Button) findViewById(R.id.btn_7);
        Btn_8 =(Button) findViewById(R.id.btn_8);
        Btn_9 =(Button) findViewById(R.id.btn_9);
        Btn_equal =(Button) findViewById(R.id.btn_equal);
        Btn_clear =(Button) findViewById(R.id.btn_clear);
        Btn_add =(Button) findViewById(R.id.btn_add);
        Btn_sub =(Button) findViewById(R.id.btn_sub);
        Btn_mul =(Button) findViewById(R.id.btn_mul);
        Btn_div =(Button) findViewById(R.id.btn_div);

        View.OnClickListener clicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                switch (v.getId()){
                    case(R.id.btn_0): ET_var1.setText(ET_var1.getText().toString()+"0");break;
                    case(R.id.btn_1): ET_var1.setText(ET_var1.getText().toString()+"1"); break;
                    case(R.id.btn_2): ET_var1.setText(ET_var1.getText().toString()+"2"); break;
                    case(R.id.btn_3): ET_var1.setText(ET_var1.getText().toString()+"3"); break;
                    case(R.id.btn_4): ET_var1.setText(ET_var1.getText().toString()+"4"); break;
                    case(R.id.btn_5): ET_var1.setText(ET_var1.getText().toString()+"5"); break;
                    case(R.id.btn_6): ET_var1.setText(ET_var1.getText().toString()+"6"); break;
                    case(R.id.btn_7): ET_var1.setText(ET_var1.getText().toString()+"7"); break;
                    case(R.id.btn_8): ET_var1.setText(ET_var1.getText().toString()+"8"); break;
                    case(R.id.btn_9): ET_var1.setText(ET_var1.getText().toString()+"9"); break;

                    case(R.id.btn_add):a= ET_var1.getText().toString().isEmpty()?0:Integer.parseInt(ET_var1.getText().toString());
                        op=1; ET_var1.setText(null);TV_op.setText("+");break;
                    case(R.id.btn_sub):a=ET_var1.getText().toString().isEmpty()?0:Integer.parseInt(ET_var1.getText().toString());
                        ET_var1.setText(null);op=2;TV_op.setText("-");break;
                    case(R.id.btn_mul):a=ET_var1.getText().toString().isEmpty()?0:Integer.parseInt(ET_var1.getText().toString());
                        ET_var1.setText(null);op=3;TV_op.setText("*");break;
                    case(R.id.btn_div):a=ET_var1.getText().toString().isEmpty()?0:Integer.parseInt(ET_var1.getText().toString());
                        ET_var1.setText(null);op=4;TV_op.setText("/");break;

                    case(R.id.btn_clear): ET_var1.setText(null);TV_result.setText(null);TV_op.setText(null);a=0;b=0; break;

                    case(R.id.btn_equal):b= ET_var1.getText().toString().isEmpty()?0:Integer.parseInt(ET_var1.getText().toString());
                        switch (op){
                            case 1: c = a+b;
                                TV_result.setText(String.valueOf(c));
                                ET_var1.setText(String.valueOf(c));break;
                            case 2: c = a-b;
                                TV_result.setText(String.valueOf(c));
                                ET_var1.setText(String.valueOf(c));break;

                            case 3: c= a*b;
                                TV_result.setText(String.valueOf(c));
                                ET_var1.setText(String.valueOf(c));break;

                            case 4: try{
                                c=a/b;
                                TV_result.setText(String.valueOf(c));
                                ET_var1.setText(String.valueOf(c));break; }
                            catch (ArithmeticException e) {
                                Log.e(TAG1, "onclick:not defined", e);
                                Toast.makeText(CALCActivity.this, "not defined ,clear input", Toast.LENGTH_SHORT).show();
                            }

                        }
                }
            }
        };

        Btn_0.setOnClickListener(clicklistener);
        Btn_1.setOnClickListener(clicklistener);
        Btn_2.setOnClickListener(clicklistener);
        Btn_3.setOnClickListener(clicklistener);
        Btn_4.setOnClickListener(clicklistener);
        Btn_5.setOnClickListener(clicklistener);
        Btn_6.setOnClickListener(clicklistener);
        Btn_7.setOnClickListener(clicklistener);
        Btn_8.setOnClickListener(clicklistener);
        Btn_9.setOnClickListener(clicklistener);
        Btn_add.setOnClickListener(clicklistener);
        Btn_sub.setOnClickListener(clicklistener);
        Btn_mul.setOnClickListener(clicklistener);
        Btn_div.setOnClickListener(clicklistener);
        Btn_clear.setOnClickListener(clicklistener);
        Btn_equal.setOnClickListener(clicklistener);



    }
}
