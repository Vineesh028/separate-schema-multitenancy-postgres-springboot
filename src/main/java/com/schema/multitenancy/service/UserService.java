package com.schema.multitenancy.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schema.multitenancy.entity.UserEntity;
import com.schema.multitenancy.model.User;
import com.schema.multitenancy.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {

		UserEntity newEntity = userRepository.save(modelMapper.map(user, UserEntity.class));

		return modelMapper.map(newEntity, User.class);
	}

	public List<User> getAllUsers() {

		return userRepository.findAll().stream().map(entity -> modelMapper.map(entity, User.class))
				.collect(Collectors.toList());

	}

	public User getUser(UUID id) {
		UserEntity entity = userRepository.findById(id).get();

		return modelMapper.map(entity, User.class);
	}

	public User updateUser(User user, UUID id) {
		UserEntity existingUser = userRepository.findById(id).get();
		existingUser.setName(user.getName());
		existingUser.setEmailId(user.getEmailId());
		UserEntity updatedEntity = userRepository.save(existingUser);

		return modelMapper.map(updatedEntity, User.class);
	}

	public void deleteUser(UUID id) {
		userRepository.deleteById(id);

	}

	public void deleteAllUsers() {
		userRepository.deleteAll();

	}

}
