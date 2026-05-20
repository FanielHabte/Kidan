package io.kidan.guardian.controller;

import io.kidan.guardian.entity.dataset.Dataset;
import io.kidan.guardian.dto.csv.CsvFormWrapper;
import io.kidan.guardian.service.contract.ContractService;
import io.kidan.guardian.service.csv.CsvContractService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContractController {

    private final ContractService contractService;
    private final CsvContractService csvFileService;
    private final HttpSession session;

    ContractController(ContractService contractService
                        , CsvContractService csvFileService
                        , HttpSession session) {
        this.contractService = contractService;
        this.csvFileService = csvFileService;
        this.session = session;
    }

    @GetMapping("/guardian/contracts")
    public String getAllContracts(Model model) {
        model.addAttribute("all_contracts", contractService.findAllContractPageSummaries());

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
        csvFileService.createDatasetAndContract(dataset, wrapper);

        return "redirect:/guardian/datasets";
    }

    @GetMapping("/guardian/contract/detail/{id}")
    public String getContractDetail(@PathVariable String id, Model model) {
        try {
            model.addAttribute("contract",contractService.findContractById(id));
        }
        catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "guardian/contract/detail-contract";
    }

}
