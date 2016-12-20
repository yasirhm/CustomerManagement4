package Presentation.RealCustomer;

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
import static java.lang.Integer.parseInt;


/**
 * Created by Yasi on 11/19/2016.
 */
public class EditRealCustomerPresentation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String customerNumber = request.getParameter("id");
        RealCustomer realCustomer = BusinessLogic.getRealCustomerBiz(parseInt(customerNumber));
        response.getWriter().println(Util.createHTMLString(createHTMLBodyEditPageString(realCustomer)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String birthDay = request.getParameter("birthDate").replaceAll("\\s+","");
        String customerNumber = request.getParameter("customerNumber").replaceAll("\\s+","");
        String nationalId = request.getParameter("NationalId").replaceAll("\\s+","");
        try {
            RealCustomer realCustomer = new RealCustomer(firstName,lastName,fatherName,birthDay,nationalId);
            realCustomer.setCustomerNumber(parseInt(customerNumber));
            //RealCustomer updated = BusinessLogic.updateRealCustomerBiz(realCustomer);
            BusinessLogic.updateRealCustomerBiz(realCustomer);
            response.getWriter().println(Util.createHTMLString(createHTMLBodyResultPageString(realCustomer)));
        }catch (ConflictInDataException exp){
            String error = "<p><h4>" + "\n  خطا : " + exp.getMessage() + "</h4></p>";
            response.getWriter().print(Presentation.Util.createHTMLString(error));
        }
    }

    public String createHTMLBodyEditPageString(RealCustomer realCustomer){
        return
                "<br><br>\n" +
                        "<form action = \"/editRealCustomer\" method = \"post\" >" +
                        "<table >\n"+
                        "<tr >\n"+
                        "<td > نام </td >\n"+
                        "<td > نام خانوادگی</td >\n"+
                        "<td > نام پدر</td >\n"+
                        "<td > تاریخ تولد</td >\n"+
                        //"<td > کد ملی</td >\n"+
                        "</tr >\n"+
                        "<tr >\n"+
                        "<td >\n"+
                        "<input name = \"firstName\" type = \"text\" value='"+realCustomer.getFirstName()+
                        "'placeholder='"+realCustomer.getFirstName()+"'>"+
                        "</td >\n"+
                        "<td >\n"+
                        "<input name = \"lastName\" type = \"text\" value='"+realCustomer.getLastName()+
                        "' placeholder="+realCustomer.getLastName()+">"+
                        "</td >\n"+
                        "<td >\n"+
                        "<input name = \"fatherName\" type = \"text\" value='"+realCustomer.getFatherName()+
                        "' placeholder="+realCustomer.getFatherName()+">"+
                        "</td >\n"+
                        "<td >\n"+
                        "<input name = \"birthDate\" type = \"text\" value='"+realCustomer.getBirthDate()+
                        "' placeholder="+realCustomer.getBirthDate()+">"+
                        "</td >\n"+
                        "<td >\n <input value = \"ثبت\" type = \"submit\"  / > </td >\n"+
                        "<td style=\"display:none;\">\n <input name='customerNumber' value='"+realCustomer.getCustomerNumber()+"' ></td >\n"+
                        "<td style=\"display:none;\">\n <input name='NationalId' value='"+realCustomer.getNationalId()+"'></td >\n"+
                        "</tr>\n"+
                        "</table >\n"+
                        "<br><br>" +
                        "</form >\n";
    }

    public String createHTMLBodyResultPageString(RealCustomer realCustomer){
        return
                "<br><br>\n" +
                        "<table  BORDER='5' WIDTH='50%'>\n"+
                        "<tr >\n"+
                        "<td ><h5> نام </h5></td >\n"+
                        "<td ><h5> نام خانوادگی </h5></td >\n"+
                        "<td ><h5>  نام پدر</h5></td >\n"+
                        "<td ><h5> تاریخ تولد</h5></td >\n"+
                        "<td ><h5> کد ملی</h5></td >\n"+
                        "</tr >\n"+
                        "<tr >\n"+
                        "<td >\n"+
                        realCustomer.getFirstName()+
                        "</td >\n"+
                        "<td >\n"+
                        realCustomer.getLastName()+
                        "</td >\n"+
                        "<td >\n"+
                        realCustomer.getFatherName()+
                        "</td >\n"+
                        "<td >\n"+
                        realCustomer.getBirthDate()+
                        "</td >\n"+
                        "<td >\n"+
                        realCustomer.getNationalId()+
                        "</td >\n"+
                        "</tr>\n"+
                        "</table >\n"+
                        "<br><br>" ;
    }
}
