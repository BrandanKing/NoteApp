package king.brandan.noteapp.auth.service;

import king.brandan.noteapp.auth.dto.UserRequest;
import king.brandan.noteapp.auth.dto.UserResponse;
import king.brandan.noteapp.auth.entities.RoleEntity;
import king.brandan.noteapp.auth.entities.UserEntity;
import king.brandan.noteapp.auth.repositories.RoleRepository;
import king.brandan.noteapp.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final ConversionService conversionService;

	public List<UserResponse> getAllUsers() {
		log.info("Fetching all users");
		return userRepository.findAll().stream()
			.map(userEntity -> conversionService.convert(userEntity, UserResponse.class))
			.toList();
	}

	public UserResponse getUserByUsername(String username) {
		log.info("Fetching user with username {}", username);
		return userRepository.findByUsername(username).map(userEntity -> conversionService.convert(userEntity, UserResponse.class)).orElseThrow();
	}

	public void saveUser(UserRequest userDto) {
		log.info("Saving user {}", userDto);
		UserEntity userEntity = conversionService.convert(userDto, UserEntity.class);
		RoleEntity roleEntity = roleRepository.findByCode("USER");
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userEntity.setRole(roleEntity);
		userRepository.save(userEntity);
	}

	public void deleteUser(long id) {
		log.info("Deleting user with id {}", id);
		userRepository.deleteById(id);
	}
}
