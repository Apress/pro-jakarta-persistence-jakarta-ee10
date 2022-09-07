package examples.stateless;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.SessionContext;
import jakarta.ejb.SessionBean;
import jakarta.ejb.Stateless;

@Stateless
@EJB(name="audit", beanInterface=AuditService.class)
public class DepartmentService implements SessionBean {
    SessionContext context;
    AuditService audit;

    public void setSessionContext(SessionContext context) { 
        this.context = context; 
    }
    
    @PostConstruct
    public void init() {
        audit = (AuditService) context.lookup("audit");
    }

    public String performAudit() {
        return audit.audit();
    }
    
    // SessionBean methods 
    public void ejbRemove() {}
    public void ejbPassivate() {}
    public void ejbActivate() {}

    // ...
}

