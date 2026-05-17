package io.kidan.inlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InletController {

    @GetMapping("/inlet/submissions")
    public String submissions() {

        return "inlet/all-submissions";
    }

    @GetMapping("/inlet/submission/new")
    public String newSubmission() {

        return "inlet/new-submission";
    }

}
