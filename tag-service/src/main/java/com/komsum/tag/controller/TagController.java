package com.komsum.tag.controller;

import com.komsum.tag.entity.TagEntity;
import com.komsum.tag.service.TagService;
import com.komsum.tag.util.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPath.TagCtrl.CTRL)
@RestController
@RequiredArgsConstructor
public class TagController implements SecuredRestController{

    private final TagService tagService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<TagEntity> createTag(@RequestBody TagEntity tagEntity){
        return ResponseEntity.ok(tagService.create(tagEntity));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<TagEntity> updateTag(@RequestBody TagEntity tagEntity){
        return ResponseEntity.ok(tagService.update(tagEntity));
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<TagEntity> deleteTag(@RequestBody TagEntity tagEntity){
        return ResponseEntity.ok(tagService.update(tagEntity));
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<TagEntity> getTagById(@PathVariable(value="id", required=true) String id){
        return ResponseEntity.ok(tagService.findById(id));
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<TagEntity>> getAllTags(){
        return ResponseEntity.ok(tagService.findAll());
    }
    
    @RequestMapping(value="/in", method= RequestMethod.POST)
    public ResponseEntity<Iterable<TagEntity>> getTagsByIdIn(@RequestBody List<String> ids){
        return ResponseEntity.ok(tagService.findByIdIn(ids));
    }
}
