package Presentation.Loan;

import BusinessLogic.BusinessLogic;
import DataAccess.LoanFile;
import DataAccess.RealCustomer;
import org.json.simple.JSONObject;

import javax.rmi.CORBA.Util;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String customerNumber = request.getParameter("customerNumber");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String loanTypeID = request.getParameter("loanTypeID");
            String period = request.getParameter("period").replaceAll("\\s+", "");
            String amount = request.getParameter("amount").replaceAll("\\s+", "");

            LoanFile loanFile = new LoanFile(firstName,lastName,parseInt(customerNumber),parseInt(loanTypeID),parseInt(period),parseInt(amount));
            BusinessLogic.checkConditionAccurency(loanFile);
            BusinessLogic.addNewLoanFile(loanFile);
            request.setAttribute("loanFile",loanFile);
            RequestDispatcher rd=request.getRequestDispatcher("/ShowLoanFileResult.jsp");
            rd.forward(request, response);
        }catch (Exception exp){
            String error = "<p><h4>"+ "\n  خطا : " + exp.getMessage() +"</h4></p>" ;
            response.getWriter().print(Presentation.Util.createHTMLString(error));
        }
    }
}
