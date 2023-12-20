package com.example.demo.controller;

import com.example.demo.service.postservice;
import com.example.demo.payload.postdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/posts")
public class PostTempletController {

    private final postservice postService;

    @Autowired
    public PostTempletController(postservice postService) {
        this.postService = postService;
    }

    @GetMapping("/new")
    public String showPostForm(Model model) {
        model.addAttribute("postDto", new postdto());
        return "form";
    }

    @PostMapping("/new")
    public String createOrUpdatePost(@ModelAttribute postdto postDto) {
        if (postDto.getId()== 0) {

            postDto.setContent("content");
            postService.createpost(postDto);
        } else {

            postService.updatepost(postDto, postDto.getId());
        }
        return "redirect:/posts";
    }

    @GetMapping
    public String getAllPosts(Model model) {
        List<postdto> posts = postService.getallpost();
        model.addAttribute("posts", posts);
        return "list";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable(name = "id") long id, Model model) {
        postdto post = postService.getpostbyid(id);
        model.addAttribute("post", post);
        return "details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable(name = "id") long id, Model model) {
        postdto post = postService.getpostbyid(id);
        model.addAttribute("postDto", post);
        return "form";
    }
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable(name = "id") long id) {
        postService.deletepost(id);
        return "redirect:/posts";
    }

}
