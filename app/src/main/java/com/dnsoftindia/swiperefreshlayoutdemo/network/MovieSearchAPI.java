package com.dnsoftindia.swiperefreshlayoutdemo.network;

import com.dnsoftindia.swiperefreshlayoutdemo.models.MovieData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ganesha on 2/28/2016.
 */
public interface MovieSearchAPI {

    @GET("?y=&plot=short&r=json")
    Call<MovieData> searchMovies(@Query("s") String s, @Query("page") String page);
}
