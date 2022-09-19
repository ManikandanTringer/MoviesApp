package com.example.movies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.movies.Adapter.MoviesListAdapter;
import com.example.movies.R;
import com.example.movies.databinding.ActivityMainBinding;
import com.example.movies.model.MoviesData;
import com.example.movies.viewmodel.MoviesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ActivityMainBinding binding;
   private MoviesListAdapter adapter;
    private List<MoviesData> moviesData;
    MoviesViewModel moviesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        recyclerView=binding.movieRV;
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter=new MoviesListAdapter(this,moviesData);

        recyclerView.setAdapter(adapter);


        getViewModel();

    }

    public void getViewModel(){
         moviesViewModel=new ViewModelProvider(this).get(MoviesViewModel.class);
//        moviesViewModel.setKey(id,secret,MainActivity.this);
//        adapter=new MoviesListAdapter(this,moviesData);
        moviesViewModel.getApi(this);
        moviesViewModel.getMoviesListObserver().observe(this, new Observer<List<MoviesData>>() {
            @Override
            public void onChanged(List<MoviesData> moviesData) {
//                adapter.setItems(moviesData);
                if(moviesData!=null){
                    moviesData=moviesData;
                    adapter.setMoviesList(moviesData);
                }
                Log.d("pp", "onChanged: "+moviesData.get(0).getTitle());
            }
        });





 }
}