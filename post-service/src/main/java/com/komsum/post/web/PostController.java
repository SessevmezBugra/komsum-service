package com.komsum.post.web;

import com.komsum.post.entity.PostEntity;
import com.komsum.post.service.PostService;
import com.komsum.post.util.constant.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.PostCtrl.CTRL)
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<PostEntity> createPost(@RequestBody PostEntity postEntity){
        return ResponseEntity.ok(postService.create(postEntity));
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<PostEntity> updatePost(@RequestBody PostEntity postEntity){
        return ResponseEntity.ok(postService.create(postEntity));
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity deletePost(@PathVariable(value="id", required=true) String id){
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
