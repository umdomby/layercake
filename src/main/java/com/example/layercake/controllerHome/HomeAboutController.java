package com.example.layercake.controllerHome;


import com.example.layercake.model.Blog;
import com.example.layercake.model.Message;
import com.example.layercake.repo.RepoBlog;
import com.example.layercake.repo.RepoMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeAboutController {

    @Autowired
    private RepoMessage repoMessage;

    @Autowired
    private RepoBlog repoBlog;

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

    @GetMapping("/home-test")
    public String main2(Model model) {
        model.addAttribute("title", "Main2");
        return "home-test";
    }

    @GetMapping("/blog-main-test")
    public String blogTest(Model model){
        model.addAttribute("title", "Home controllerHome");
        Iterable<Blog> posts = repoBlog.findAll();
        model.addAttribute("posts", posts);
        return "blog-main-test";
    }

    @GetMapping("/message-main-test")
    public String messageTest(Model model) {
        Iterable<Message> messages = repoMessage.findAll();
        model.addAttribute("messages", messages);
        return "message-main-test";
    }

}
