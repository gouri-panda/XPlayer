package com.one4ll.xplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PaintActivity extends AppCompatActivity {
    private static final String TAG = "PaintActivity";
    ImageView imageView;
    int batteryPercentageLabel;

    @Override
    @RequiresApi(23)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        imageView = findViewById(R.id.image_view);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        Log.d(TAG, "onCreate: width "+ width);
        Log.d(TAG, "onCreate: height "+ height);
        if (width > height){
            Log.d(TAG, "onCreate: landscape");
        }else if(width  < height){
            Log.d(TAG, "onCreate: portrait");

        }
        Log.d(TAG, "onCreate: pixel format "+ display.getMode().getRefreshRate());
        this.registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            batteryPercentageLabel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            Log.d(TAG, "onCreate: battery level "+ String.valueOf(batteryPercentageLabel));
        }
    };

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

//    /**
//     * sends message to the specific application
//     * @param text The message that user wants to send
//     * @param view is the activity view of the application
//     */
//    public void sendMessage(String text, View view){
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setType("text/plain");
//        intent.setPackage("com.example.appname");//For whatsapp you can use com.whatsapp
//        if (intent !=null){
//            intent.putExtra(Intent.EXTRA_TEXT, text);
//            startActivity(intent);
//        }else{
//            Snackbar.make(this, "Application not found.", Snackbar.LENGTH_SHORT).show();
//        }
//    }
}
