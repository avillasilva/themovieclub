package br.com.inatel.themovieclub.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.themovieclub.controller.dto.UserDto;
import br.com.inatel.themovieclub.controller.form.UserForm;
import br.com.inatel.themovieclub.controller.form.UserUpdateForm;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
	@GetMapping
    public List<UserDto> list() {
		List<User> users = userRepository.findAll(); 
    	return UserDto.toUserDto(users);
    }
    
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> detail(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(new UserDto(user.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
    	User user = form.toUser();
    	userRepository.save(user);
    	
    	URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
    	return ResponseEntity.created(uri).body(new UserDto(user));
    }
	

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserUpdateForm form) {
    	Optional<User> userOptional = userRepository.findById(id);
    	if (userOptional.isPresent()) {
			User user = form.update(id, userRepository);
			return ResponseEntity.ok(new UserDto(user));
		}
    	
    	return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> delete(@PathVariable Long id){
    	Optional<User> userOptional = userRepository.findById(id);
    	if (userOptional.isPresent()) {
    		userRepository.deleteById(id);			
    		return ResponseEntity.ok().build();
		}
    	
    	return ResponseEntity.notFound().build();
    }

//	@GetMapping("/friends")
//	public List<UserDto> getFriends(Authentication auth) {
//		User user = (User) auth.getPrincipal();
//		return UserDto.toUserDto(user.getFriends());
//	}
//
//	@PostMapping("/friends/{friendId}")
//	@Transactional
//	public ResponseEntity<UserDto> addFriend(Authentication auth, @PathVariable Long friendId) {
//		User user = (User) auth.getPrincipal();
//		Optional<User> optional = userRepository.findById(friendId);
//		if (optional.isPresent()) {
//			user.addFriend(optional.get());
//			// optional.get().addFriend(user);	
//			return ResponseEntity.ok().build();
//		}
//
//		return ResponseEntity.notFound().build();
//	}
//
//	@DeleteMapping("/friends/{friendId}")
//	@Transactional
//	public ResponseEntity<UserDto> unfriend(Authentication auth, @PathVariable Long friendId) {
//		User user = (User) auth.getPrincipal();
//		Optional<User> optional = userRepository.findById(friendId);
//		if (optional.isPresent()) {
//			user.unfriend(optional.get());
//			optional.get().unfriend(user);
//			return ResponseEntity.ok().build();
//		}
//
//		return ResponseEntity.notFound().build();
//	}
}
