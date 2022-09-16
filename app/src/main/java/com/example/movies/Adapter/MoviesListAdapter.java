package com.example.movies.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.databinding.MovieCardItemBinding;
import com.example.movies.model.MoviesData;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.SlidingWindowKt;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoiveViewHolder> {
    private static final String TAG = "MoviesListAdapter";

    Context context;
    List<MoviesData> moviesDataList;
   public MoviesListAdapter(Context context){
       this.context=context;

   }
    @NonNull
    @Override
    public MoviesListAdapter.MoiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieCardItemBinding movieCardItemBinding;
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListAdapter.MoiveViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return moviesDataList.size();
    }

    public class MoiveViewHolder extends RecyclerView.ViewHolder {
        public MoiveViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
