package com.komsum.tag.service.impl;

import com.komsum.tag.entity.TagEntity;
import com.komsum.tag.repository.TagRepository;
import com.komsum.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public TagEntity create(TagEntity tagEntity) {
        return tagRepository.save(tagEntity);
    }

    @Override
    public TagEntity update(TagEntity tagEntity) {
        return tagRepository.save(tagEntity);
    }

    @Override
    public void deleteById(String id) {
        tagRepository.deleteById(id);
    }

    @Override
    public TagEntity findById(String id) {
        return tagRepository.findById(id).get();
    }

    @Override
    public List<TagEntity> findAll() {
        return tagRepository.findAll();
    }

	@Override
	public Iterable<TagEntity> findByIdIn(List<String> ids) {
		return tagRepository.findAllById(ids);
	}

}
