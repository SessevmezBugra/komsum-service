package com.komsum.feed.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeycloakUserDto {

	private String id;
	
	private String firstName;

    private String username;
	
    private Boolean enabled;
	
    private Boolean emailVerified;
	
    private String lastName;
	
    private String email;
	
    private String federationLink;
	
    private String serviceAccountClientId;
	
	@JsonManagedReference
    private List<KeycloakUserAttributeDto> attributes;
	
}
