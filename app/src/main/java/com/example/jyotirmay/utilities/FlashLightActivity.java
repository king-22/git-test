package com.example.jyotirmay.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jyotirmay.utilities.R;

public class FlashLightActivity extends Activity {
    private boolean isFlashOn = false;
    private boolean FlashCheck;
    private Camera.Parameters params;
    private ImageButton btn;
    private Camera camera;
    RelativeLayout RL_FLASH;
    public static final String TAG = "hey";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_light);
        // flash switch button
        btn = (ImageButton) findViewById(R.id.btnFlash);
        RL_FLASH = (RelativeLayout) findViewById(R.id.rl_flash);

        FlashCheck = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if(!FlashCheck) {
            AlertDialog alertDialog = new AlertDialog.Builder(FlashLightActivity.this).create();
            alertDialog.setTitle("Sorry");
            alertDialog.setMessage("This device does not support Flashlight");
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alertDialog.show();
            return;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFlashOn) {
                    // turn off flash
                    MyCamera();
                    turnOffFlash();
                    btn.setImageResource(R.drawable.button_switch_off_min);

                } else {
                    // turn on flash
                    MyCamera();
                    turnOnFlash();
                    btn.setImageResource(R.drawable.button_switch_on_min);


                }
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("hey", "onPause: ");
//        if (isFlashOn) {
//            // turn off flash
//            MyCamera();
//            turnOffFlash();
//            btn.setImageResource(R.drawable.button_switch_off);
//        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyCamera();
        turnOffFlash();
        btn.setImageResource(R.drawable.button_switch_off_min);
        Log.d(TAG, "onBackPressed: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("hey", "onStop: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MyCamera();
//        turnOnFlash();
//        btn.setImageResource(R.drawable.button_switch_on);
        Log.d("hey", "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");      
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    private  void MyCamera(){

        if(camera == null){
            try {
                camera = Camera.open();
                params = camera.getParameters();
            }catch (RuntimeException e) {

                Toast.makeText(FlashLightActivity.this, "Camera failed , Please try later.", Toast.LENGTH_SHORT).show();
            }
        }
        }


        private void turnOnFlash() {
            if (!isFlashOn) {
                if (camera == null || params == null) {
                    return;
                }


              //  params = camera.getParameters();
                params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(params);
                camera.startPreview();
                isFlashOn = true;



            }
        }

    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }



          //  params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;



        }
    }



    }

