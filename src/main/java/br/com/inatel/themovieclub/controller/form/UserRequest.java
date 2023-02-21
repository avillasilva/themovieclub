package br.com.inatel.themovieclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

}
