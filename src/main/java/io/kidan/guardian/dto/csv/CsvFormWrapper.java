package io.kidan.guardian.dto.csv;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
