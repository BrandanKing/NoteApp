package king.brandan.noteapp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	@NotNull
	private String username;
	@NotNull
	private String email;
	@NotNull
	private String password;

}
