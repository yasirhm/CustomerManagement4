package Presentation.Loan;

import BusinessLogic.BusinessLogic;
import BusinessLogic.ConflictInDataException;
import DataAccess.GrantCondition;
import DataAccess.LoanType;
import Presentation.Util;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

/**
 * Created by $Yasi on 12/6/2016.
 */
public class CreateNewLoanTypePresentation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String loanName = request.getParameter("loanName");
        String interestRate = request.getParameter("interestRate").replaceAll("\\s+", "");
        String grantName = request.getParameter("grantName");
        String minPeriod = request.getParameter("minPeriod");
        String maxPeriod = request.getParameter("maxPeriod");
        String minAmount = request.getParameter("minAmount");
        String maxAmount = request.getParameter("maxAmount");

        String html;
        try {
            if (loanName.equals("") || interestRate.equals("")) {
                throw new ConflictInDataException("اطلاعات ورودی کافی نیست.");
            }else {
                LoanType loan = new LoanType(loanName, new Double(parseInt(interestRate)));
                Set<GrantCondition> grantConditions = new HashSet<GrantCondition>();
                grantConditions.add(new GrantCondition(grantName, parseInt(maxPeriod), parseInt(minPeriod), new Double(parseInt(maxAmount)), new Double(parseInt(minAmount))));
                loan.setGrantConditions(grantConditions);
                LoanType loanType = BusinessLogic.addLoanType(loan);
            }
        } catch (ConflictInDataException exp) {
            String error = "خطا :" + "\n" + exp.getMessage();
            html = Util.createHTMLString(error);
            out.println(html);
        }
    }
}
