package com.example.movies.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.databinding.ActivityMainBinding;
import com.example.movies.databinding.ActivityMovieBinding;
import com.example.movies.model.MovieDetails;
import com.example.movies.viewmodel.MovieDetailsViewModel;

public class MovieActivity extends AppCompatActivity {

    ImageView backdropIV;
    TextView runtimeTV,genreTV,releasedateTV,descTV,votingTV,langTV,budgetTV,revenueTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


        backdropIV=(ImageView) findViewById(R.id.backdropIV);
        runtimeTV=(TextView) findViewById(R.id.runtimeTV);
        genreTV=(TextView) findViewById(R.id.genreTV);
        releasedateTV=(TextView) findViewById(R.id.releasedateTV);
        descTV=(TextView) findViewById(R.id.descTV);
        votingTV=(TextView) findViewById(R.id.votingTV);
        langTV=(TextView) findViewById(R.id.langTV);
        budgetTV=(TextView) findViewById(R.id.budgetTV);
        revenueTV=(TextView) findViewById(R.id.revenueTV);

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        String backdrop=intent.getStringExtra("backdrop");
        String title=intent.getStringExtra("title");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);


        String imgDes="https://image.tmdb.org/t/p/original/"+backdrop;

        Log.d("movie", "onCreate: "+id+"  "+backdrop);

        Glide.with(backdropIV.getContext())
                .load(imgDes)
                .into(backdropIV);

        getMovieViewModel(id);

    }

    private void getMovieViewModel(String id) {
        MovieDetailsViewModel viewModel=new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        viewModel.setKey(id,MovieActivity.this);
        viewModel.getLiveData().observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                descTV.setText(movieDetails.getOverview());
                runtimeTV.setText(movieDetails.getRuntime()+" minutes");
                genreTV.setText(movieDetails.getGenre());
                releasedateTV.setText(movieDetails.getRelease_date());
                votingTV.setText(movieDetails.getVoteAverage());
                budgetTV.setText(movieDetails.getBudget());
                revenueTV.setText(movieDetails.getRevenue());
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}