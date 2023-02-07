package com.example.googlelibrosapi.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.googlelibrosapi.data.Volume;
import com.example.googlelibrosapi.data.VolumesResponse;

//import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {

    private static final String BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/";

    private BookSearchService bookSearchService;
    private MutableLiveData<VolumesResponse> volumesResponseLiveData;
    private MutableLiveData<Volume> volumeLiveData;

    public BookRepository() {
        volumesResponseLiveData = new MutableLiveData<>();
        volumeLiveData = new MutableLiveData<>();

        /*HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();*/

        bookSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookSearchService.class);

    }

    public void searchVolumes(String keyword, String author) {
        searchVolumes(keyword, author, "0");
    }

    public void searchVolumes(String keyword, String author, String startIndex) {
        bookSearchService.searchVolumes(keyword, author, startIndex)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response) {
                        if (response.body() != null) {
                            volumesResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<VolumesResponse> call, Throwable t) {
                        volumesResponseLiveData.postValue(null);
                    }
                });
    }

    public void searchVolumesById(String id) {
        bookSearchService.searchVolumesById(id)
                .enqueue(new Callback<Volume>() {
                    @Override
                    public void onResponse(Call<Volume> call, Response<Volume> response) {
                        if (response.body() != null) {
                            volumeLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Volume> call, Throwable t) {
                        volumeLiveData.postValue(null);
                    }
                });
    }

    /*public void searchVolumes(String keyword, String author, String apiKey) {
        bookSearchService.searchVolumes(keyword, author, apiKey)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response) {
                        if (response.body() != null) {
                            volumesResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<VolumesResponse> call, Throwable t) {
                        volumesResponseLiveData.postValue(null);
                    }
                });
    }*/

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }

    public LiveData<Volume> getVolumeLiveData() {
        return volumeLiveData;
    }
}
