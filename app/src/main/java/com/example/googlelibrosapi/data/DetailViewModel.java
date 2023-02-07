package com.example.googlelibrosapi.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.googlelibrosapi.api.BookRepository;

public class DetailViewModel extends AndroidViewModel {

    private BookRepository bookRepository;
    private LiveData<Volume> volumeLiveData;

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

        //public DetailViewModel(@NonNull Application application) {
        //    super(application);
        //}

        public void init() {
            bookRepository = new BookRepository();
            volumeLiveData = bookRepository.getVolumeLiveData();
        }

        public void searchVolumesById(String id) {
            //Dotenv dotenv = Dotenv.configure().directory("/assets").filename("env").load();
            //bookRepository.searchVolumes(keyword, author, dotenv.get("GOOGLE_API_KEY"));
            bookRepository.searchVolumesById(id);
        }

        public LiveData<Volume> getVolumeLiveData() {
            return volumeLiveData;
        }

}
