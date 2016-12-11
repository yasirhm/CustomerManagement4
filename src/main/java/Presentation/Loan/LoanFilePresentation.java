package Presentation.Loan;

import BusinessLogic.BusinessLogic;
import DataAccess.RealCustomer;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

/**
 * Created by $Yasi on 12/11/2016.
 */
public class LoanFilePresentation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();

        String customerNumber = request.getParameter("customerNumber");
        try {
            RealCustomer realCustomer = BusinessLogic.getRealCustomerBiz(parseInt(customerNumber));
            request.setAttribute("realCustomer",realCustomer);
            //request.getRequestDispatcher("/LoanFileManager.jsp").forward(request,response);
            jsonObject.put("firstName", realCustomer.getFirstName());
            jsonObject.put("lastName", realCustomer.getLastName());
            response.setContentType("application/javascript");
            response.getWriter().print(jsonObject);
        }catch (NumberFormatException exp){
            jsonObject.put("error","فرمت شماره مشتری ورودی اشتباه است.");
            response.getWriter().print(jsonObject);
        }

    }
}
