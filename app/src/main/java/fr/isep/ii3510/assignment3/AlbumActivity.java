package fr.isep.ii3510.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlbumActivity extends AppCompatActivity {

    private HashMap<String, ArrayList<String>> _albums;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        Intent intent  = getIntent();
        _albums =  (HashMap<String, ArrayList<String>>)intent.getSerializableExtra("Albums");
        AlbumAdapter albumAdapter =  new AlbumAdapter(this,_albums);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.album_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(albumAdapter);


    }
}