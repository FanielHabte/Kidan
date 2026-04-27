package io.kidan.sage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SageController {

    @GetMapping("/document-qa")
    public String documentQa () {

        return "sage/document-qa";
    }
}
