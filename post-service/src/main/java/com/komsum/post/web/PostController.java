package com.komsum.post.web;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.komsum.post.dto.PostDto;
import com.komsum.post.entity.PostEntity;
import com.komsum.post.service.PostService;
import com.komsum.post.util.constant.ApiPath;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPath.PostCtrl.CTRL)
@RequiredArgsConstructor
public class PostController implements SecuredRestController{

    private final PostService postService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<PostEntity> createPost(@RequestParam("file") MultipartFile file, @RequestPart("body") String body) throws IOException {
    	ObjectMapper objectMapper = new ObjectMapper();
		PostDto postDto = objectMapper.readValue(body, PostDto.class);
    	return ResponseEntity.ok(postService.create(postDto, file));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<PostEntity> updatePost(@RequestBody PostEntity postEntity){
        return ResponseEntity.ok(postService.update(postEntity));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> deletePost(@PathVariable(value="id", required=true) String id){
        postService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<PostEntity> getPostById(@PathVariable(value="id", required=true) String id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<PostEntity>> getAllPost(){
        return ResponseEntity.ok(postService.findAll());
    }

    @RequestMapping(value="/user/{username}", method=RequestMethod.GET)
    public ResponseEntity<List<PostEntity>> getPostsByUsername(@PathVariable(value="username", required=true) String username){
        return ResponseEntity.ok(postService.findByUsername(username));
    }

    @RequestMapping(value="/in", method= RequestMethod.POST)
    public ResponseEntity<List<PostEntity>> getPostsByIdIn(@RequestBody List<String> ids){
        return ResponseEntity.ok(postService.findPostsByIdIn(ids));
    }
}
