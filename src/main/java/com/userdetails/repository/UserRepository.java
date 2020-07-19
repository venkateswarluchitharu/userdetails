package com.userdetails.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userdetails.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Serializable>{

}
