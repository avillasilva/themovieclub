package br.com.inatel.themovieclub.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReadResponse {

    private Long id;
    private String username;
    private String email;

}
