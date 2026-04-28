package io.kidan.guardian.controller;

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

    @GetMapping("/datasets/new-dataset")
    public String newDataSetPage (Model model) {
        model.addAttribute("formCreatedDataset", new Dataset());

        return "guardian/new-dataset";
    }

    @GetMapping("/datasets/new-contract")
    public String newContractPage (Model model) {
        model.addAttribute("formCreatedContract", new Contract());

        return "guardian/new-contract";
    }

    @GetMapping("datasets/review-resource")
    public String newContractCreated(Model model) {
//        ArrayList <Object> resources =
//                new ArrayList <> (
//                        List.of (
//                                session.getAttribute("newDataset"),
//                                session.getAttribute("newContract")
//                            )
//                        );
//
//        session.setAttribute("allResources", resources);
//        model.addAttribute("resources", resources);
//
        return "guardian/resource-review";
    }

    @PostMapping("datasets/new-dataset/created")
    public String newDatasetCreated (@ModelAttribute Dataset newDataset) {
        session.setAttribute("newDataset", newDataset);

        return "guardian/new-contract";
    }

    @PostMapping("datasets/new-contract/created")
    public String newContractCreated(@ModelAttribute Contract newContract) {
        session.setAttribute("newContract", newContract);

        return "guardian/resource-review";
    }

    @PostMapping("datasets/resources/created")
    public String allResourcesCreated(Contract contract) {
        Dataset dataset = (Dataset) session.getAttribute("newDataset");
        ContractRule contractRule = (ContractRule) session.getAttribute("newContractRule");
        guardianService.createDatasetAndContract(dataset, contract ,contractRule);

        return "redirect:guardian/datasets";
    }

}
