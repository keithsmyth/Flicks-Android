package com.keithsmyth.flicks.api;

import com.keithsmyth.flicks.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("movie/now_playing")
    Call<MovieResponse> listNowPlaying(@Query("api_key") String apiKey);
}
