package com.userdetails.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class UserEntity {
	
	@Id
	@SequenceGenerator(name = "uid_seq_gen", sequenceName = "USER_ID_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "uid_seq_gen", strategy = GenerationType.SEQUENCE)
	private Long user_id;
	
	private String user_name;
	private String user_email;
	private String user_address;

}
