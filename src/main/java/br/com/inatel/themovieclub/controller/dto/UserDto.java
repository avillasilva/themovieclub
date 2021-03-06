package br.com.inatel.themovieclub.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.inatel.themovieclub.model.User;

public class UserDto {

	private Long id;
	private String name;
	private String email;
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	public static Page<UserDto> toUserDto(Page<User> userList) {
		return userList.map(UserDto::new);
	}

	public static List<UserDto> toUserDto(List<User> friends) {
		return friends.stream().map(UserDto::new).collect(Collectors.toList());
	}
}
