package indexing.entity;

import jakarta.persistence.*;
import submission.entity.Submission;

import java.time.LocalDateTime;
import java.util.Vector;

public class Embeddings {
    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn (name = "submission_id")
    private Submission submissionId;
    private String chuckText;
    private int chuckIndex;
    private Vector<Double> embedding = new Vector<>();
    private String metaData;
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Embeddings () {

    }

}


