package fr.isep.ii3510.assignment3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private Context _context;
     ArrayList<String> _songs;
    public SongAdapter(Context context, ArrayList<String> songs)
    {
        _context =  context;
        _songs =  songs;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =  LayoutInflater.from(parent.getContext());
        View viewItem  =  layoutInflater.inflate(R.layout.song_name,parent,false);
        ViewHolder viewHolder =  new ViewHolder(viewItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String songName = _songs.get(position);
        TextView songTextView = holder.textView;
        songTextView.setText(songName);
    }

    @Override
    public int getItemCount() {
        return _songs.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.textView);
        }
    }
}
