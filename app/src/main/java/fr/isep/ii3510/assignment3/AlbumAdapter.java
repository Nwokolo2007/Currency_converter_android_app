package fr.isep.ii3510.assignment3;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private Context _context;
    private HashMap<String,ArrayList<String>> _albums;
    private String[]albumListNames;
    public AlbumAdapter(Context context, HashMap<String, ArrayList<String>> albums)
    {
        _context = context;
        _albums = albums;
        albumListNames = _albums.keySet().toArray(new String[0]);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View viewItem = layoutInflater.inflate(R.layout.album_names, parent, false);
        ViewHolder viewHolder = new ViewHolder(viewItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try{
            final String albumName = albumListNames[position];
            holder.textView.setText(albumName);
            String albumImage = albumName.toLowerCase(Locale.ROOT).replaceAll("\\s","");
            int res = _context.getResources().getIdentifier(albumImage,"drawable",_context.getPackageName());
            holder.imageView.setImageResource(res);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent =  new Intent(view.getContext(),SongActivity.class);
                    intent.putExtra("Songs",_albums.get(albumName));
                    view.getContext().startActivity(intent);
                }
            });
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return albumListNames.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.textView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
