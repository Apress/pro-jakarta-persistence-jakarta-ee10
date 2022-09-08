package examples.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class DepartmentService {
    private AuditService audit;

    @EJB
    public void setAuditService(AuditService audit) {
        this.audit = audit;
    }

    public String performAudit() {
        return audit.audit();
    }
    
    // ...
}

