package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;
import jakarta.servlet.annotation.WebServlet;

import examples.model.Employee;
import examples.model.ParkingSpace;
import examples.stateless.EmployeeService;
import examples.stateless.ParkingSpaceService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 4: One-to-One Primary Key Mapping Example";
    
    private final String DESCRIPTION = 
        "This example demonstates how to use a one-to-one pk mapping for entities.</br> " +
        "It allows you to create/find employees & parking spaces and associates them.";

    
    @EJB EmployeeService empService;
    @EJB ParkingSpaceService pSpaceService;
    @Resource UserTransaction tx;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("CreateEmpAndParkingSpace")) {
            // make sure both operations happen in one tx
            try {
                tx.begin();
                Employee emp = empService.createEmployee(
                        parseInt(request.getParameter("id")),
                        request.getParameter("empName"),
                        parseLong(request.getParameter("salary")));
                
                pSpaceService.createParkingSpace(emp,
                        parseInt(request.getParameter("lot")),
                        request.getParameter("location"));
                tx.commit();
                out.println("Created " + emp);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        } else if (action.equals("FindAll")) {
            Collection<Employee> emps = empService.findAllEmployees();
            if (emps.isEmpty()) {
                out.println("No Employees found ");
            } else {
                out.println("Found Employees: </br>");
                for (Employee emp : emps) {
                    out.println(emp + "<br/>");
                }
            }
            
            out.println("</br>");
            Collection<ParkingSpace> pSpaces = pSpaceService.findAllParkingSpaces();
            if (pSpaces.isEmpty()) {
                out.println("No ParkingSpaces found ");
            } else {
                out.println("Found ParkingSpaces: </br>");
                for (ParkingSpace pSpace : pSpaces) {
                    out.println(pSpace + "<br/>");
                }
            }
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
    
    private long parseLong(String longString) {
        try {
            return Long.parseLong(longString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
        // form to create and Employee and Department
        out.println("<h3>Create an Employee and ParkingSpace</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"id\"/>(int)</td></tr>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"empName\"/>(String)</td></tr>");
        out.println("<tr><td>Employee Salary:</td><td><input type=\"text\" name=\"salary\"/>(long)</td></tr>");
        out.println("<tr><td>ParkingSpace lot#:</td><td><input type=\"text\" name=\"lot\"/>(int)</td></tr>");
        out.println("<tr><td>location:</td><td><input type=\"text\" name=\"location\"/>(String)</td></tr>");
        out.println("<td><input name=\"action\" type=\"submit\" value=\"CreateEmpAndParkingSpace\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
        // form to find all
        out.println("<h3>Find all Employees & Parking Spaces</h3>");
        out.println("<input name=\"action\" type=\"submit\" value=\"FindAll\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
