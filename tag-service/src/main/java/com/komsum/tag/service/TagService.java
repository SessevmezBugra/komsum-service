package com.komsum.tag.service;


import com.komsum.tag.entity.TagEntity;

import java.util.List;

public interface TagService {

    TagEntity create(TagEntity tagEntity);

    TagEntity update(TagEntity tagEntity);

    void deleteById(String id);

    TagEntity findById(String id);

    List<TagEntity> findAll();
}
