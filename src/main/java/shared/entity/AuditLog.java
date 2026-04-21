package shared.entity;

import gateway.entity.User;
import gateway.enums.Action;
import gateway.enums.PermissionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class AuditLog {
    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User userId;
    private Action action;
    private String resourceId;
    private String resourceType;
    private PermissionStatus status;
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public AuditLog() {

    }

    public String getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
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

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}

