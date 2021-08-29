package com.example.handlemessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Handler handler;
    private TextView txtNumber;
    private Button btnStart;
    private ProgressBar progressBar;
    private static final int UP_NUMBER = 100;
    private static final int NUMBER_DONE = 101;
    private boolean isUpdate;
    private ImageButton btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        processHandler();
    }
    @SuppressLint("WrongViewCast")
    private void getViews(){
        txtNumber = findViewById(R.id.txtNumber);
        btnStart  = findViewById(R.id.btnStart);
        progressBar = findViewById(R.id.progressBar);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnStart.setOnClickListener(this);
    }
    private void  processHandler(){
        handler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case UP_NUMBER:
                        // thuc hien cap nhat
                        isUpdate = true;
                        // cap nhat UI voi gia tri moi
                        txtNumber.setText(String.valueOf(msg.arg1));
                        break;
                    case NUMBER_DONE:
                        // cap nhat lai giao dien
                        txtNumber.setText("SUCCESS!");
                        isUpdate= false;
                    default: break;
                }
            }
        };
    }
    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnStart:
                    updateNumber();
                    break;
                case R.id.btnNext:
                     startActivity(new Intent(MainActivity.this,MainActivity2.class));
                    break;
                default: break;
            }
    }
    private void updateNumber(){
        Log.d("TAG", "updateNumber: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // xu ly cap nhat
                for (int i=0;i<=10;i++){
                    // khai bao 1 message
                    Message msg = new Message();
                    // gan cong viec
                    msg.what = UP_NUMBER;
                    msg.arg1=i;
                    // push message vao message pool
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(NUMBER_DONE);
            }
        }).start();
    }
}
