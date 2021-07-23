package br.com.inatel.themovieclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.UserRepository;

public class UserUpdateForm {
	
	@NotNull @NotEmpty @Length(min=5)
    private String name;

    @NotNull @NotEmpty
    private String email;

    @NotNull @NotEmpty @Length(min=8)
    private String password;

    @NotNull @NotEmpty @Length(min=8)
    private String passwordConfirmation;
   
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getPasswordConfirmation() {
    	return passwordConfirmation;
    }
    
    public User update(Long id, UserRepository userRepository) {
    	if (!password.equals(passwordConfirmation)) {
            return null;
        }
    	
    	User user = userRepository.getById(id);
    	user.setName(name);
    	user.setEmail(email);
    	user.setPassword(password);
    	return user;
    }
}
