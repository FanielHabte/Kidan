package gateway.entity;

import gateway.enums.ResourceType;
import gateway.enums.RoleType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ResourcePermission {
    @Id@GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    private ResourceType resourceType;
    private String resourceId;
    private RoleType roleType;
    @ManyToOne
    @JoinColumn (name = "granted_by")
    private User grantedBy;
    private LocalDateTime grantedAt;

    public ResourcePermission() {

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

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public User getGrantedBy() {
        return grantedBy;
    }

    public void setGrantedBy(User grantedBy) {
        this.grantedBy = grantedBy;
    }

    public LocalDateTime getGrantedAt() {
        return grantedAt;
    }

    @PrePersist
    public void setGrantedAt() {
        this.grantedAt = LocalDateTime.now();
    }
}

//•	id (UUID, PK)
//•	user_id (UUID, FK → users.id)
//•	resource_type (enum: DATASET, SUBMISSION)
//•	resource_id (UUID)
//•	role (enum: OWNER, PROVIDER, VIEWER)
//•	granted_by (UUID, FK → users.id)
//•	granted_at (timestamp)
