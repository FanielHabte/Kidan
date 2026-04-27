package io.kidan.nexus.controller;

import io.kidan.nexus.entity.User;
import io.kidan.nexus.service.NexusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NexusController {

    private final NexusService nexusService;

    NexusController (NexusService nexusService) {
        this.nexusService = nexusService;
    }

    @GetMapping("/login")
    public String loginPage (Model model) {
        model.addAttribute("newUser", new User());

        return "nexus/login";
    }

    @GetMapping("/signup")
    public String signUpPage (Model model) {
        model.addAttribute("newUser", new User());

        return "nexus/signup";
    }

    @GetMapping("/team-management")
    public String teamManagement () {

        return "nexus/team-management";
    }

    @PostMapping("/signup/create-user")
    public String createNewUser (@ModelAttribute User user) {
        nexusService.createUser(user);

        return "redirect:guradian/datasets";
    }

}
