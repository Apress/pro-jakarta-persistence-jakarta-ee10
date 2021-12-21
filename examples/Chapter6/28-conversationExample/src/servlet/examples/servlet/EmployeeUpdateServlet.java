package examples.servlet;

import java.io.IOException;

import jakarta.inject.Inject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import examples.model.Employee;
import examples.stateful.EmployeeService;

@WebServlet(name="EmployeeUpdateServlet", 
            urlPatterns="/EmployeeUpdateServlet")
public class EmployeeUpdateServlet extends HttpServlet {

    @Inject EmployeeService bean;
       
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("Save")) {
            Employee emp = bean.getCurrentEmployee();            
            emp.setName(request.getParameter("name"));
            emp.setSalary(parseLong(request.getParameter("salary")));
            bean.saveChangesToEmployee();
        } else if (action.equals("Cancel")) {
            bean.cancel();
        }
        request.setAttribute("employees", bean.findAll());
        getServletContext().getRequestDispatcher("/listEmployees.jsp")
                           .forward(request, response);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }    

    private long parseLong(String longString) {
        try {
            return Long.parseLong(longString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
