package com.example.movies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.movies.R;
import com.example.movies.model.MoviesData;
import com.example.movies.view.MainActivity;
import com.example.movies.view.MovieActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kotlin.collections.SlidingWindowKt;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoiveViewHolder> {
    private static final String TAG = "MoviesListAdapter";

    Context context;
    List<MoviesData> moviesDataList;
   public MoviesListAdapter(Context context,List<MoviesData> moviesData){
       this.context=context;
       this.moviesDataList=moviesData;

   }

   public  void setMoviesList(List<MoviesData> moviesList){
       this.moviesDataList=moviesList;
       notifyDataSetChanged();
   }
    @NonNull
    @Override
    public MoviesListAdapter.MoiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view=LayoutInflater.from(context).inflate(R.layout.movie_card_item,parent,false);
        return new MoiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListAdapter.MoiveViewHolder holder, int position) {
       String imgLink="https://image.tmdb.org/t/p/original/"+this.moviesDataList.get(position).getPosterPath();
       Glide.with(context)
                       .load(imgLink).apply(RequestOptions.centerCropTransform()).into(holder.posterIV);
       holder.titleTV.setText(this.moviesDataList.get(position).getTitle().toString());
       holder.overviewTV.setText(this.moviesDataList.get(position).getOverview().toString());
       holder.ratingTV.setText(this.moviesDataList.get(position).getVoteAverage().toString());
       holder.dateTV.setText(this.moviesDataList.get(position).getRelease_date().toString());
       holder.languageTV.setText(this.moviesDataList.get(position).getOriginalLanguage().toString().toUpperCase(Locale.ROOT));

       holder.movie_card.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(context, MovieActivity.class);
//               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",moviesDataList.get(holder.getAdapterPosition()).getId());
                intent.putExtra("overview",moviesDataList.get(holder.getAdapterPosition()).getOverview());
                intent.putExtra("title",moviesDataList.get(holder.getAdapterPosition()).getOriginalTitle());
                intent.putExtra("backdrop",moviesDataList.get(holder.getAdapterPosition()).getBackdrop_path());
                context.startActivity(intent);

           }

       });

    }

    @Override
    public int getItemCount() {
       if(this.moviesDataList!= null){
           return moviesDataList.size();
       }
        return 0;
    }

    public class MoiveViewHolder extends RecyclerView.ViewHolder {
       ImageView posterIV;
        TextView titleTV,overviewTV,ratingTV,dateTV,languageTV;
            CardView movie_card;
        public MoiveViewHolder(@NonNull View itemView) {
            super(itemView);
            posterIV=(ImageView) itemView.findViewById(R.id.posterIV);
            titleTV=(TextView) itemView.findViewById(R.id.titleTV);
            overviewTV=(TextView) itemView.findViewById(R.id.overviewTV);
            ratingTV=(TextView) itemView.findViewById(R.id.ratingTV);
            dateTV=(TextView) itemView.findViewById(R.id.dateTV);
            languageTV=(TextView) itemView.findViewById(R.id.languageTV);
            movie_card=(CardView) itemView.findViewById(R.id.movie_card);


        }
    }
}
