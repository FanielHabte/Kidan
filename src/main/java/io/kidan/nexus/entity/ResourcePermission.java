package io.kidan.nexus.entity;

import io.kidan.nexus.enums.ResourceType;
import io.kidan.nexus.enums.RoleType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(catalog = "kidan",schema = "nexus")
public class ResourcePermission {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne @JoinColumn (name = "user_id", nullable = false)
    private User user;
    @Enumerated (EnumType.STRING) @Column(nullable = false)
    private ResourceType resourceType;
    @Enumerated (EnumType.STRING) @Column(nullable = false)
    private RoleType roleType;
    @Column (nullable = false)
    private String resourceId;
    @ManyToOne @JoinColumn (name = "granted_by", nullable = false)
    private User grantedBy;
    @Column (nullable = false)
    private LocalDateTime grantedAt;

    public ResourcePermission() {

    }

    @PrePersist
    public void setGrantedAt() {
        this.grantedAt = LocalDateTime.now();
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

}