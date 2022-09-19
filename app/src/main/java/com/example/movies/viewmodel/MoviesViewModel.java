package com.example.movies.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies.model.MoviesData;
import com.example.movies.network.MovieRepository;

import java.util.List;

public class MoviesViewModel extends ViewModel {

    MovieRepository movieRepository;

    MutableLiveData<MoviesData> liveData;
    MutableLiveData<List<MoviesData>> moviesData;

    public MoviesViewModel() {
        moviesData = new MutableLiveData<>();
//        apiRepository= new MovieRepository(thigets);

//        liveData= (MutableLiveData<MoviesData>)
//                movieRepository.callApi();

    }

    public void getApi(Context context) {
        movieRepository = new MovieRepository(context);
        moviesData = movieRepository.callApi();
        Log.d("api", "getApi: VIewModel" + moviesData);
    }


    public void setData(int id, float voteAverage, String originalLanguage, String originalTitle, String backdrop_path,
                        String title, String posterPath, String release_date, String overview, Context context) {
        Log.d("live", liveData.toString());
    }

    public LiveData<List<MoviesData>> getLiveData() {
        return moviesData;
    }

    public MutableLiveData<List<MoviesData>> getMoviesListObserver() {
        return moviesData;
    }

}


