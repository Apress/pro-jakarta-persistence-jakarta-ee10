package examples.beans;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Dependent
@Secure
public class SecureDepartmentService extends DepartmentService {
    @Inject AuditService audit;
    
    @Inject @EmployeeEM
    private EntityManager empEM;

    public String performAudit() {
        return "Secure " + audit.audit();
    }
    
    // ...
}

