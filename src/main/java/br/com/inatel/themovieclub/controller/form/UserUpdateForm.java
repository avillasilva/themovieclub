package br.com.inatel.themovieclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.UserRepository;
import lombok.Data;

@Data
public class UserUpdateForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String name;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    @Length(min = 8)
    private String password;

    public User update(Long id, UserRepository userRepository) {
        User user = userRepository.getById(id);
        user.setName(name);
        user.setEmail(email);
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encryptedPassword);
        return user;
    }

}
