package king.brandan.noteapp.auth.mapper;

import king.brandan.noteapp.auth.dto.UserRequest;
import king.brandan.noteapp.auth.dto.UserResponse;
import king.brandan.noteapp.auth.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public UserResponse toResponse(UserEntity userEntity) {
		return UserResponse.builder()
			.id(userEntity.getId())
			.email(userEntity.getEmail())
			.username(userEntity.getUsername())
			.role(userEntity.getRole())
			.build();
	}

	public UserEntity toEntity(UserRequest userRequest) {
		return UserEntity.builder()
			.email(userRequest.getEmail())
			.username(userRequest.getUsername())
			.password(userRequest.getPassword())
			.build();
	}

}
