package Presentation.RealCustomer;

/**
 * Created by Yasi on 11/26/2016.
 */

import BusinessLogic.BusinessLogic;
import DataAccess.RealCustomer;
import BusinessLogic.ConflictInDataException;

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
        PrintWriter out = response.getWriter();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String birthDay = request.getParameter("birthDay");
        String nationalId = request.getParameter("NationalId").replaceAll("\\s+", "");

        RealCustomer realCustomer = new RealCustomer(firstName, lastName, fatherName, nationalId, birthDay);

        String html;
        try {
            realCustomer = BusinessLogic.addRealCustomerBiz(realCustomer);
            html = createHTMLString(realCustomer);
            //request.setAttribute("realcustomer",realCustomer);
           // request.getRequestDispatcher("/Show.jsp").forward(request,response);
        } catch (ConflictInDataException e) {
            System.out.println("Error: "+e.getMessage());
             html = showExceptionMessage(e.getMessage());
        }
        out.println(html);
    }

    public String createHTMLString(RealCustomer realCustomer) {
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html charset=UTF-8\" lang=\"fa\" dir=\"rtl\"> \n" +
                "<style type=\"text/css\">\n" +
                "    body {\n" +
                "        background-image:\n" +
                "                url('images/background.png');\n" +
                "}\n" +
                "</style>" +
                "<head> \n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "<title>DataAccessCustomer Manager</title> \n" +
                "</head> \n" +
                "<body> <div align='center'> \n" +
                "<style" +
                "\"font-size=\"12px\" color='black'\"" + "\">" +
                "<br><br><br><br> " +
                "اطلاعات مشتری جدید ثبت شد. <br>" +
                "<br><br>" +
                "\t نام  : " + realCustomer.getFirstName() + " " + realCustomer.getLastName() + " <br> " +
                "\t کدملی : " + realCustomer.getNationalId() + " <br> " +
                "\t شماره مشتری : " + realCustomer.getCustomerNumber() + " <br> " +
                "</font> <br><br>\n" +
                "<a type=\"text\" href=\"index.jsp\"> صفحه ی اول </a><br>\n" +
                "</body> \n" +
                "</html>";
    }
    public String showExceptionMessage(String message){
        return
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                        "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                        "<html charset=UTF-8\" lang=\"fa\" dir=\"rtl\"> \n" +
                        "<style type=\"text/css\">\n" +
                        "    body {\n" +
                        "        background-image:\n" +
                        "                url('images/background.png')};\n"+
                        "</style>"+
                        "<head> \n" +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"+
                        "<title>DataAccessCustomer Manager</title> \n" +
                        "</head> \n" +
                        "<body> <div align='center'> \n" +
                        "<style" +
                        "\"font-size=\"12px\" color='black'\"" + "\">" +
                        "<br><br><br><br> " +
                        "خطا: <br>"+
                        "\t " + message + " <br> " +
                        "</font> <br><br>\n" +
                        "<a type=\"text\" href=\"index.jsp\"> صفحه ی اول </a><br>\n"+
                        "</div>\n"+
                        "</body> \n" +
                        "</html>";
    }

}

