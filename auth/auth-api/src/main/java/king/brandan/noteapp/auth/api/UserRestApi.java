package king.brandan.noteapp.auth.api;

import king.brandan.noteapp.auth.dto.UserRequest;
import king.brandan.noteapp.auth.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping()
public interface UserRestApi {

	/**
	 * Get all users
	 *
	 * @return List of users
	 */
	@GetMapping("/v1/users")
	ResponseEntity<List<UserResponse>> getAllUsers();

	/**
	 * Gets a user by their username.
	 *
	 * @param username The username of the user.
	 * @return The user.
	 */
	@GetMapping("/v1/users/{username}")
	ResponseEntity<UserResponse> getUserByUsername(@Valid @PathVariable String username);

	/**
	 * Save a user.
	 *
	 * @param user The user to save.
	 * @return The saved user.
	 */
	@PostMapping("/v1/users")
	ResponseEntity<String> saveUser(@Valid @RequestBody UserRequest user);

	/**
	 * Delete a user.
	 *
	 * @param id The id of the user to delete.
	 * @return The deleted user.
	 */
	@DeleteMapping("/v1/users/{id}")
	ResponseEntity<String> deleteUser(@Valid @PathVariable long id);

}
