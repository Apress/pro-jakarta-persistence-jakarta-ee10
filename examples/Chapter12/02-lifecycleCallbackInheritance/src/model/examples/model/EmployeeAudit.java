package examples.model;

import jakarta.persistence.PostPersist;

public class EmployeeAudit {
    @PostPersist
    public void auditNewHire(CompanyEmployee emp) { 
        System.out.println("EmployeeAudit.auditNewHire called on employee: " + emp.getId());
        //...
    }
}

