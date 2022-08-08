package king.brandan.noteapp.auth.service;

import king.brandan.noteapp.auth.dto.UserDto;
import king.brandan.noteapp.auth.entities.RoleEntity;
import king.brandan.noteapp.auth.entities.UserEntity;
import king.brandan.noteapp.auth.mapper.UserMapper;
import king.brandan.noteapp.auth.repositories.RoleRepository;
import king.brandan.noteapp.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserMapper userMapper;
	// private final PasswordEncoder passwordEncoder;

	public List<UserDto> getAllUsers() {
		log.info("Fetching all users");
		return userRepository.findAll().stream()
			.map(userMapper::toDto)
			.toList();
	}

	public UserDto getUserById(long id) {
		log.info("Fetching user with id {}", id);
		return userRepository.findById(id).map(userMapper::toDto).orElseThrow();
	}

	public UserDto getUserByUsername(String username) {
		log.info("Fetching user with username {}", username);
		return userRepository.findByUsername(username).map(userMapper::toDto).orElseThrow();
	}

	public void saveUser(UserDto userDto) {
		log.info("Saving user {}", userDto);
		UserEntity userEntity = userMapper.toEntity(userDto);
		RoleEntity roleEntity = roleRepository.findByCode("USER");
		// userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userEntity.setRole(roleEntity);
		// userRepository.save(userEntity);
	}

	public void updateUser(long id, UserDto userDto) {
		log.info("Updating user with id {}", id);
		UserEntity userEntity = userMapper.toEntity(userDto);
		userRepository.save(userEntity);
	}

	public void deleteUser(long id) {
		log.info("Deleting user with id {}", id);
		userRepository.deleteById(id);
	}
}
