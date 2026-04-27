package io.kidan.inlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InletController {

    @GetMapping("/submissions")
    public String submissions() {

        return "inlet/submissions";
    }

}
