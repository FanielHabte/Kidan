package io.kidan.guardian.controller;

import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.service.GuardianService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DatasetController {
    private final GuardianService guardianService;
    private final HttpSession session;

    DatasetController(GuardianService guardianService, HttpSession session) {
        this.guardianService = guardianService;
        this.session = session;
    }

    // Fetches and passes all datasets
    @GetMapping("/guardian/datasets")
    public String datasetsListPage(Model model) {
        model.addAttribute("all_datasets", guardianService.getAllDataSets());

        return "guardian/all-datasets";
    }

    // Creates and passes new dataset for form submission
    @GetMapping("/guardian/dataset/new")
    public String newDataSetPage(Model model) {
        model.addAttribute("new_dataset", new Dataset());

        return "guardian/new-dataset";
    }

    /*
        1. Collects submitted data,
        2. Adds collected dataset to session inorder to be accessed in the new-contract page
        2. Redirect user to new-contract page
     */
    @PostMapping("/guardian/dataset/new/create")
    public String newDatasetCreated(@ModelAttribute Dataset newDataset) {
        session.setAttribute("created_dataset", newDataset);

        return "redirect:/guardian/contract/new";
    }

    /*
      Try's to fetch dataset:
        1. If successful it passes dataset
        2. Else it passes the exception message to client
    */
    @GetMapping("guardian/dataset/detail/{id}")
    public String getDatasetDetail(@PathVariable String id, Model model) {
        try {
            Dataset requestedDataset = guardianService.getDatasetById(id);
            model.addAttribute("requested_dataset", requestedDataset);
        } catch (RuntimeException exception) {
            model.addAttribute("error", exception.getMessage());
        }

        return "guardian/dataset-detail";
    }

}
