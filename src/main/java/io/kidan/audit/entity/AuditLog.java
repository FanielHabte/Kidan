package io.kidan.audit.entity;

import io.kidan.nexus.entity.User;
import io.kidan.nexus.enums.Action;
import io.kidan.nexus.enums.PermissionStatus;
import io.kidan.nexus.enums.ResourceType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (catalog = "kidan", schema = "audit",
        indexes = {
                @Index(name = "idx_audit_log_user", columnList = "user_id")
        }
)
public class AuditLog {
    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    @ManyToOne @JoinColumn (name = "user_id", nullable = false)
    private User user;
    @Column (nullable = false)
    private String resourceId;
    @Enumerated (EnumType.STRING) @Column(nullable = false)
    private Action action;
    @Enumerated (EnumType.STRING) @Column(nullable = false)
    private ResourceType resourceType;
    @Enumerated (EnumType.STRING) @Column(nullable = false)
    private PermissionStatus status;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public AuditLog() {

    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public PermissionStatus getStatus() {
        return status;
    }

    public void setStatus(PermissionStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}

