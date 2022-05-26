package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "jk");
        return "greetings";     // templates/greetings.mustache -> 브라우저로 전송
                                // return 값은 보여줄 view template 페이지
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "jk");
        return "goodBye";
    }

    @GetMapping("/hello")
    public String test() {
        return "hello.html";
    }
}
