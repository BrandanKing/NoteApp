package king.brandan.noteapp.auth.api;

import king.brandan.noteapp.auth.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	ResponseEntity<List<UserDto>> getAllUsers();

	/**
	 * Gets a user by their username.
	 *
	 * @param username The username of the user.
	 * @return The user.
	 */
	@GetMapping("/v1/users/{username}")
	ResponseEntity<UserDto> getUserByUsername(@Valid @PathVariable String username);

	/**
	 * Save a user.
	 *
	 * @param user The user to save.
	 * @return The saved user.
	 */
	@PostMapping("/v1/users")
	ResponseEntity<String> saveUser(@Valid @RequestBody UserDto user);

	/**
	 * Update a user.
	 *
	 * @param id   The id of the user to update.
	 * @param user The user to update.
	 * @return The updated user.
	 */
	@PatchMapping("/v1/users/{id}")
	ResponseEntity<UserDto> updateUser(@Valid @PathVariable long id, @Valid @RequestBody UserDto user);

	/**
	 * Delete a user.
	 *
	 * @param id The id of the user to delete.
	 * @return The deleted user.
	 */
	@DeleteMapping("/v1/users/{id}")
	ResponseEntity<UserDto> deleteUser(@Valid @PathVariable long id);

}
