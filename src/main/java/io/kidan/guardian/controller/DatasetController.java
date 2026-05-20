package io.kidan.guardian.controller;

import io.kidan.guardian.entity.dataset.Dataset;
import io.kidan.guardian.service.dataset.DatasetService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DatasetController {
    private final DatasetService datasetService;
    private final HttpSession session;

    DatasetController(DatasetService datasetService, HttpSession session) {
        this.datasetService = datasetService;
        this.session = session;
    }

    // Fetches and passes all datasets
    @GetMapping("/guardian/datasets")
    public String datasetsListPage(Model model) {
        model.addAttribute("all_datasets", datasetService.findAllDataSets());

        return "guardian/dataset/all-datasets";
    }

    // Creates and passes new dataset for form submission
    @GetMapping("/guardian/dataset/new")
    public String newDataSetPage(Model model) {
        model.addAttribute("new_dataset", new Dataset());

        return "guardian/dataset/new-dataset";
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
    @GetMapping("/guardian/dataset/detail/{id}")
    public String getDatasetDetail(@PathVariable String id, Model model) {
        try {
            Dataset requestedDataset = datasetService.findDatasetById(id);
            model.addAttribute("requested_dataset", requestedDataset);
        } catch (RuntimeException exception) {
            model.addAttribute("error", exception.getMessage());
        }

        return "guardian/dataset/detail-dataset";
    }

    @GetMapping("/guardian/dataset/edit/{id}")
    public String editDataset(@PathVariable String id, Model model) {
        Dataset editableDataset = datasetService.findDatasetById(id);
        model.addAttribute("editable_dataset", editableDataset);

        return "guardian/dataset/edit-dataset";
    }

    @PostMapping("/guardian/dataset/save")
    public String saveEditedDataset(@RequestParam String id, @RequestParam String name) {
        datasetService.updateDatasetNameById(id, name);

        return "redirect:/guardian/datasets";
    }

}
