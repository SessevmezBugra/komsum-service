package com.komsum.geography.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="CITY")
@NoArgsConstructor
@Getter
@Setter
public class CityEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NAME")
    private String name;

//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.LAZY, mappedBy="city")
//    private List<DistrictEntity> districts;
}
