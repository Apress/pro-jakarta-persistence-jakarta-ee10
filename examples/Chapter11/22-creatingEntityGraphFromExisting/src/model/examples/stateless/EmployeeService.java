package examples.stateless;

import java.io.PrintWriter;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.AttributeNode;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import examples.model.*;

@Stateless
public class EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public void addEntityGraph(PrintWriter out) {
        EntityGraph<?> graph = em.createEntityGraph("Employee.graph2");
        graph.addAttributeNodes("projects");
        em.getEntityManagerFactory().addNamedEntityGraph("Employee.newGraph", graph);
        out.println("Added EntityGraph Employee.newGraph to Employee <br/>");
    }

    public void printAllEntityGraphs(PrintWriter out) {
        out.println("EntityGraphs for ContractEmployee: <br/>");
        List<EntityGraph<? super ContractEmployee>> egList = em.getEntityGraphs(ContractEmployee.class);
        for (EntityGraph<? super ContractEmployee> graph : egList) {
            out.println("EntityGraph: " + graph.getName() + "<br/>");
            List<AttributeNode<?>> attribs = graph.getAttributeNodes();
            for (AttributeNode<?> attr : attribs) {
                out.println(">>> Attribute: " + attr.getAttributeName() + "<br/>");
            }
        }
    }
}
