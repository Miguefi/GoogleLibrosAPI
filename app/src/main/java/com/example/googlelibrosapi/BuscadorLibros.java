package com.example.googlelibrosapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.googlelibrosapi.EndlessRecyclerViewScrollListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.googlelibrosapi.data.BookSearchViewModel;
import com.example.googlelibrosapi.data.Volume;
import com.example.googlelibrosapi.data.VolumesResponse;

public class BuscadorLibros extends AppCompatActivity {

    public static final String BOOK_ID = "";
    private static final int ITEMS_PER_PAGE = 10;
    private static final String FINAL = "Se acabó";
    TextView busqueda, autor;
    Button buscar, mas;
    RecyclerView listado;
    BookSearchViewModel vm;
    LiveData<VolumesResponse> data;
    int startIndex;
    EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        busqueda = findViewById(R.id.idBusqueda);
        autor = findViewById(R.id.idAutor);
        buscar = findViewById(R.id.idBuscar);
        mas = findViewById(R.id.mas);
        startIndex = 0;
        listado = findViewById(R.id.idList);

        BookSearchResultsAdapter adapter = new BookSearchResultsAdapter();
        listado.setLayoutManager(new LinearLayoutManager(this));
        listado.setAdapter(adapter);

//        listado.addOnScrollListener(new EndlessRecyclerViewScrollListener(listado.getLayoutManager()) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                // Cargar más datos aquí
//                loadNextDataFromApi(page);
//            }
//        });

        scrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) listado.getLayoutManager()) {
            @Override
            public void onLoadMore(int i, int totalItemsCount, RecyclerView view) {
                startIndex = startIndex + 10;
                Toast.makeText(BuscadorLibros.this, "" + startIndex, Toast.LENGTH_SHORT).show();
                loadNextDataFromApi(startIndex);
            }
        };
        listado.addOnScrollListener(scrollListener);

        vm = new ViewModelProvider(this).get(BookSearchViewModel.class);
        vm.init();
        vm.getVolumesResponseLiveData().observe(this, (data)-> {
            adapter.setResults(data.getItems());
        });

        buscar.setOnClickListener((v)->{
            startIndex = 0;
            vm.searchVolumes(
                    busqueda.getText().toString() ,
                    autor.getText().toString() ,
                    Integer.toString(startIndex));
        });

        mas.setOnClickListener((v)->{
            startIndex += ITEMS_PER_PAGE;
            vm.searchVolumes(
                    busqueda.getText().toString() ,
                    autor.getText().toString() ,
                    Integer.toString(startIndex));
        });

        adapter.setClickListener(new BookSearchResultsAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, Volume volume) {
                lanzar(volume.getId());
            }
        });

    }

    private void lanzar(String id){
        Intent intento =  new Intent(this, Detail.class);
        intento.putExtra(BOOK_ID, id);
        startActivity(intento);
    }

    private void loadNextDataFromApi(int startIndex) {
        // Llamar a la API de Google Books aquí y añadir los datos a la lista
        // actualizar el adaptador
        startIndex += ITEMS_PER_PAGE;
        vm.searchVolumes(
                busqueda.getText().toString() ,
                autor.getText().toString() ,
                Integer.toString(startIndex));
    }
}