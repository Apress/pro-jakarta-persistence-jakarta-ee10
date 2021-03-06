package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import javax.naming.InitialContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;
import jakarta.servlet.annotation.WebServlet;

import examples.stateless.DepartmentManager;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
@EJB(name="ejb/DepartmentManager", 
     beanInterface=DepartmentManager.class)
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 6: Persistence Context Collision Example";
    
    private final String DESCRIPTION = 
        "This example shows the basic use of an extended persistence " +
        "context and how to avoid collisions. The second create button " +
        "shows what happens when the 'addEmployee' operation is marked " +
        "REQUIRED and there's already a tx in place.";

    
    @EJB
    private EmployeeService empService;
    @Resource UserTransaction tx;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        DepartmentManager deptManager = (DepartmentManager) request.getSession().getAttribute("DepartmentManager");
        if (deptManager == null) {
            try {
                deptManager = (DepartmentManager)
                    new InitialContext().lookup("java:comp/env/ejb/DepartmentManager");
                request.getSession().setAttribute("DepartmentManager", deptManager);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
            
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            printInitAction(out);
        } else if (action.equals("ManageDept")) {
            deptManager.init(parseInt(request.getParameter("deptId")));
            printManagementActions(out, deptManager);
        } else if (action.equals("AddEmployee")) {
            deptManager.addEmployee(parseInt(request.getParameter("empId")));
            printManagementActions(out, deptManager);
        } else if (action.equals("AddEmployee2")) {
            try {
                tx.begin();
                deptManager.addEmployee2(parseInt(request.getParameter("empId")));
                printManagementActions(out, deptManager);
                tx.rollback();
            } catch (Exception e) {
                out.print(e);
                request.getSession().removeAttribute("DepartmentManager");
                printInitAction(out);
            } finally {
            }
        } else if (action.equals("SetName")) {
            deptManager.setName(request.getParameter("name"));
            printManagementActions(out, deptManager);
        } else if (action.equals("Finished")) {
            deptManager.finished();
            request.getSession().removeAttribute("DepartmentManager");
            printInitAction(out);
        }
        
        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    private int parseInt(String intString) {
        try {
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void printCollection(Collection c, PrintWriter out) {
        for (Object o : c) {
            out.print(o + "<br/>");
        }
    }
    
    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");
    }
    
    private void printInitAction(PrintWriter out) {
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
        out.println("<h3>Manage Department</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Department Id:</td><td><input type=\"text\" name=\"deptId\"/>(int)</td></tr>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"ManageDept\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("</form>");
        out.println("<hr/>");
        out.println("Departments:<br>");
        printCollection(empService.findAllDepartments(), out);
    }
    
    private void printManagementActions(PrintWriter out, DepartmentManager deptManager) {
        out.print("Managing " + deptManager.getDepartment() + "<br>");

        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
        out.println("<h3>Add Employee to Department</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"AddEmployee\"/></td>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"AddEmployee2\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<h3>Add Set Department Name</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Name:</td><td><input type=\"text\" name=\"name\"/>(String)</td></tr>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"SetName\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"Finished\"/></td></tr>");
        out.println("</form>");
        out.println("<hr/>");

        out.println("Employees:<br> ");
        printCollection(empService.findAllEmployees(), out);
    }
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
