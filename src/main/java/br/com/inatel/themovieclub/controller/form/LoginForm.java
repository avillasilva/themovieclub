package br.com.inatel.themovieclub.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;

@Data
public class LoginForm {

    private String email;
    private String password;

    public UsernamePasswordAuthenticationToken getToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
