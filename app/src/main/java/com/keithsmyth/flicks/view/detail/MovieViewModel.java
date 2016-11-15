package com.keithsmyth.flicks.view.detail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.keithsmyth.flicks.BR;
import com.keithsmyth.flicks.model.Movie;

public class MovieViewModel extends BaseObservable {

    private final Movie movie;

    private int progress;

    public MovieViewModel(@NonNull Movie movie) {
        this.movie = movie;
    }

    @Bindable
    public String getPosterPath() {
        return movie.poster_path;
    }

    @Bindable
    public String getTitle() {
        return movie.title;
    }

    @Bindable
    public String getOverview() {
        return movie.overview;
    }

    @Bindable
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        notifyPropertyChanged(BR.progress);
        notifyPropertyChanged(BR.posterImageAlpha);
        notifyPropertyChanged(BR.contentViewAlpha);
        notifyPropertyChanged(BR.contentViewRotationY);
    }

    @Bindable
    public int getPosterImageAlpha() {
        // fade out until transparent at 90 degrees
        return progress > 90 ? 0 : (int) Math.round((1.0 - progress / 90.0) * 255.0);
    }

    @Bindable
    public int getContentViewRotationY() {
        return progress + 180;
    }

    @Bindable
    public float getContentViewAlpha() {
        // fade in until visible at 90 degrees
        return progress < 90 ? 0 : (float) (progress / 90.0 - 1.0);
    }
}
