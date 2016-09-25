package com.lljackie.test26_handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_content = (TextView) findViewById(R.id.tv_content);

        final Handler handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                tv_content.setText(msg.arg1 + "");
            }
        };

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress <= 100){
                    Message msg = new Message();
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    progress += 10;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage();
                msg.arg1 = -1;
                handler.sendMessage(msg);
            }
        };

        Button bt_start = (Button) findViewById(R.id.bt_start);
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(null, runnable, "WorkThread");
                thread.start();
            }
        });
    }
}
