package com.schema.multitenancy.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class User {

	private UUID userId;
	
	@NotBlank
	private String name;

	@NotBlank
	private String emailId;

}
