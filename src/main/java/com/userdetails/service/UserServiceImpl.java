package com.userdetails.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userdetails.entity.UserEntity;
import com.userdetails.model.User;
import com.userdetails.repository.UserRepository;

@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public User saveUser(User user) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		UserEntity saveEntity = userRepo.save(userEntity);
		BeanUtils.copyProperties(saveEntity, user);
		return user;
		
	}
	@Override
	public List<User> getAllUsers() {
		List<UserEntity> findAll = userRepo.findAll();

		return findAll.stream().map(userEntity -> {
			User user = new User();
			BeanUtils.copyProperties(userEntity, user);
			return user;
		}).collect(Collectors.toList()).stream()
				.collect(Collectors.toList());

	}
	
	@Override
	public User getUserByID(Long id) {
		
		Optional<UserEntity> findById = userRepo.findById(id);
		if(findById.isPresent()) {
			UserEntity userEntity = findById.get();
	
			User user = new User();
		
			BeanUtils.copyProperties(userEntity, user);
			return user;
		}
		return null;
	}
	
	@Override
	public void deleteUserByID(Long id) {
		userRepo.deleteById(id);
		
	}


}
