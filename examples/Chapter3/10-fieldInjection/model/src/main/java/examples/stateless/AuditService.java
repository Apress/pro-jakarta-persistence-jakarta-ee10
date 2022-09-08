package examples.stateless;

import jakarta.ejb.Stateless;

@Stateless
public class AuditService {
    public String audit() {
        return "Audit performed.";
    }
}

