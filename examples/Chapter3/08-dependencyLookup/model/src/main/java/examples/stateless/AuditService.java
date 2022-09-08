package examples.stateless;

import jakarta.ejb.Stateless;

@Stateless(name = "audit")
public class AuditService {
    public String audit() {
        return "Audit performed.";
    }
}

