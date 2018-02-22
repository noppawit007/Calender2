package com.example.exrusive.wit;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {

    Button buttonNext;
    Button buttonPre;
    TextSwitcher textSwitcher;

    Animation slide_in_left, slide_out_right;

    String[] TextToSwitched = { "1.มกราคม", "2.กุมภาพันธ์", "3.มีนาคม", "4.เมษายน",
            "5.พฤษภาคม", "6.มิถุนายน", "7.กรกฎาคม","8.สิงหาคม","9.กันยายน","10.ตุลาคม","11.พฤศจิกายน","12.ธันวาคม"};
    int curIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNext = (Button) findViewById(R.id.next);
        buttonPre = (Button) findViewById(R.id.pre);

        textSwitcher = (TextSwitcher) findViewById(R.id.textswitcher);

        slide_in_left = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);

        textSwitcher.setInAnimation(slide_in_left);
        textSwitcher.setOutAnimation(slide_out_right);

        textSwitcher.setFactory(new ViewFactory(){

            @Override
            public View makeView() {
                TextView textView = new TextView(MainActivity.this);
                textView.setTextSize(60);
                textView.setTextColor(Color.RED);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                return textView;
            }});

        curIndex = 0;
        textSwitcher.setText(TextToSwitched[curIndex]);
        buttonNext.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(curIndex == TextToSwitched.length-1){
                    curIndex = 0;
                    textSwitcher.setText(TextToSwitched[curIndex]);
                }else{
                    textSwitcher.setText(TextToSwitched[++curIndex]);
                }
            }
        });
        buttonPre.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(curIndex == 0){
                    Toast toast = Toast.makeText(MainActivity.this, "ไม่สามารถย้อนกลับได้อีก", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                    textSwitcher.setText(TextToSwitched[--curIndex]);

            }
        });
    }
}
