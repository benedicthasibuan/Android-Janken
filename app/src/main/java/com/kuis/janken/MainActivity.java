package com.kuis.janken;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView msg;
    private ImageView img1, img2;
    private Wheel wheel1, wheel2;
    private Button btn;
    private boolean isStarted;
    public static final Random RANDOM = new Random();
    public static long randomLong(long lower, long upper) {
        return lower + (long)(RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        btn = findViewById(R.id.btn);
        msg = findViewById(R.id.msg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStarted) {
                   wheel1.stopWheel();
                   wheel2.stopWheel();
                   if (wheel1.currentIndex == wheel2.currentIndex) {
                       msg.setText("I");
                   } else if (wheel1.currentIndex == wheel2.currentIndex) {
                       msg.setText("YOU LOSE");
                   }
                   btn.setText("START");
                   isStarted = false;
                } else {
                    wheel1 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img1.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(0, 200));
                    wheel1.start();

                    wheel2 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img2.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(150, 400));
                    wheel2.start();

                    btn.setText("STOP");
                    msg.setText("");
                    isStarted = true;
                }
            }
        });
    }
}
