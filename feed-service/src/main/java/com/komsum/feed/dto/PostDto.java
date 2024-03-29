package com.komsum.feed.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PostDto {

	private String id;

    private String username;
    
    private String firstName;
    
    private String lastName;
    
    private String profilePictureId;

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

    private List<TagDto> tags = new ArrayList<TagDto>();
    
    private String fileId;
    
    public void addTagg(TagDto tag) {
    	this.tags.add(tag);
    }
}
