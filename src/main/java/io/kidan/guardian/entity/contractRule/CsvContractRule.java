package io.kidan.guardian.entity.contractRule;

import io.kidan.guardian.enums.DataType;
import jakarta.persistence.*;

@Entity
@Table(schema = "guardian", name = "csv_contract_rule")
public class CsvContractRule {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String columnName;
    @Column(nullable = false)
    private DataType dataType;
    @OneToOne @JoinColumn(nullable = false, name = "contract_rule")
    private ContractRule contractRule;
    @Column(nullable = false)
    private boolean isUnique;
    @Column (nullable = false)
    private boolean isRequired;

    public CsvContractRule() {
    }

    public String getId() {
        return id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public ContractRule getContractRule() {
        return contractRule;
    }

    public void setContractRule(ContractRule contractRule) {
        this.contractRule = contractRule;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean unique) {
        isUnique = unique;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }
}
