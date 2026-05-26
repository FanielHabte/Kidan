package io.kidan.guardian.dto.csv.request;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 DTO used to collect data from the CSV type contract submission
    1. This the file structure (include: contract_name, description, delimiter)
    2. List of all fields that a user can input for each column type
*/
public class CsvFormWrapper implements Serializable {
    @Serial
    private static final long serialVersionUID = -2388477470216719934L;
    private CsvFileStructure csvFileStructure;
    private List<CsvRuleForm> csvRuleFormList = new ArrayList<>();

    public CsvFormWrapper() {}

    public CsvFormWrapper(CsvFileStructure csvFileStructure, List<CsvRuleForm> csvRuleFormList) {
        this.csvRuleFormList = csvRuleFormList;
        this.csvFileStructure = csvFileStructure;
    }

    public CsvFileStructure getCsvFileStructure() {
        return csvFileStructure;
    }

    public void setCsvFileStructure(CsvFileStructure csvFileStructure) {
        this.csvFileStructure = csvFileStructure;
    }

    public List<CsvRuleForm> getCsvRuleFormList() {
        return csvRuleFormList;
    }

    public void setCsvRuleFormList(List<CsvRuleForm> csvRuleFormList) {
        this.csvRuleFormList = csvRuleFormList;
    }
}
