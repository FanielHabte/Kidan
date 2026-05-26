package io.kidan.guardian.dto.csv.request;

import io.kidan.guardian.enums.Delimiter;

import java.io.Serial;
import java.io.Serializable;

/*
    DTO used to collect data of the CSV file structure.
*/
public class CsvFileStructure implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String contractName;
    private Delimiter delimiter;
    private String description;

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
