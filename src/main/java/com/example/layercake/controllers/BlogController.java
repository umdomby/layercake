package com.example.layercake.controllers;


import com.example.layercake.model.Blog;
import com.example.layercake.repo.RepoBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private RepoBlog repoBlog;

    @GetMapping("/blog")
    public String blogMain(Model model){
//        model.addAttribute("title", "О блоге");
        Iterable<Blog> posts = repoBlog.findAll();
        model.addAttribute("posts", posts);
        return "html-blog/blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        model.addAttribute("title", "Добавить статью");
        return "html-blog/blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        Blog post = new Blog(title, anons, full_text);
        repoBlog.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model){
        if(!repoBlog.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Blog> post = repoBlog.findById(id);
        ArrayList<Blog> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "html-blog/blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model){
        if(!repoBlog.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Blog> post = repoBlog.findById(id);
        ArrayList<Blog> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "html-blog/blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        Blog post = repoBlog.findById(id).orElseThrow(IllegalStateException::new);
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        repoBlog.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model){
        Blog post = repoBlog.findById(id).orElseThrow(IllegalStateException::new);
        repoBlog.delete(post);
        return "redirect:/blog";
    }


}
