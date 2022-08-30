package king.brandan.noteapp.auth.converter;

import king.brandan.noteapp.auth.dto.UserRequest;
import king.brandan.noteapp.auth.entities.UserEntity;
import org.springframework.core.convert.converter.Converter;

public class UserRequestToEntity implements Converter<UserRequest, UserEntity> {

	@Override
	public UserEntity convert(UserRequest userRequest) {
		return UserEntity.builder()
			.username(userRequest.getUsername())
			.email(userRequest.getEmail())
			.password(userRequest.getPassword())
			.build();
	}

}
