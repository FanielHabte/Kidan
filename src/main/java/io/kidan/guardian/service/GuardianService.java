package io.kidan.guardian.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import io.kidan.fortress.service.UserAuthService;
import io.kidan.guardian.dto.csv.*;
import io.kidan.guardian.entity.Contract;
import io.kidan.guardian.entity.ContractRule;
import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.enums.RuleType;
import io.kidan.guardian.repository.ContractRepository;
import io.kidan.guardian.repository.ContractRuleRepository;
import io.kidan.guardian.repository.DatasetRepository;
import io.kidan.nexus.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardianService {

    private final DatasetRepository datasetRepository;
    private final ContractRepository contractRepository;
    private final ContractRuleRepository contractRuleRepository;
    private final UserAuthService userAuthService;

    GuardianService(DatasetRepository datasetRepository,
                    ContractRepository contractRepository,
                    ContractRuleRepository contractRuleRepository,
                    UserAuthService userAuthService) {
        this.datasetRepository = datasetRepository;
        this.contractRepository = contractRepository;
        this.contractRuleRepository = contractRuleRepository;
        this.userAuthService = userAuthService;
    }

    public List<Dataset> getAllDataSets() {
        return datasetRepository.findAll();
    }

    public void createDatasetAndContract(@NonNull Dataset dataset, @NonNull Contract contract, @NonNull ContractRuleFormWrapper wrapper, ContractRule contractRule) throws UsernameNotFoundException {
        User currentUser = userAuthService
                .AuthenticatedUser()
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Authenticated User not found"
                        )
                );

        if (resourcesValidation(dataset, contract, wrapper)) {
            dataset.setUser(currentUser);
            datasetRepository.save(dataset);

            contract.setDataset(dataset);
            contractRepository.save(contract);

            contractRule.setRuleConfig(createRuleConfig(wrapper));
            contractRule.setContract(contract);
            contractRuleRepository.save(contractRule);
        } else {
            throw new RuntimeException("Error occurred");
        }

    }

    public boolean resourcesValidation(Dataset dataset, Contract contract, ContractRuleFormWrapper wrapper) {
        return !wrapper.getContractRuleFormList().isEmpty() && contract != null && dataset != null;
    }

    public String createRuleConfig(ContractRuleFormWrapper wrapper) {
        ContractRuleFormWrapper correctWrapper = convertToCorrectSubTypes(wrapper);
        RuntimeTypeAdapterFactory<ContractRuleForm> adapterFactory = RuntimeTypeAdapterFactory
                .of(ContractRuleForm.class, "type")
                .registerSubtype(ContractRuleFormDate.class, "Date")
                .registerSubtype(ContractRuleFormString.class, "String")
                .registerSubtype(ContractRuleFormNumeric.class, "Numeric");
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(adapterFactory)
                .create();

        return gson.toJson(correctWrapper);
    }

    public ContractRuleFormWrapper convertToCorrectSubTypes(ContractRuleFormWrapper wrapper) {
        List<ContractRuleForm> listContractForm = wrapper.getContractRuleFormList();
        for (int i = 0; i < wrapper.getContractRuleFormList().size(); i++) {
            ContractRuleForm formInIndex = wrapper.getContractRuleFormList().get(i);

            if (formInIndex.getRuleType() == RuleType.KEYWORD_RULE) {
                ContractRuleFormString formString = new ContractRuleFormString();
                formString.setKeywords(formInIndex.);
                listContractForm.set(i, formString);
            } else if (formInIndex.getRuleType() == RuleType.RANGE) {
                ContractRuleFormNumeric formNumeric = (ContractRuleFormNumeric) formInIndex;
                listContractForm.set(i, formNumeric);
            } else if (formInIndex.getRuleType() == RuleType.FORMAT_TYPE){
                ContractRuleFormDate formDate = (ContractRuleFormDate) formInIndex;
                listContractForm.set(i, formDate);
            }
            else {
                throw new RuntimeException("Instance outside of expected type (String, Date, Numeric)");
            }
        }

        wrapper.setContractRuleFormList(listContractForm);

        return wrapper;
    }


}
