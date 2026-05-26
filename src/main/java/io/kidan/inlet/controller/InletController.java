package io.kidan.inlet.controller;

import io.kidan.guardian.service.DatasetService;
import io.kidan.inlet.entity.Submission;
import io.kidan.inlet.service.FilesStorageService;
import io.kidan.inlet.service.InletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class InletController {

    private final InletService inletService;
    private final DatasetService datasetService;
    private final FilesStorageService filesStorageService;

    InletController(InletService inletService, DatasetService datasetService, FilesStorageService filesStorageService) {
        this.inletService = inletService;
        this.datasetService = datasetService;
        this.filesStorageService = filesStorageService;
    }

    @GetMapping("/inlet/submissions")
    public String submissions(Model model) {
        model.addAttribute("submissions", inletService.findAllSubmissions());

        return "inlet/all-submissions";
    }

    @GetMapping("/inlet/submission/new")
    public String newSubmission(Model model) {
        model.addAttribute("all_datasets", datasetService.findAllDataSets());
        model.addAttribute("new_submission", new Submission());

        return "inlet/new-submission";
    }

    @PostMapping("/inlet/submission/new/file/upload")
    public String fileUploadHandler(@RequestParam("file") MultipartFile inputFile,
                                    @ModelAttribute("new_submission") Submission submission,
                                    RedirectAttributes redirectAttributes) {
        try {
            inletService.saveSubmission(submission, inputFile);
            filesStorageService.saveFile(inputFile);
            return "redirect:/inlet/submissions";
        }
        catch (IOException e) {
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            redirectAttributes.addFlashAttribute("new_submission", submission);
            return "redirect:/inlet/submission/new";
        }
    }

}
