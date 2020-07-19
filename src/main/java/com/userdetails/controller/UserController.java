package com.userdetails.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.userdetails.exception.UserNotFoundException;
import com.userdetails.model.User;
import com.userdetails.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@PostMapping("/save")
	public  ResponseEntity<User> createMovie(@Valid @RequestBody User user) {
		User savedUser = userServiceImpl.saveUser(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().pathSegment("/{id}")
	.buildAndExpand(savedUser.getUser_id()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllUser() {
		
		List<User> users= userServiceImpl.getAllUsers();
		if(users.isEmpty()) {
			throw new UserNotFoundException("no contacts are there!");
		}
		else {
		
		return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<User> getUserByID(@PathVariable("id") long id) {
		
		User userFindByID= userServiceImpl.getUserByID(id);
		if(userFindByID==null) {
			throw new UserNotFoundException(id+" contact not found");
		}
		else {
		return new ResponseEntity<User>(userFindByID, HttpStatus.FOUND);
		}
	}
	
	@PutMapping("/get/{uid}") 
	  public ResponseEntity<User> updateMovie(@PathVariable("uid") Long uid, @RequestBody User userinfo)
	  { 
	  User user = userServiceImpl.getUserByID(uid);
	  BeanUtils.copyProperties(userinfo, user); 
	  userServiceImpl.saveUser(user);
	  //return
	   URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().build()
				.toUri();
	  	return	ResponseEntity.created(location).build();
	  
	  }
	
	  @DeleteMapping("/get/{uid}")
			public ResponseEntity<User> deleteUser(@PathVariable("uid") Long mid) {
				User userFindByID= userServiceImpl.getUserByID(mid);
			
				if(userFindByID==null) {
					throw new UserNotFoundException(mid+" contact not found");
				}
				else {
					userServiceImpl.deleteUserByID(mid);
				return new ResponseEntity<User>(HttpStatus.OK);
				}
			}


}
