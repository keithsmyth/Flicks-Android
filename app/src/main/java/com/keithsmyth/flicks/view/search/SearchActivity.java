package com.keithsmyth.flicks.view.search;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.keithsmyth.flicks.BuildConfig;
import com.keithsmyth.flicks.R;
import com.keithsmyth.flicks.api.MoviesApi;
import com.keithsmyth.flicks.model.Movie;
import com.keithsmyth.flicks.model.MovieResponse;
import com.keithsmyth.flicks.view.detail.DetailActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private Call<MovieResponse> moviesCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final RecyclerView recycler = (RecyclerView) findViewById(R.id.results_recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter();
        movieAdapter.setMovieTapListener(new OnMovieTapListenerImpl());
        recycler.setAdapter(movieAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        if (BuildConfig.DEBUG && searchView == null)
            throw new NullPointerException("searchView is null");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                movieAdapter.setSearchTerm(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        final MoviesApi moviesApi = retrofit.create(MoviesApi.class);
        moviesCall = moviesApi.listNowPlaying(BuildConfig.API_KEY);
        moviesCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                onMoviesResponse(response.body().results);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                onMoviesFailure(t);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (moviesCall != null) {
            moviesCall.cancel();
            moviesCall = null;
        }
    }

    void onMoviesResponse(List<Movie> movies) {
        movieAdapter.setMovies(movies);
    }

    void onMoviesFailure(Throwable t) {
        Snackbar.make(findViewById(R.id.activity_main), t.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    class OnMovieTapListenerImpl implements MovieAdapter.OnMovieTapListener {

        @Override
        public void onMovieTap(Movie movie) {
            startActivity(DetailActivity.getIntent(SearchActivity.this, movie));
        }
    }
}
