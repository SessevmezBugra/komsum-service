package com.komsum.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER_ATTRIBUTE")
public class KeycloakUserAttributeEntity {

	@Id
	@Column(name = "ID")
	private String id;
	
	@JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID", referencedColumnName = "ID")
    private KeycloakUserEntity user;

	@Column(name = "NAME")
	private String name;

	@Column(name = "VALUE")
	private String value;

}
