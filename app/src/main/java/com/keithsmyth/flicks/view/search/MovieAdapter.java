package com.keithsmyth.flicks.view.search;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.keithsmyth.flicks.databinding.ItemMovieBinding;
import com.keithsmyth.flicks.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final List<Movie> movies = new ArrayList<>();
    private final List<Movie> filteredMovies = new ArrayList<>();

    private OnMovieTapListener movieTapListener;
    private String lowercaseSearchTerm;

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ItemMovieBinding movieBinding = ItemMovieBinding.inflate(inflater, parent, false);
        return new MovieViewHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.movieBinding.setMovie(filteredMovies.get(position));
        holder.movieBinding.setMovieTapListener(movieTapListener);
    }

    @Override
    public int getItemCount() {
        return filteredMovies.size();
    }

    void setMovieTapListener(OnMovieTapListener movieTapListener) {
        this.movieTapListener = movieTapListener;
    }

    void setMovies(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        filterMovies(lowercaseSearchTerm);
    }

    void setSearchTerm(String searchTerm) {
        this.lowercaseSearchTerm = searchTerm != null ? searchTerm.toLowerCase() : "";
        filterMovies(this.lowercaseSearchTerm);
    }

    private void filterMovies(String lowercaseSearchTerm) {
        filteredMovies.clear();
        if (TextUtils.isEmpty(lowercaseSearchTerm)) {
            filteredMovies.addAll(movies);
        } else {
            for (Movie movie : movies) {
                if (movie.title.toLowerCase().contains(lowercaseSearchTerm)) {
                    filteredMovies.add(movie);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        final ItemMovieBinding movieBinding;

        MovieViewHolder(ItemMovieBinding movieBinding) {
            super(movieBinding.getRoot());
            this.movieBinding = movieBinding;
        }
    }

    public interface OnMovieTapListener {
        void onMovieTap(Movie movie);
    }
}
