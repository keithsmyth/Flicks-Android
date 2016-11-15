package com.keithsmyth.flicks.view;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PosterBindingAdapter {

    private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/";
    private static final String SIZE_SMALL = "w150/";
    private static final String SIZE_LARGE = "w500/";

    @BindingAdapter("custom:posterSmallUrl")
    public static void setPosterSmallUrl(ImageView imageView, String posterPath) {
        Picasso.with(imageView.getContext())
                .load(POSTER_BASE_URL + SIZE_SMALL + posterPath)
                .into(imageView);
    }

    @BindingAdapter("custom:posterLargeUrl")
    public static void setPosterLargeUrl(ImageView imageView, String posterPath) {
        Picasso.with(imageView.getContext())
                .load(POSTER_BASE_URL + SIZE_LARGE + posterPath)
                .into(imageView);
    }
}
