package com.example.googlelibrosapi.api;

import com.example.googlelibrosapi.data.VolumesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookSearchService {
    @GET("/books/v1/volumes")
    Call<VolumesResponse> searchVolumes(
            @Query("q") String query,
            @Query("inauthor") String author,
            @Query("startIndex") String startIndex
            //,@Query("key") String apiKey
    );

    @GET("/books/v1/volumes/id")
    Call<VolumesResponse> searchVolumesById(
            @Query("id") String id
    );
}