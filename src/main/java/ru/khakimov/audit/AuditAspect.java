package ru.khakimov.audit;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.khakimov.service.AuditService;
import ru.khakimov.service.UserService;

import java.time.OffsetDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {
    private final UserService userService;
    private final AuditService auditService;

    @Around(value = "@annotation(ru.khakimov.audit.annotation.Audit)")
    public Object auditMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String methodName = method.getName();
        String userName = securityContext.getAuthentication().getName();
        String roles = userService.getUserRolesByLogin(userName);
        auditService.save(userName, methodName, OffsetDateTime.now(), roles);
        return proceedingJoinPoint.proceed();
    }
}