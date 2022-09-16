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
    MoviesListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        recyclerView=binding.movieRV;
        adapter=new MoviesListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        getViewModel();

    }

    public void getViewModel(){
        MoviesViewModel moviesViewModel=new ViewModelProvider(this).get(MoviesViewModel.class);
//        moviesViewModel.setKey(id,secret,MainActivity.this);
        adapter=new MoviesListAdapter(this);
        moviesViewModel.getApi(this);
        moviesViewModel.getLiveData().observe(this, new Observer<List<MoviesData>>() {
            @Override
            public void onChanged(List<MoviesData> moviesData) {
                adapter.setItems(moviesData);
                Log.d("pp", "onChanged: "+moviesData.get(0).getTitle());
            }
        });
//        moviesViewModel.getLiveData().observe(this, new Observer<MoviesData>() {
//            @Override
//            public void onChanged(MoviesData moviesData) {
//                Log.d("some",moviesData.toString());
//                text.setText(imageModel.getUsername());
//                usernameTV.setText(imageModel.getUsername());
//                locationTV.setText(imageModel.getLocation());
//                viewsTV.setText(imageModel.getViews());
//                descTV.setText(imageModel.getDesc());
//                titleTV.setText(imageModel.getTitle());
//            }
//        });
    }
}