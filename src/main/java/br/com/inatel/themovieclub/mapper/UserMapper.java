package br.com.inatel.themovieclub.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.inatel.themovieclub.controller.request.UserRequest;
import br.com.inatel.themovieclub.controller.response.UserReadResponse;
import br.com.inatel.themovieclub.controller.response.UserSearchResponse;
import br.com.inatel.themovieclub.model.User;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public User mapToUser(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    public void mapToUser(UserRequest userRequest, User user) {
        modelMapper.map(userRequest, user);
    }

    public UserReadResponse mapToUserReadResponse(User user) {
        return modelMapper.map(user, UserReadResponse.class);
    }

    public Page<UserSearchResponse> mapToUserResponse(Page<User> users) {
        return users.map(user -> modelMapper.map(user, UserSearchResponse.class));
    }

    public List<UserSearchResponse> mapToUserSearchResponse(Set<User> users) {
        return users.stream()
                .map(user -> modelMapper.map(user, UserSearchResponse.class))
                .collect(Collectors.toList());
    }

}
