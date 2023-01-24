package com.example.googlelibrosapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.googlelibrosapi.data.BookSearchViewModel;
import com.example.googlelibrosapi.data.VolumesResponse;

public class Detail extends AppCompatActivity {

    TextView busqueda, autor;
    Button buscar;
    RecyclerView listado;
    BookSearchViewModel vm;
    LiveData<VolumesResponse> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //mensaje = findViewById(R.id.ut02idRecpetora);
        Intent i = getIntent();
        String mensaje = i.getStringExtra(BuscadorLibros.BOOK_ID);
        //mensaje.setText(i.getStringExtra(ut02Lanzadora.CLAVE_INFO));

        /*busqueda = findViewById(R.id.idBusqueda);
        //autor = findViewById(R.id.idAutor);
        //buscar = findViewById(R.id.idBuscar);
        //listado = findViewById(R.id.idList);

        BookSearchResultsAdapter adapter = new BookSearchResultsAdapter();
        listado.setLayoutManager(new LinearLayoutManager(this));
        listado.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(BookSearchViewModel.class);
        vm.init();
        data = vm.getVolumesResponseLiveData();
        data.observe(this, (data)-> {
            adapter.setResults(data.getItems());
        });

        buscar.setOnClickListener((v)->{
            vm.searchVolumes(busqueda.getText().toString() , autor.getText().toString());
        });*/

    }
}