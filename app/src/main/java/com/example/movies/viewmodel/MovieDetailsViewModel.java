package com.example.movies.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies.model.MovieDetails;
import com.example.movies.network.MovieRepository;

public class MovieDetailsViewModel extends ViewModel {

    MutableLiveData<MovieDetails> liveData;
    public MovieDetailsViewModel(){
        liveData=new MutableLiveData<>();
    }
    public void setKey(String id, Context context){
        MovieRepository repository=new MovieRepository(context);
        liveData= repository.movieDetailsApi(id);
        Log.d("live",liveData.toString());
    }
    public LiveData<MovieDetails> getLiveData(){
        return liveData;
    }
}
