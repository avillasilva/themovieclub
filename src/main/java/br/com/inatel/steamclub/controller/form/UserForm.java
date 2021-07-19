//package br.com.inatel.steamclub.controller.form;
//
//import br.com.inatel.steamclub.model.User;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.validator.constraints.Length;
//
//public class UserForm {
//    
//    @NotNull @NotEmpty @Length(min=5)
//    private String name;
//
//    @NotNull @NotEmpty
//    private String email;
//
//    @NotNull @NotEmpty @Length(min=8)
//    private String password;
//
//    @NotNull @NotEmpty @Length(min=8)
//    private String passwordConfirmation;
//
//    public String getName() {
//        return name;
//    }
//    
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public User toUser() {
//        if (this.password.equals(passwordConfirmation)) {
//            return new User(name, email, password);
//        }
//
//        return null;
//    }
//}
