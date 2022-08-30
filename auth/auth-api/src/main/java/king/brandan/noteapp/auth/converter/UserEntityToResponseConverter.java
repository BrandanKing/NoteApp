package king.brandan.noteapp.auth.converter;

import king.brandan.noteapp.auth.dto.UserResponse;
import king.brandan.noteapp.auth.entities.UserEntity;
import org.springframework.core.convert.converter.Converter;

public class UserEntityToResponseConverter implements Converter<UserEntity, UserResponse> {

	@Override
	public UserResponse convert(UserEntity userEntity) {
		return UserResponse.builder()
			.id(userEntity.getId())
			.email(userEntity.getEmail())
			.username(userEntity.getUsername())
			.role(userEntity.getRole())
			.build();
	}

}
