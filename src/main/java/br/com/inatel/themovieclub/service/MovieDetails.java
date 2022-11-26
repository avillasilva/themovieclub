package br.com.inatel.themovieclub.service;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetails {

    private Long id;
    private String title;
    private String status;
    private Date release_date;
    private String popularity;
    private String overview;
    private String original_language;
    private Genre[] genres;

    public Genre[] getGenres() {
        return genres;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public MovieDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    @Override
    public String toString() {
        return "MovieDetails [id=" + id + ",\n title=" + title + ",\n status=" + status + ",\n release_date=" + release_date
                + ",\n popularity=" + popularity + ",\n overview=" + overview + ",\n original_language=" + original_language + ",\n genres="
                + Arrays.toString(genres) + "]";
    }

}
