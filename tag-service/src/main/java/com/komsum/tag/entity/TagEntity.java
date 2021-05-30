package com.komsum.tag.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document
public class TagEntity {

    @Id
    private String id;

    private String key;

    private String trDesc;

    private String enDesc;
}
