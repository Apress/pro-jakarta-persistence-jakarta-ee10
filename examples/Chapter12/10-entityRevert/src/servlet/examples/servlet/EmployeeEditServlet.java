package examples.servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import examples.stateful.EmployeeEdit;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeEditServlet", 
            urlPatterns="/EmployeeEditServlet")
public class EmployeeEditServlet extends HttpServlet {

    @EJB private EmployeeService empService;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter("id"));
        if (id == 0) {
            request.setAttribute("employees", empService.findAll());
            getServletContext().getRequestDispatcher("/listEmployees.jsp")
                               .forward(request, response);
        } else {
            EmployeeEdit bean = getBean();
            bean.begin(id);
            HttpSession session = request.getSession();
            session.setAttribute("employee.edit", bean);
            request.setAttribute("employee", bean.getEmployee());
            getServletContext().getRequestDispatcher("/editEmployee.jsp")
                               .forward(request, response);
       }
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

    public EmployeeEdit getBean() throws ServletException {
        // lookup EmployeeEdit bean
        try {
            return (EmployeeEdit)
                new InitialContext().lookup("java:comp/env/EmployeeEdit");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
