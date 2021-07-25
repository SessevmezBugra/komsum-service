package com.komsum.geography.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="NEIGHBORHOOD")
@NoArgsConstructor
@Getter
@Setter
public class NeighborhoodEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NAME")
    private String name;

    @JsonBackReference
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="DISTRICT_ID")
    private DistrictEntity district;

    @Column(name="DISTRICT_NAME")
    private String districtName;

    @Column(name="CITY_ID")
    private Integer cityId;

    @Column(name="CITY_NAME")
    private String cityName;


}
