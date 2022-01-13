package com.komsum.user.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_ENTITY")
public class KeycloakUserEntity {

	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "USERNAME")
    private String username;
	
	@Column(name = "ENABLED")
    private Boolean enabled;
	
	@Column(name = "EMAIL_VERIFIED")
    private Boolean emailVerified;
	
	@Column(name = "LAST_NAME")
    private String lastName;
	
	@Column(name = "REALM_ID")
    private String realmId;
	
	@Column(name = "EMAIL")
    private String email;
	
	@Column(name = "FEDERATION_LINK")
    private String federationLink;
	
	@Column(name = "SERVICE_ACCOUNT_CLIENT_LINK")
    private String serviceAccountClientId;
	
	@JsonManagedReference
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<KeycloakUserAttributeEntity> attributes;
}
