package br.com.inatel.themovieclub.moviedatabaseapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Genre {

    private Long id;
    private String name;

}
