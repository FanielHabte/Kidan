package io.kidan.guardian.controller;

import io.kidan.guardian.dto.csv.ContractFileStructure;
import io.kidan.guardian.dto.csv.ContractRuleFormString;
import io.kidan.guardian.dto.csv.ContractRuleFormWrapper;
import io.kidan.guardian.entity.Contract;
import io.kidan.guardian.entity.ContractRule;
import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.service.GuardianService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuardianController {

    private final GuardianService guardianService;
    private final HttpSession session;

    GuardianController (GuardianService guardianService, HttpSession session) {
        this.guardianService = guardianService;
        this.session = session;
    }

    @GetMapping ("/datasets")
    public String datasetsListPage (Model model) {
        model.addAttribute("datasetList", guardianService.getAllDataSets());

        return "guardian/datasets";
    }

    @GetMapping ("/datasets/detail")
    public String datasetDetail (Model model) {
        model.addAttribute("datasetList", guardianService.getAllDataSets());

        return "guardian/dataset-detail";
    }

    @GetMapping("/datasets/dataset/new")
    public String newDataSetPage (Model model) {
        model.addAttribute("formCreatedDataset", new Dataset());

        return "guardian/new-dataset";
    }

    @PostMapping("/datasets/dataset/new/create")
    public String newDatasetCreated (@ModelAttribute Dataset newDataset) {
        session.setAttribute("newDataset", newDataset);

        return "redirect:/datasets/contract/new";
    }

    /*
    In each new contract page refresh we:
     1. Get the dataset that was created from the session
     2. Pass a new ContractFileStructure object
     2. Pass a new StringContractForm as the first item in the contractFormList found in the Wrapper class.
        ** This is done be getting the list from the wrapper, adding a new StringForm object
           and replacing the list in the object with the new one **
    */
    @GetMapping("/datasets/contract/new")
    public String newContractPage (Model model) {
        Dataset newDataset = (Dataset)  session.getAttribute("newDataset");
        ContractRuleFormWrapper wrapper = new ContractRuleFormWrapper();

        wrapper.getContractRuleFormList().add(new ContractRuleFormString());
        wrapper.setContractFileStructure(new ContractFileStructure());

        model.addAttribute("contractFormWrapper",wrapper);
        model.addAttribute("newDatasetCreated",newDataset);

        return "guardian/new-contract";
    }

    /*
        Collects ContractRuleWrapper from the new-contract submission and the dataset from session and passes
        it to the page review page.
    */
    @PostMapping("/datasets/contract/new/create")
    public String newContractCreated(@ModelAttribute ContractRuleFormWrapper contractRuleFormWrapper, Model model) {
        session.setAttribute("contractWrapper", contractRuleFormWrapper);

        return "redirect:/datasets/review/resources";
    }

    @GetMapping("/datasets/review/resources")
    public String newContractCreated(Model model) {
        Dataset newDataset = (Dataset) session.getAttribute("newDataset");
        ContractRuleFormWrapper wrapper = (ContractRuleFormWrapper) session.getAttribute("contractWrapper");
        model.addAttribute("newDataset", newDataset );
        model.addAttribute("contractWrapper", wrapper);

        return "guardian/resource-review";
    }


    @PostMapping("/datasets/resources/create")
    public String allResourcesCreated() {
        Dataset dataset = (Dataset) session.getAttribute("newDataset");
        ContractRuleFormWrapper wrapper = (ContractRuleFormWrapper) session.getAttribute("contractWrapper");
        Contract contract = new Contract();
        ContractRule contractRule = new ContractRule();
        System.out.println(wrapper);

        guardianService.createDatasetAndContract(dataset, contract, wrapper, contractRule);

        return "redirect:/datasets";
    }

}
