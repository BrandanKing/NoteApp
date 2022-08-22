package king.brandan.noteapp.auth.controller;

import king.brandan.noteapp.auth.api.UserRestApi;
import king.brandan.noteapp.auth.dto.UserRequest;
import king.brandan.noteapp.auth.dto.UserResponse;
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
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		return ResponseEntity.ok().body(userService.getAllUsers());
	}

	@Override
	public ResponseEntity<UserResponse> getUserByUsername(String username) {
		return ResponseEntity.ok().body(userService.getUserByUsername(username));
	}

	@Override
	public ResponseEntity<String> saveUser(UserRequest user) {
		userService.saveUser(user);
		return new ResponseEntity<>(CREATED);
	}

	@Override
	public ResponseEntity<String> deleteUser(long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
