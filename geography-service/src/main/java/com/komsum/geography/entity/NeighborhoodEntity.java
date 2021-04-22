package com.komsum.geography.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="NEIGHBORHOOD")
@NoArgsConstructor
@Getter
@Setter
public class NeighborhoodEntity implements Serializable {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NAME")
    private String name;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="DISTRICT_ID")
    private DistrictEntity district;

}
