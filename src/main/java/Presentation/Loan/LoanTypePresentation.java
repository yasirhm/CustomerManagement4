package Presentation.Loan;

import BusinessLogic.BusinessLogic;
import BusinessLogic.ConflictInDataException;
import DataAccess.GrantCondition;
import DataAccess.LoanType;
import DataAccess.RealCustomer;
import Presentation.Util;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

/**
 * Created by $Yasi on 12/6/2016.
 */
public class LoanTypePresentation extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoanTypePresentation.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String loanName = request.getParameter("loanName");
        String interestRate = request.getParameter("interestRate").replaceAll("\\s+", "");
        String[] grantNames = request.getParameterValues("grantName");
        String[] minPeriods = request.getParameterValues("minPeriod");
        String[] maxPeriods = request.getParameterValues("maxPeriod");
        String[] minAmounts = request.getParameterValues("minAmount");
        String[] maxAmounts = request.getParameterValues("maxAmount");
        try {
            if (loanName.equals("") || interestRate.equals("")) {
                throw new ConflictInDataException("اطلاعات ورودی کافی نیست.");
            }else {
                LoanType loan = new LoanType(loanName, new Double(parseInt(interestRate)));
                Set<GrantCondition> grantConditions = new HashSet<GrantCondition>();
                for (int i = 0; i < grantNames.length; i++ ){
                    grantConditions.add(new GrantCondition(grantNames[i], parseInt(maxPeriods[i]), parseInt(minPeriods[i]), new Double(parseInt(maxAmounts[i])), new Double(parseInt(minAmounts[i]))));
                }
                loan.setGrantConditions(grantConditions);
                LoanType loanType = BusinessLogic.addLoanType(loan);
                String result = "<p><h4> تسهیلات جدید با شروط اعطای تعریف شدهّ با موفقیت ثبت شد. </h4></p>" ;
                response.getWriter().println(Util.createHTMLString(result));
            }
        } catch (ConflictInDataException exp) {
            String error = "<p><h4>"+ "\n  خطا : " + exp.getMessage() +"</h4></p>" ;
            response.getWriter().print(Presentation.Util.createHTMLString(error));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            List<LoanType> loans = new ArrayList<LoanType>();
            loans = BusinessLogic.loadAllLoantTypes();
            request.setAttribute("loans",loans);
            RequestDispatcher rd=request.getRequestDispatcher("/LoanFileManager.jsp");
            rd.forward(request, response);
        }catch (Exception e){
            logger.error(e , e);
            String error = "<p><h4>"+ "\n  خطا : " + e.getMessage() +"</h4></p>" ;
            response.getWriter().print(Presentation.Util.createHTMLString(error));
        }
    }

}
