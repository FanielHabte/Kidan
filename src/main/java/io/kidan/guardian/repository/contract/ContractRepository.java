package io.kidan.guardian.repository.contract;

import io.kidan.guardian.entity.contract.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ContractRepository extends JpaRepository <Contract, String> {
    @Override @NonNull
    Optional<Contract> findById(@NonNull String id);

    @Query("SELECT c FROM Contract c " +
            "LEFT JOIN FETCH c.contractsRuleList " +
            "LEFT JOIN FETCH c.csvContract " +
            "WHERE c.id = :id ")
    Optional<Contract> findByIdWithRulesAndCsvContracts (String id);

}
