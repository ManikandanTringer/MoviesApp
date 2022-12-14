package com.example.movies.network;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movies.model.MovieDetails;
import com.example.movies.model.MoviesData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    final String baseURL = "https://api.themoviedb.org/3/discover/movie?api_key=9a2bdc61f7e9de98ec887d51db0cde00&sort_by=popularity.desc";
    JSONArray jsonArray;
    MoviesData moviesData;
    MovieDetails movieDetails;
    Context contexto;
    RequestQueue queue;
    ArrayList<MoviesData> moviesDataArrayList = new ArrayList<>();
    private static final String TAG = "api";
    private MutableLiveData<List<MoviesData>> moviesLiveData;
    private Application application;

    public MovieRepository(Context context) {
        contexto = context;
        queue = Volley.newRequestQueue(contexto);

    }

//    public MovieRepository getMovies(){
//    }

    public MutableLiveData<List<MoviesData>> callApi() {
    moviesLiveData=new MutableLiveData<>();

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, baseURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray;
                        try {
//                            JSONObject jsonObject = response.getJSONObject("results");
//                            jsonArray = jsonObject.getJSONArray("photo");
                            JSONArray jsonArray1 = response.getJSONArray("results");
                            Log.d(TAG, "onResponse: " + response);
                            Log.d(TAG, "onResponse: RESULTS " + jsonArray1.getJSONObject(1).getString("id"));
                            for (int i = 0; i < jsonArray1.length(); i++) {
                               moviesData= new MoviesData(jsonArray1.getJSONObject(i).getString("id"), jsonArray1.getJSONObject(i).getString("vote_average"),
                                        jsonArray1.getJSONObject(i).getString("original_language"), jsonArray1.getJSONObject(i).getString("original_title"),
                                        jsonArray1.getJSONObject(i).getString("backdrop_path"), jsonArray1.getJSONObject(i).getString("title"),
                                        jsonArray1.getJSONObject(i).getString("poster_path"), jsonArray1.getJSONObject(i).getString("release_date"), jsonArray1.getJSONObject(i).getString("overview"));
                                    moviesDataArrayList.add(moviesData);
                                Log.d(TAG, "onResponse:aaa "+moviesData);
                            }
                            moviesLiveData.postValue(moviesDataArrayList);
                            Log.d(TAG, "aaaaaaa: "+moviesDataArrayList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );
        Log.d(TAG, "req  " + getRequest);

        queue.add(getRequest);


        return moviesLiveData;

    }

    public MutableLiveData<MovieDetails> movieDetailsApi(String id){
        MutableLiveData<MovieDetails> data=new MutableLiveData<>();
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/" + id + "?api_key=9a2bdc61f7e9de98ec887d51db0cde00", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray genres=response.getJSONArray("genres");
                            JSONObject name= (JSONObject) genres.get(0);
                            String runtime=response.getString("runtime");
                            String revenue=response.getString("revenue");
                            String genre=name.getString("name");
                            String id=response.getString("id");
                            String release_date=response.getString("release_date");
                            String voting=response.getString("vote_average");
                            String budget=response.getString("budget");
                            String lang=response.getString("original_language");
                            String title=response.getString("original_title");
                            String overview=response.getString("overview");
                            String backdrop_path=response.getString("backdrop_path");
                            movieDetails=new MovieDetails(id,budget,revenue,runtime,release_date,lang,backdrop_path,release_date,overview,title,genre,voting);
                            data.postValue(movieDetails);
                            Log.d(TAG, "Movieeeee: "+genre);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        JSONObject jsonObject = response.getString


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("movie", error.toString());

                    }
                });

        queue.add(request);
        return data;
    }

}
