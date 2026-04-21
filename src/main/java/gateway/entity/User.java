package gateway.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.LocalDateTime;

@Entity
public class User {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Email @Unique
    private String email;
    private String password;
    private String name;
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public User () {

    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

}
