package king.brandan.noteapp.auth.mapper;

import king.brandan.noteapp.auth.dto.UserDto;
import king.brandan.noteapp.auth.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public UserDto toDto(UserEntity userEntity) {
		return UserDto.builder()
			.id(userEntity.getId())
			.email(userEntity.getEmail())
			.username(userEntity.getUsername())
			.role(userEntity.getRole())
			.build();
	}

}
