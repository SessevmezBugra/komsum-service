package com.komsum.feed.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeycloakUserAttributeDto {

	private String id;

	@JsonBackReference
	private KeycloakUserDto user;
	
	private String name;
	
	private String value;
	
}
