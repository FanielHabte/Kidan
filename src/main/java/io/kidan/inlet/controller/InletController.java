package io.kidan.inlet.controller;

import io.kidan.inlet.entity.Submission;
import io.kidan.inlet.service.InletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InletController {

    private final InletService inletService;

    InletController(InletService inletService) {
        this.inletService = inletService;
    }

    @GetMapping("/inlet/submissions")
    public String submissions(Model model) {
        model.addAttribute("submissions", inletService.getSubmissionList());

        return "inlet/all-submissions";
    }

    @GetMapping("/inlet/submission/new")
    public String newSubmission(Model model) {
        model.addAttribute("all_datasets", inletService.getDatasetList());
        model.addAttribute("new_submission", new Submission());

        return "inlet/new-submission";
    }

}
