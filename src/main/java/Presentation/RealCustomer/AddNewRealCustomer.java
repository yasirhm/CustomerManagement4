package Presentation.RealCustomer;

/**
 * Created by Yasi on 11/26/2016.
 */

import BusinessLogic.BusinessLogic;
import DataAccess.RealCustomer;
import BusinessLogic.ConflictInDataException;
import Presentation.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddNewRealCustomer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String birthDay = request.getParameter("birthDay");
        String nationalId = request.getParameter("NationalId").replaceAll("\\s+", "");

        try {
            RealCustomer realCustomer = new RealCustomer(firstName, lastName, fatherName, nationalId, birthDay);
            realCustomer = BusinessLogic.addRealCustomerBiz(realCustomer);
            response.getWriter().print(Presentation.Util.createHTMLString(createHTMLString(realCustomer)));
            //request.setAttribute("realcustomer",realCustomer);
            // request.getRequestDispatcher("/Show.jsp").forward(request,response);
        } catch (ConflictInDataException e) {
            String error = "<p><h4>" + "\n  خطا : " + e.getMessage() + "</h4></p>";
            response.getWriter().print(Presentation.Util.createHTMLString(error));
        }
    }

    public String createHTMLString(RealCustomer realCustomer) {
        return
                "<style font-size=\"12px\" color='black'\"" + "\">" +
                        "<p><h4> اطلاعات مشتری جدید ثبت شد. </h4></p>" +
                        "<br><br>" +
                        "\t نام  : " + realCustomer.getFirstName() + " " + realCustomer.getLastName() + "\n <br> " +
                        "\t کدملی : " + realCustomer.getNationalId() + "\n <br> " +
                        "\t شماره مشتری : " + realCustomer.getCustomerNumber() + "\n <br> " +
                        "</font> <br><br>\n";
    }
}

