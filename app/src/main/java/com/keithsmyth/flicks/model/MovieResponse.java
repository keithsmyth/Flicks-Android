package com.keithsmyth.flicks.model;

import java.util.List;

public class MovieResponse {
    public final List<Movie> results;

    public MovieResponse(List<Movie> results) {
        this.results = results;
    }
}
