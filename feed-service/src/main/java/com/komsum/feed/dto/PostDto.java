package com.komsum.feed.dto;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data
public class PostDto {

	private String id;

    private String username;

    private Instant createdAt;

    private Instant updatedAt;

    private String content;
    
    private Integer countryId;
    
    private String countryName;
    
    private Integer cityId;
    
    private String cityName;
    
    private Integer districtId;
    
    private String districtName;
    
    private Integer neighborhoodId;
    
    private String neighborhoodName;
    
    private Integer streetId;
    
    private String streetName;

    private List<String> tagIds;
    
    private String fileId;
}
