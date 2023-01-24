package com.example.googlelibrosapi.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.googlelibrosapi.api.BookRepository;

public class DetailViewModel {

        private BookRepository bookRepository;
        private LiveData<VolumesResponse> volumesResponseLiveData;

        //public DetailViewModel(@NonNull Application application) {
        //    super(application);
        //}

        public void init() {
            bookRepository = new BookRepository();
            volumesResponseLiveData = bookRepository.getVolumesResponseLiveData();
        }

        public void searchVolumes(String keyword, String author) {
            //Dotenv dotenv = Dotenv.configure().directory("/assets").filename("env").load();
            //bookRepository.searchVolumes(keyword, author, dotenv.get("GOOGLE_API_KEY"));
            bookRepository.searchVolumes(keyword, author);
        }

        public LiveData<VolumesResponse> getVolumesResponseLiveData() {
            return volumesResponseLiveData;
        }

}
