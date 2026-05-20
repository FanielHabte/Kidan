package io.kidan.guardian.repository.csv;

import io.kidan.guardian.entity.contract.CsvContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvContractRepository extends JpaRepository<CsvContract, String> {
}
