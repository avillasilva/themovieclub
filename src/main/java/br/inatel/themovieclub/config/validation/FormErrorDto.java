package br.inatel.themovieclub.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FormErrorDto {

    private String field;
    private String error;

}
