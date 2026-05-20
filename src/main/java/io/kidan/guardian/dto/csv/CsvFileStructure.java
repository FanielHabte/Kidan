package io.kidan.guardian.dto.csv;

import io.kidan.guardian.enums.Delimiter;

import java.io.Serializable;

public class CsvFileStructure implements Serializable {
    private String contractName;
    private String columnNames;
    private Delimiter delimiter;
    private String description;

    public String getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String columnNames) {
        this.columnNames = columnNames;
    }

    public Delimiter getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(Delimiter delimiter) {
        this.delimiter = delimiter;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
