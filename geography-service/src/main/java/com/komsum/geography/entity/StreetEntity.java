package com.komsum.geography.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="STREET")
@NoArgsConstructor
@Getter
@Setter
public class StreetEntity implements Serializable {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NAME")
    private String name;

    @JsonBackReference
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="NEIGHBORHOOD_ID")
    private NeighborhoodEntity neighborhood;

    @Column(name="NEIGHBORHOOD_NAME")
    private String neighborhoodName;

    @Column(name="DISTRICT_ID")
    private Integer districtId;

    @Column(name="DISTRICT_NAME")
    private String districtName;

    @Column(name="CITY_ID")
    private Integer cityId;

    @Column(name="CITY_NAME")
    private String cityName;
}
