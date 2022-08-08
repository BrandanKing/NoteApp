package king.brandan.noteapp.auth.controller;

import king.brandan.noteapp.auth.api.UserRestApi;
import king.brandan.noteapp.auth.dto.UserDto;
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
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok().body(userService.getAllUsers());
	}

	@Override
	public ResponseEntity<UserDto> getUserByUsername(String username) {
		return ResponseEntity.ok().body(userService.getUserByUsername(username));
	}

	@Override
	public ResponseEntity<String> saveUser(UserDto user) {
		userService.saveUser(user);
		return new ResponseEntity<>(CREATED);
	}

	@Override
	public ResponseEntity<UserDto> updateUser(long id, UserDto user) {
		return null;
	}

	@Override
	public ResponseEntity<UserDto> deleteUser(long id) {
		return null;
	}
}
