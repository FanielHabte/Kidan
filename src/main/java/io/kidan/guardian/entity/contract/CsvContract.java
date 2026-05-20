package io.kidan.guardian.entity.contract;

import io.kidan.guardian.enums.Delimiter;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table (catalog = "kidan", schema = "guardian")
public class CsvContract implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne() @JoinColumn(nullable = false, name = "contract_id")
    private Contract contract;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private Delimiter delimiter;

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

    public Delimiter getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(Delimiter delimiter) {
        this.delimiter = delimiter;
    }
}
