package io.kidan.guardian.dto.csv;

import io.kidan.guardian.enums.Delimiter;

import java.io.Serializable;

public class ContractFileStructure implements Serializable {
    private String columnNames;
    private Delimiter delimiter;

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
}
