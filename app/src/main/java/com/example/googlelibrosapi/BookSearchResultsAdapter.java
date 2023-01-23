package com.example.googlelibrosapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.example.googlelibrosapi.R;
import com.example.googlelibrosapi.data.Volume;
//import com.example.googlelibrosapi.util.Util;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class BookSearchResultsAdapter extends RecyclerView.Adapter<BookSearchResultsAdapter.BookSearchResultHolder> {
    private List<Volume> results = new ArrayList<>();

    @NonNull
    @Override
    public BookSearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);

        return new BookSearchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchResultHolder holder, int position) {
        Volume volume = results.get(position);

        holder.titleTextView.setText(volume.getVolumeInfo().getTitle());
        holder.publishedDateTextView.setText(volume.getVolumeInfo().getPublishedDate());

        if (volume.getVolumeInfo().getImageLinks() != null) {
            String imageUrl = volume.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");
            /*
            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.smallThumbnailImageView);

             */
        }

        if (volume.getVolumeInfo().getAuthors() != null) {
            String authors = null;
            for (String a : volume.getVolumeInfo().getAuthors()) {
                authors += a + ", ";
            }
            //Util u = new Util();
            //String authors = u.StringJoin(volume.getVolumeInfo().getAuthors(), ", ");
            //String authors = u.concat(volume.getVolumeInfo().getAuthors(), ", ");
            holder.authorsTextView.setText(authors);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Volume> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class BookSearchResultHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView authorsTextView;
        private TextView publishedDateTextView;
        private ImageView smallThumbnailImageView;

        public BookSearchResultHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.book_item_title);
            authorsTextView = itemView.findViewById(R.id.book_item_authors);
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate);
            smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail);
        }
    }
}
