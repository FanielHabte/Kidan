package aether.entity;

import jakarta.persistence.*;
import inlet.entity.Submission;

import java.time.LocalDateTime;

@Entity
@Table (catalog = "kidan", schema = "aether",
        indexes = {
            @Index(name = "idx_embedding_submission", columnList = "submission_id")
        }
)
public class Embedding {
    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    @ManyToOne @JoinColumn (name = "submission_id", nullable = false)
    private Submission submission;
    @Column (nullable = false)
    private String chunkText;
    @Column (nullable = false)
    private int chunkIndex;
    @Column(columnDefinition = "vector(1536)", nullable = false)
    private float[] embedding;
    @Column (nullable = false)
    private String metaData;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public Embedding() {

    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public String getChunkText() {
        return chunkText;
    }

    public void setChunkText(String chunkText) {
        this.chunkText = chunkText;
    }

    public int getChunkIndex() {
        return chunkIndex;
    }

    public void setChunkIndex(int chunkIndex) {
        this.chunkIndex = chunkIndex;
    }

    public float[] getEmbedding() {
        return embedding;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}


