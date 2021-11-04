package com.hou.kiemtragk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hou.kiemtragk.entity.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnSelect, btnPause;
    static  Button btnPlay;
    TextView txtNumber,txtName;
    public static Song currentSong;
    public static MediaPlayer  mediaPlayer  = new MediaPlayer();;
    public  static List<Song> listSong = new ArrayList<>();
    int RequestCode = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSongs();
        setContentView(R.layout.activity_main);
        currentSong = listSong.get(0);
        setViews();
        setUp();
    }

    private void setUp() {

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SelectSongActivity.class);
              //  startActivity(intent);
                startActivityForResult(intent,RequestCode);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    btnPlay.setText("Play");
                }else{
                    btnPlay.setText("Stop");
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    if(currentSong == null){
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.song1);
                    }else{
                        mediaPlayer = MediaPlayer.create(MainActivity.this,currentSong.getPath());
                    }
                    mediaPlayer.start();
                }
//                try {
//                    mediaPlayer.prepare();

//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPauseMedia();
            }
        });
    }
    public static void onPauseMedia () {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            btnPlay.setText("Play");
        }
    }
    private void setSongs() {
        listSong.add(new Song(1,"Bài Hát 1",R.raw.song1));
        listSong.add(new Song(2,"Bài Hát 2",R.raw.song2));
        listSong.add(new Song(3,"Bài Hát 3",R.raw.song3));
        listSong.add(new Song(4,"Bài Hát 4",R.raw.song4));
        listSong.add(new Song(5,"Bài Hát 5",R.raw.song5));
        listSong.add(new Song(6,"Bài Hát 6",R.raw.song6));
    }

    private void setViews() {
        btnSelect = findViewById(R.id.select);
        btnPlay = findViewById(R.id.play);
        btnPause = findViewById(R.id.pause);
        txtNumber = findViewById(R.id.number);
        txtName = findViewById(R.id.name);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RequestCode) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    String value = data.getStringExtra("id");
                    Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
                    txtNumber.setText(value);
                    int position = Integer.parseInt(value);
                    txtName.setText(listSong.get(position -1).getName());
                    currentSong = listSong.get(position - 1);
                }
            }

        }

    }
}