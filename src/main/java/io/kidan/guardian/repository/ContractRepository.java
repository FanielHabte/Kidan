package io.kidan.guardian.repository;

import io.kidan.guardian.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ContractRepository extends JpaRepository <Contract, String> {
    @Override @NonNull
    Optional<Contract> findById(@NonNull String id);

    @Query("SELECT c FROM Contract c " +
            "LEFT JOIN FETCH c.contractsRuleList " +
            "WHERE c.id = :id ")
    Optional<Contract> findByIdWithRulesAndCsvContracts (String id);

}
