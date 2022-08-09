package king.brandan.noteapp.auth.dto;

import king.brandan.noteapp.auth.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	private long id;
	private String username;
	private String email;
	private RoleEntity role;

}
