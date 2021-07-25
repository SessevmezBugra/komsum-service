package com.komsum.feed.dto;

import lombok.Data;

@Data
public class StreetDto {
	
    private Integer id;
    private String name;
    private String neighborhoodName;
    private Integer districtId;
    private String districtName;
    private Integer cityId;
    private String cityName;

}
