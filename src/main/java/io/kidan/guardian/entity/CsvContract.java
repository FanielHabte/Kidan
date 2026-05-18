package io.kidan.guardian.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table (catalog = "kidan", schema = "guardian")
public class CsvContract implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne() @JoinColumn(nullable = false, name = "contract_id")
    private Contract contract;
    @Column(nullable = false)
    private String contractName;
    @Column(nullable = false)
    private String columnNames;
    @Column(nullable = false)
    private String description;

    public CsvContract() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String columnNames) {
        this.columnNames = columnNames;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
