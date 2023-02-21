package br.inatel.themovieclub.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenDto {

    private String token;
    private String type;

}
