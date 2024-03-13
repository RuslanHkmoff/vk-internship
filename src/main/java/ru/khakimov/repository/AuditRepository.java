package ru.khakimov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khakimov.model.AuditLog;

public interface AuditRepository extends JpaRepository<AuditLog, Long> {
}
