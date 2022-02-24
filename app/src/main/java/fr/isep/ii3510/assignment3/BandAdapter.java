package fr.isep.ii3510.assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BandAdapter extends RecyclerView.Adapter<BandAdapter.ViewHolder>{

    Map<String, HashMap<String,ArrayList<String>>>Bands;
    String[] bandList;
    Context _context;


    public BandAdapter(Context context,HashMap<String, HashMap<String,ArrayList<String>>>bands)
    {
        Bands  = bands;
        bandList = Bands.keySet().toArray(new String[0]);
        this._context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =  LayoutInflater.from(parent.getContext());
        View viewItem = layoutInflater.inflate(R.layout.band_name,parent,false);
        ViewHolder viewHolder =  new ViewHolder(viewItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String bandName = bandList[position];
        holder.textView.setText(bandName);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView clickedTextView = (TextView)view;
                HashMap<String,ArrayList<String>> selectedAlbum = Bands.get(clickedTextView.getText());
                Intent intent =  new Intent(view.getContext(),AlbumActivity.class);
                intent.putExtra("Albums", selectedAlbum);
               view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bandList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.textView);
        }
    }
}

