package com.example.layercake.controllers;


import com.example.layercake.model.Message;
import com.example.layercake.repo.RepoMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeAboutController {

    @Autowired
    private RepoMessage repoMessage;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("title", "Main");
        return "home";
    }


    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }

    @GetMapping("/main")
    public String main2(Model model) {
        Iterable<Message> messages = repoMessage.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

}
