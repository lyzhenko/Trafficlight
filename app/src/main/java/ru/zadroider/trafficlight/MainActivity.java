package ru.zadroider.trafficlight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_start;
    private LinearLayout lamp_1;
    private LinearLayout lamp_2;
    private LinearLayout lamp_3;
    private boolean start = false;
    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lamp_1 = findViewById(R.id.lamp_1);
        lamp_2 = findViewById(R.id.lamp_2);
        lamp_3 = findViewById(R.id.lamp_3);
        btn_start = findViewById(R.id.btn_start);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!start) {
                    start = true;
                    btn_start.setText("Stop");

                    new Thread(() -> {
                        while (start) {
                            try {
                                counter++;
                                switch (counter) {
                                    case 1:
                                        lamp_1.setBackgroundColor(getResources().getColor(R.color.red));
                                        lamp_2.setBackgroundColor(getResources().getColor(R.color.grey));
                                        lamp_3.setBackgroundColor(getResources().getColor(R.color.grey));
                                        break;
                                    case 2:
                                        lamp_1.setBackgroundColor(getResources().getColor(R.color.grey));
                                        lamp_2.setBackgroundColor(getResources().getColor(R.color.yellow));
                                        lamp_3.setBackgroundColor(getResources().getColor(R.color.grey));
                                        break;
                                    case 3:
                                        lamp_1.setBackgroundColor(getResources().getColor(R.color.grey));
                                        lamp_2.setBackgroundColor(getResources().getColor(R.color.grey));
                                        lamp_3.setBackgroundColor(getResources().getColor(R.color.red));
                                        counter = 0;
                                        break;
                                }
                                Thread.sleep(1000);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    start = false;
                    btn_start.setText("Start");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        start = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        start = false;
    }
}