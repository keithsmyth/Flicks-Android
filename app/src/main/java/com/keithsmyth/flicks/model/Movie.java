package com.keithsmyth.flicks.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    public final String title;
    public final String overview;
    public final String poster_path;

    public Movie(String title, String overview, String poster_path) {
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
    }

    private Movie(Parcel in) {
        title = in.readString();
        overview = in.readString();
        poster_path = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(poster_path);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
