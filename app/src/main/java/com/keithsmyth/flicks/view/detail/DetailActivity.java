package com.keithsmyth.flicks.view.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.keithsmyth.flicks.R;
import com.keithsmyth.flicks.databinding.ActivityDetailBinding;
import com.keithsmyth.flicks.model.Movie;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "extra-movie";

    public static Intent getIntent(Context context, Movie movie) {
        return new Intent(context, DetailActivity.class)
                .putExtra(EXTRA_MOVIE, movie);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        getSupportActionBar().hide();
        final Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        binding.setMovie(new MovieViewModel(movie));
    }
}
