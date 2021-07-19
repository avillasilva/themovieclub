package br.com.inatel.steamclub.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.steamclub.model.User;
import br.com.inatel.steamclub.repository.UserRepository;

//import br.com.inatel.steamclub.controller.dto.UserDetailsDto;
//import br.com.inatel.steamclub.controller.form.UserForm;
//import br.com.inatel.steamclub.model.User;
//import br.com.inatel.steamclub.repository.UserRepository;

@RestController
@RequestMapping(value="/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
	@GetMapping
    public List<User> list() {
    	return userRepository.findAll();
    }
    
    
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDetailsDto> detale(@PathVariable Long id) {
//        Optional<User> userOptional = userRepository.findById(id);
//        if (userOptional.isPresent()) {
//            return ResponseEntity.ok(new UserDetailsDto(userOptional.get()));
//        }
//
//        return ResponseEntity.notFound().build();
//    }
//    
//    @GetMapping()
//    public List<User> list() {
//    	return Arrays.asList(new User("user 1", "email 1", "password 1"));
//    }
//
//    @PostMapping
//	@Transactional
//	public ResponseEntity<UserDetailsDto> create(@RequestBody @Validated UserForm form, UriComponentsBuilder uriBuilder) {
//		User user = form.toUser();
//		userRepository.save(user);
//		
//		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
//		return ResponseEntity.created(uri).body(new UserDetailsDto(user));
//	}
}
