package examples.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class DepartmentService {
    @EJB AuditService audit;

    public String performAudit() {
        return audit.audit();
    }
}

