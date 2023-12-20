package com.example.demo.controller;
import com.example.demo.service.postservice;
import com.example.demo.payload.postdto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private  postservice postservice;
    public PostController(com.example.demo.service.postservice postservice) {
        this.postservice = postservice;
    }
    @PostMapping
    public ResponseEntity<postdto> createpost(@RequestBody postdto postdto){
        return new ResponseEntity<>(postservice.createpost(postdto), HttpStatus.CREATED);
    }
    @GetMapping
    public List<postdto>  getallpost(){
        return postservice.getallpost();

    }
    @GetMapping("/{id}")
    public ResponseEntity<postdto>getpostbyid(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(postservice.getpostbyid(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<postdto>updatepost(@RequestBody postdto postdto ,@PathVariable(name = "id")long id){
        postdto postResponse=postservice.updatepost(postdto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
}