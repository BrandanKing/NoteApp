package king.brandan.noteapp.auth.api;

import king.brandan.noteapp.auth.dto.UserDto;
import king.brandan.noteapp.auth.entities.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping()
public interface UserRestApi {

	@GetMapping("/users")
	ResponseEntity<List<UserDto>> getUsers();

	@GetMapping("/{username}")
	ResponseEntity<UserDto> getUser(@PathVariable String username);

	@PostMapping("/user")
	ResponseEntity<String> saveUser(@Valid @RequestBody UserEntity user);

}
