package br.com.inatel.themovieclub.moviedatabaseapi;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    private Long id;
    private boolean adult;
    private int[] genreIds;
    private String originalTitle;
    private String title;
    private Date releaseDate;

}
