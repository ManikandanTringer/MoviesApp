package com.example.movies.model;

public class MoviesData {
    private String id;
    private String voteAverage;
    private String originalLanguage;
    private String originalTitle;
    private String backdrop_path;
    private String title;
    private String posterPath;
    private String release_date;
    private String overview;

    public MoviesData(String id, String voteAverage, String originalLanguage, String originalTitle, String backdrop_path, String title, String posterPath, String release_date, String overview) {

        this.id = id;
        this.voteAverage = voteAverage;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.backdrop_path = backdrop_path;
        this.title = title;
        this.posterPath = posterPath;
        this.release_date = release_date;
        this.overview = overview;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
