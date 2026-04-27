package io.kidan.guardian.controller;

import io.kidan.guardian.service.GuardianService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuardianController {

    private final GuardianService guardianService;

    GuardianController (GuardianService guardianService) {
        this.guardianService = guardianService;
    }

    @GetMapping ("/datasets")
    public String listDatasets (Model model) {
        model.addAttribute("datasetList",guardianService.getAllDataSets());

        return "guardian/datasets";
    }

}
