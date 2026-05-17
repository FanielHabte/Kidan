package io.kidan.guardian.controller;

import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.service.GuardianService;
import io.kidan.guardian.web.dto.csv.CsvFormWrapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContractController {

    private final GuardianService guardianService;
    private final HttpSession session;

    ContractController(GuardianService guardianService, HttpSession session) {
        this.guardianService = guardianService;
        this.session = session;
    }

    @GetMapping("/guardian/contracts")
    public String getAllContracts(Model model) {
        model.addAttribute("all_contracts", guardianService.contractsPageViewList());

        return "guardian/contract/all-contracts";
    }

    /*
     1. Gets the dataset that was created from the session
     2. Created a new CsvFormWrapper class
        ** Spring boot binder handles the auto initialization of object in the wrapper **
    */
    @GetMapping("/guardian/contract/new")
    public String newContractPage(Model model) {
        Dataset createdDataset = (Dataset) session.getAttribute("created_dataset");
        CsvFormWrapper newFormWrapper = new CsvFormWrapper();

        model.addAttribute("new_contract_form", newFormWrapper);
        model.addAttribute("created_dataset", createdDataset);

        return "guardian/contract/new-contract";
    }

    /*
        ** Adding a post middle step to add submission from page into session **
        1. Collects Dataset & ContractRuleWrapper from the new-contract & new-dataset submissions
        2. Adds ContractRuleWrapper to session inorder to be accessed for review
        3. Redirect client to resource-review page
    */
    @PostMapping("/guardian/contract/new/create")
    public String newContractCreated(@ModelAttribute CsvFormWrapper createdFormWrapper) {
        session.setAttribute("created_contract_form", createdFormWrapper);

        return "redirect:/guardian/review/resources";
    }

    /*
        1. Collects dataset and wrapper class from session
        2. Passes created resources to client
    */
    @GetMapping("/guardian/review/resources")
    public String newContractCreated(Model model) {
        Dataset createdDataset = (Dataset) session.getAttribute("created_dataset");
        CsvFormWrapper createdContractForm = (CsvFormWrapper) session.getAttribute("created_contract_form");
        model.addAttribute("created_dataset", createdDataset);
        model.addAttribute("created_contract_form", createdContractForm);

        return "guardian/contract/resource-review";
    }

    /*
        1. Collects dataset and wrapper class from session
        2. Creates new contract object
        2. Pass all three resources to service for database submission
    */
    @PostMapping("/guardian/resources/create")
    public String allResourcesCreated() {
        Dataset dataset = (Dataset) session.getAttribute("created_dataset");
        CsvFormWrapper wrapper = (CsvFormWrapper) session.getAttribute("created_contract_form");
        guardianService.createDatasetAndContract(dataset, wrapper);

        return "redirect:/guardian/datasets";
    }

}
