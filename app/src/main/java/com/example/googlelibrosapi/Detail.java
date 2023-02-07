package com.example.googlelibrosapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.googlelibrosapi.data.DetailViewModel;
import com.example.googlelibrosapi.data.Volume;
import com.example.googlelibrosapi.data.VolumesResponse;

public class Detail extends AppCompatActivity {

    TextView autor, fechaPubli, titulo;
    ImageView imagen;
    DetailViewModel vm;
    LiveData<Volume> data;
    Volume libro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        String volumeId = i.getStringExtra(BuscadorLibros.BOOK_ID);
        Log.d("ID", volumeId);

        fechaPubli = findViewById(R.id.book_item_publishedDate);
        autor = findViewById(R.id.book_item_authors);
        titulo = findViewById(R.id.book_item_title);
        imagen = findViewById(R.id.book_item_smallThumbnail);

        vm = new ViewModelProvider(this).get(DetailViewModel.class);
        vm.init();
        data = vm.getVolumeLiveData();
        vm.searchVolumesById(volumeId);

        data.observe(this, (data)-> {
            //Log.d("Fallo", data.getItems().toString());
            libro = data;
            autor.setText(libro.getVolumeInfo().getAuthors().toString());
            titulo.setText(libro.getVolumeInfo().getTitle());
            fechaPubli.setText(libro.getVolumeInfo().getPublishedDate());
            if (libro.getVolumeInfo().getImageLinks() != null) {
                String imageUrl = libro.getVolumeInfo().getImageLinks().getSmallThumbnail()
                        .replace("http://", "https://");

                Glide.with(this).load(imageUrl).into(imagen);
            }
        });

    }
}