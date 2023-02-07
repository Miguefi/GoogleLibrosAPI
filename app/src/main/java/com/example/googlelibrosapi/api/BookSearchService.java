package com.example.googlelibrosapi.api;

import com.example.googlelibrosapi.data.VolumesResponse;
import com.example.googlelibrosapi.data.Volume;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookSearchService {
    @GET("/books/v1/volumes")
    Call<VolumesResponse> searchVolumes(
            @Query("q") String query,
            @Query("inauthor") String author,
            @Query("startIndex") String startIndex
            //,@Query("key") String apiKey
            );

    @GET("/books/v1/volumes/{volumeId}")
    Call<Volume> searchVolumesById(
            @Path("volumeId") String volumeId
    );

}