package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.ejb.EJB;

import examples.stateless.DepartmentService;

@WebServlet(name="DepartmentServiceServlet", 
            urlPatterns="/DepartmentServiceServlet")
public class DepartmentServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 3: Dependency Lookup Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates InitialContext lookup of a dependency. </br>" +
        "Click the 'Audit' button.  This will trigger a servlet client that invokes " +
        "a SLSB. That SLSB in turn looks up another SLSB using an InitialContext.";

    @EJB DepartmentService service;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);

        if (request.getParameter("action") != null) {
            out.println("Result: " + service.performAudit());
        }
        
        printHtmlFooter(out);
    }
    
    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("</hr>");
        out.println("<form action=\"DepartmentServiceServlet\" method=\"POST\">");
        out.println("<input name=\"action\" type=\"submit\" value=\"Audit\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
        
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
