package com.hou.kiemtragk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.hou.kiemtragk.entity.Song;

import java.util.ArrayList;
import java.util.List;

import static com.hou.kiemtragk.MainActivity.currentSong;
import static com.hou.kiemtragk.MainActivity.listSong;
import static com.hou.kiemtragk.MainActivity.onPauseMedia;

public class SelectSongActivity extends AppCompatActivity {
    TextView txtNumber;
    Button btnChose;
    GridView grSongs;
    List<Song> list = listSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_song);
        setViews();
        setUp();
    }

    private void setUp() {
        List<String> arrName = new ArrayList<>();
        for (Song song : list) {
            arrName.add(song.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrName);
        grSongs.setAdapter(adapter);
        grSongs.setNumColumns(2);
        btnChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSongActivity.this,MainActivity.class);
                intent.putExtra("id",txtNumber.getText());
                setResult(RESULT_OK,intent);
                //(intent);
//                int position = Integer.parseInt(txtNumber.getText()+"");
//                currentSong = list.get(position);
                onPauseMedia();
                finish();
            }
        });
        grSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtNumber.setText(listSong.get(position).getId()+"");
            }
        });
    }

    private void setViews() {
        txtNumber = findViewById(R.id.txtnumber);
        btnChose = findViewById(R.id.btnChose);
        grSongs = findViewById(R.id.listSong);
    }
}