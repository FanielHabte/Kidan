package io.kidan.gateway;

import io.kidan.guardian.service.GuardianService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KidanController {

    private final GuardianService guardianService;

    KidanController (GuardianService guardianService) {
        this.guardianService = guardianService;
    }

    @GetMapping ("/Datasets")
    public String listDatasets (Model model) {
        model.addAttribute("datasetList",guardianService.getAllDataSets());

        return "GuardianPages/Datasets";
    }

    @GetMapping ("/signup")
    public String signUpPage (Model model) {
        model.addAttribute("datasetList",guardianService.getAllDataSets());

        return "NexusPages/SignUp";
    }

}
