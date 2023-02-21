package br.com.inatel.themovieclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.themovieclub.controller.request.UserRequest;
import br.com.inatel.themovieclub.controller.response.UserReadResponse;
import br.com.inatel.themovieclub.controller.response.UserSearchResponse;
import br.com.inatel.themovieclub.mapper.UserMapper;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    @Cacheable(value = "userList")
    public Page<UserSearchResponse> search(@PageableDefault(sort = "id", size = 10) Pageable pageable) {
        Page<User> users = userService.search(pageable);
        return userMapper.mapToUserResponse(users);
    }

    @GetMapping("/{id}")
    public UserReadResponse read(@PathVariable Long id) {
        User user = userService.read(id);
        return userMapper.mapToUserReadResponse(user);
    }

    @PostMapping
    @CacheEvict(value = "userList", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadResponse create(@RequestBody @Valid UserRequest form) {
        User user = userMapper.mapToUser(form);
        user = userService.save(user);
        return userMapper.mapToUserReadResponse(user);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "userList", allEntries = true)
    public ResponseEntity<UserReadResponse> update(Authentication authentication, @PathVariable Long id, @RequestBody @Valid UserRequest form) {
        if (((User) authentication.getPrincipal()).getId() != id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.read(id);
        userMapper.mapToUser(form, user);
        user = userService.save(user);
        return ResponseEntity.ok(userMapper.mapToUserReadResponse(user));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "userList", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(Authentication authentication, @PathVariable Long id) {
        if (((User) authentication.getPrincipal()).getId() != id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/{id}/friends")
//    public List<UserDto> getFriends(@PathVariable Long id) {
//        User user = userService.read(id);
//        Set<User> friends = user.getFriends();
//        return userMapper.mapToUserResponse(friends);
//    }
//
//    @PutMapping("/{id}/friends/{friendId}")
//    @Transactional
//    public ResponseEntity<UserDto> addFriend(@PathVariable Long id, @PathVariable Long friendId) {
//        User user = userService.read(id);
//        User friend = userService.read(friendId);
//        friend.get().addFriend(user);
//        user.addFriend(friend.get());
//        return ResponseEntity.ok(new UserDto(user));
//    }
//
//    @DeleteMapping("{id}/friends/{friendId}")
//    @Transactional
//    public ResponseEntity<UserDto> unfriend(Authentication auth, @PathVariable Long friendId) {
//        User authUser = (User) auth.getPrincipal();
//        User user = userRepository.findById(authUser.getId()).get();
//        Optional<User> friend = userRepository.findById(friendId);
//
//        if (friend.isPresent()) {
//            user.unfriend(friend.get());
//            friend.get().unfriend(user);
//            return ResponseEntity.ok().build();
//        }
//
//        return ResponseEntity.notFound().build();
//    }

}
