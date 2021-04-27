package com.komsum.geography.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="DISTRICT")
@NoArgsConstructor
@Getter
@Setter
public class DistrictEntity implements Serializable {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NAME")
    private String name;

    @JsonBackReference
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="CITY_ID")
    private CityEntity city;

    @Column(name="CITY_NAME")
    private String cityName;

}
