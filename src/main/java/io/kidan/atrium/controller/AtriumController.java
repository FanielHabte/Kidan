package io.kidan.atrium.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AtriumController {

    @GetMapping("/")
    public String homePage(HttpSession session, Model model) {
        Object testInt = session.getAttribute("Test");

        if (testInt == null || testInt.toString().isBlank()) {
            session.setAttribute("Test", 1);
            model.addAttribute("Test", 1);
        }
        else {
            model.addAttribute("Test", testInt);
        }

        return "/atrium/home";
    }

    @GetMapping("/settings")
    public String settingsPage() {

        return "/atrium/settings";
    }

}
