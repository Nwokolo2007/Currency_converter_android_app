package fr.isep.ii3510.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BandActivity extends AppCompatActivity {
    private ListView listview;
    InputStream inputStream;
    BufferedReader reader;
    HashMap<String,HashMap<String,String[]>>albumRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band);
        HashMap<String, ArrayList<String>>Albums =  new HashMap<>();
        HashMap<String, HashMap<String,ArrayList<String>>> bands = new HashMap<>();

        try{
            inputStream = getResources().openRawResource(R.raw.library);
            reader =  new BufferedReader(new InputStreamReader(inputStream));
            String bandInfo;
            while((bandInfo =  reader.readLine()) !=null)
            {
                String[]band = bandInfo.split(",");
                String bandName = band[0];
                String albumName = band[1];
                String songName = band[2];
                if(!bandName.equalsIgnoreCase("Band"))
                {
                    if(!bands.containsKey(bandName)) // check to see if band record has been retrieved
                    {
                        bands.put(bandName, new HashMap<String, ArrayList<String>>())  ;   // add a new band to list
                    }
                    Albums = bands.get(bandName);

                    if(!Albums.containsKey(albumName))
                    {
                        ArrayList<String>album = new ArrayList<>();
                        Albums.put(albumName,album);
                    }
                    Albums.get(albumName).add(songName);

                }


            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally {
            try{
                inputStream.close();

            }
            catch (IOException ex)
            {
                throw new RuntimeException("Error while closing input stream" + ex);
            }
        }

        BandAdapter bandAdapter =  new BandAdapter(this,bands);
        RecyclerView recyclerView  =   (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bandAdapter);

    }
}