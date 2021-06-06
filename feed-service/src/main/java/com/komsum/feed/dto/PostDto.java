package com.komsum.feed.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostDto {

    private String postId;

    private String username;

    private Date createdAt;

    private Date updatedAt;

    private String content;

    private Integer streetId;

    private List<Integer> tagIds;

}
