package king.brandan.noteapp.auth.controller;

import king.brandan.noteapp.auth.api.UserRestApi;
import king.brandan.noteapp.auth.dto.UserDto;
import king.brandan.noteapp.auth.entities.UserEntity;
import king.brandan.noteapp.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Validated
@AllArgsConstructor
public class UserController implements UserRestApi {

	private final UserService userService;

	@Override
	public ResponseEntity<List<UserDto>> getUsers() {
		return ResponseEntity.ok().body(userService.getUsers());
	}

	@Override
	public ResponseEntity<UserDto> getUser(String username) {
		return ResponseEntity.ok().body(userService.getUser(username));
	}

	@Override
	public ResponseEntity<String> saveUser(UserEntity user) {
		userService.addUser(user);
		return new ResponseEntity<>(CREATED);
	}

}
