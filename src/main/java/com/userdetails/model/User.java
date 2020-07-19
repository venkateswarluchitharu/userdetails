package com.userdetails.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class User {
	
	private Long user_id;
	 @Size(min=4,max=10)
	 private String user_name;
	 @Email
	 private String user_email;
	 @NotNull
	 private String user_address;

}
