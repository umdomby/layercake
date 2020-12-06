package com.example.layercake.controllers;

import com.example.layercake.model.Message;
import com.example.layercake.repo.RepoMessage;
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
public class MessageController {
    @Autowired
    private RepoMessage repoMessage;


    @GetMapping("/message")
    public String main(Model model) {
        Iterable<Message> messages = repoMessage.findAll();
        model.addAttribute("messages", messages);
        return "html-message/message-main";
    }

    @GetMapping("/message/add")
    public String blogAdd(Model model){
        model.addAttribute("title", "Добавить статью");
        return "html-message/message-add";
    }

    @PostMapping("/message/add")
    public String add(@RequestParam String text, @RequestParam String tag, @RequestParam String tag2, Model model) {
        Message message = new Message(text, tag, tag2);
        repoMessage.save(message);
        Iterable<Message> messages = repoMessage.findAll();
        model.addAttribute("messages", messages);
        return "redirect:/message";
    }

    @GetMapping("/message/{id}")
    public String messageDetails(@PathVariable(value = "id") long id, Model model){
        if(!repoMessage.existsById(id)){
            return "redirect:/message";
        }
        Optional<Message> postmessage = repoMessage.findById(id);
        ArrayList<Message> res = new ArrayList<>();
        postmessage.ifPresent(res::add);
        model.addAttribute("postmessage", res);
        return "html-message/message-details";
    }

    @GetMapping("/message/{id}/edit")
    public String messageEdit(@PathVariable(value = "id") long id, Model model){
        if(!repoMessage.existsById(id)){
            return "redirect:/message";
        }
        Optional<Message> postmessage = repoMessage.findById(id);
        ArrayList<Message> res = new ArrayList<>();
        postmessage.ifPresent(res::add);
        model.addAttribute("postmessage", res);
        return "html-message/message-edit";
    }

    @PostMapping("/message/{id}/edit")
    public String messageUpdate(@PathVariable(value = "id") long id, @RequestParam String text, @RequestParam String tag, @RequestParam String tag2, Model model){
        Message postmessage = repoMessage.findById(id).orElseThrow(IllegalStateException::new);
        postmessage.setText(text);
        postmessage.setTag(tag);
        postmessage.setTag2(tag2);
        repoMessage.save(postmessage);
        return "redirect:/message";
    }

    @PostMapping("/message/{id}/remove")
    public String messageDelete(@PathVariable(value = "id") long id, Model model){
        Message postmessage = repoMessage.findById(id).orElseThrow(IllegalStateException::new);
        repoMessage.delete(postmessage);
        return "redirect:/blog";
    }

}
