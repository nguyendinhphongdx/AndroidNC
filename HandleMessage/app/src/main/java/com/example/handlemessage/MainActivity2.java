package com.example.handlemessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnBack;
    private ProgressBar progressBar;
    private Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getViews();
    }

    private void getViews() {
        btnBack = findViewById(R.id.btnBack);
        btnStart = findViewById(R.id.startPro);
        progressBar = findViewById(R.id.id_progressBar);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
                break;
            case R.id.startPro:
                new ProgessAsyncTask().execute();
                break;
            default: break;
        }
    }
    private class ProgessAsyncTask extends AsyncTask<Void,Integer,String>{

        @Override
        protected String doInBackground(Void... voids) {
            // thuc thi cong viec duoi background
            for(int i=0;i<=100;i++){
                publishProgress(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "DONE";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity2.this, s, Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // cập nhật giao diện trên thanh progess
            Log.d("TAG", "onProgressUpdate: "+values[0]);
            progressBar.setProgress(values[0]);
        }
    }
}