package io.kidan.guardian.service.csv;

import io.kidan.guardian.entity.contract.Contract;
import io.kidan.guardian.entity.contract.CsvContract;
import io.kidan.guardian.repository.csv.CsvContractRepository;
import org.springframework.stereotype.Service;

@Service
public class CsvContractService {
    private final CsvContractRepository csvContractRepository;

    CsvContractService(CsvContractRepository csvContractRepository) {
        this.csvContractRepository = csvContractRepository;
    }

    public void saveCsvContractRule(CsvContract csvContract, Contract contract) {
        csvContract.setContract(contract);
        csvContractRepository.save(csvContract);
    }

}
