package io.kidan.guardian.repository;

import io.kidan.guardian.entity.ContractsPageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ContractsPageViewRepository extends JpaRepository <ContractsPageView, String> {

    @Override @NonNull
    List<ContractsPageView> findAll();
}
