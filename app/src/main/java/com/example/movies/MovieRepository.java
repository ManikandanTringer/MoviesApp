package com.example.movies;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    String baseURL = "";
    JSONArray jsonArray;
    MoviesData moviesData;
    Context contexto;
    RequestQueue queue;
    ArrayList<MoviesData> moviesDataArrayList=new ArrayList<>();
    private static final String TAG = "api";

    public ApiRepository(Context context){
        contexto=context;
        queue= Volley.newRequestQueue(contexto);

    }

    public List<MoviesData> callApi(String baseUrl) {


        Log.d(TAG, "callApi: "+baseUrl);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, "https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=6875a6037041f0fe5c4e5781f5c08b7e&text="+baseUrl+"&format=json&nojsoncallback=1", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        JSONArray jsonArray ;
                        try {
                            JSONObject jsonObject = response.getJSONObject("photos");
                            jsonArray = jsonObject.getJSONArray("photo");
                            Log.d(TAG, "onResponse: " + response);
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject object=jsonArray.getJSONObject(i);
                                Log.d("res",object.getString("farm"));
                                moviesData=new MoviesData(object.getString("farm"),object.getString("server"),object.getString("id"),object.getString("secret"));
                                moviesDataArrayList.add(moviesData);
//                                Log.d(TAG, "onResponse: "+photoInfo.getClass().getSimpleName());
                            }

                        } catch (Exception e) {
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
        Log.d(TAG, "onResponse:sdfg "+getRequest);

        queue.add(getRequest);


        return moviesDataArrayList;

    }

}
