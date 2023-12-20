package com.example.demo.service.impl;
import com.example.demo.entity.post;
import com.example.demo.exception.resourcenotfound;
import com.example.demo.payload.postdto;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.postservice;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class postserviceimpl implements postservice {
    private PostRepository postRepository;
    public postserviceimpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public postdto createpost(postdto postdto){
        post post=maptoentity(postdto);
        post newpost= postRepository.save(post);
        postdto postresponse=maptodto(newpost);
        return postresponse;
    }
    @Override
    public List<postdto> getallpost() {
        List<post>posts=postRepository.findAll();
        return  posts.stream().map(post -> maptodto(post)).collect(Collectors.toList());
    }
    @Override
    public postdto getpostbyid(long id) {
        post post =postRepository.findById(id).orElseThrow(()->new resourcenotfound("post","id",id));
        return maptodto(post);
    }

    @Override
    public postdto updatepost(postdto postdto, long id) {
        post post =postRepository.findById(id).orElseThrow(()->new resourcenotfound("post","id",id));
        post.setTitle(postdto.getTitle());
        post.setContent(postdto.getContent());
        post.setDescription(postdto.getDescription());
        post updatepost=postRepository.save(post);
        return maptodto(updatepost);
    }
    @Override
    public void deletepost(long id) {

        postRepository.findById(id).orElseThrow(() -> new resourcenotfound("post", "id", id));


        postRepository.deleteById(id);
    }
    private postdto maptodto(post post){

        postdto postdto=new postdto();
        postdto.setId(post.getId());
        postdto.setTitle(post.getTitle());
        postdto.setDescription(post.getDescription());
        postdto.setContent(post.getContent());
        return postdto;
    }
    private post maptoentity(postdto postdto){
        post post=new post();
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());
        return post;
    }

}
