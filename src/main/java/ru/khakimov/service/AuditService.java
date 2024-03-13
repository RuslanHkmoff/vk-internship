package ru.khakimov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.khakimov.model.AuditLog;
import ru.khakimov.repository.AuditRepository;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditRepository auditRepository;

    public void save(String userName, String methodName, OffsetDateTime now, String roles) {
        AuditLog log = new AuditLog();
        log.setUsername(userName);
        log.setUserRoles(roles);
        log.setMethodName(methodName);
        log.setCreationTime(now);
        auditRepository.save(log);
    }
}
