package ru.khakimov.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "auditlogs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String methodName;

    @Column(name = "user_roles", nullable = false)
    private String userRoles;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime creationTime;
}
