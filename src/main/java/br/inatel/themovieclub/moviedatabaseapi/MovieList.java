package br.inatel.themovieclub.moviedatabaseapi;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieList {

    private int page;
    private Movie[] results;
    private int totalPages;
    private int totalResults;

    public MovieList() {
    }

    @Override
    public String toString() {
        return "SearchResponse [page=" + page + ",\n results=" + Arrays.toString(results) + ",\n total_pages=" + totalPages
                + ",\n total_results=" + totalResults + "]";
    }

}
