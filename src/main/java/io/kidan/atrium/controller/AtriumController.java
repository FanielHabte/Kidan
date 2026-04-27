package io.kidan.atrium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AtriumController {

    @GetMapping("/")
    public String homePage() {

        return "/atrium/home";
    }

    @GetMapping("/settings")
    public String settingsPage() {

        return "/atrium/settings";
    }

}
