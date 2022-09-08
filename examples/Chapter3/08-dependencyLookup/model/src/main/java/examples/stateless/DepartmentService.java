package examples.stateless;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
@EJB(name="deptAudit", beanInterface=AuditService.class)
public class DepartmentService  {
    AuditService audit;

    @PostConstruct
    public void init() {
        try {
            Context ctx = new InitialContext();
            audit = (AuditService) ctx.lookup("java:module/audit");
        } catch (NamingException ex){
            throw new EJBException(ex);
        }
    }

    public String performAudit() {
        return audit.audit();
    }

}

