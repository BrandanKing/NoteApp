package king.brandan.noteapp.auth.service;
import king.brandan.noteapp.auth.dto.UserDto;
import king.brandan.noteapp.auth.entities.UserEntity;
import king.brandan.noteapp.auth.mapper.UserMapper;
import king.brandan.noteapp.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	// private final PasswordEncoder passwordEncoder;

	public void addUser(UserEntity userEntity) {
		log.info("Saving new user {} to the database", userEntity.getUsername());
		// userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userRepository.save(userEntity);
	}

	public UserDto getUser(String username) {
		log.info("Fetching user {}", username);
		return userRepository.findByUsername(username).map(userMapper::toDto).orElse(null);
	}

	public List<UserDto> getUsers() {
		log.info("Fetching all users");
		return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
	}
}
