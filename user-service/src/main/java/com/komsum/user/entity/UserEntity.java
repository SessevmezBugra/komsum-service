package com.komsum.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="USER")
public class UserEntity {
	
	@Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="USER_ID")
    private String userId;
	
	@Column(name="USER_NAME")
    private String username;
	
	@Column(name="PROFILE_PICTURE_ID")
    private String profilePictureId;

}
