<%@ page import="DataAccess.RealCustomer" %>
<%@ page import="java.util.List" %>
<%@ page import="DataAccess.LoanType" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: dotinschool6
  Date: 12/11/2016
  Time: 9:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fa" dir="rtl" contentType="text/html  ؛charset=UTF-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Loan File</title>
    <link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
    <script src="functions.js"></script>
</head>
<body>
<% List<LoanType> loans =(ArrayList)request.getAttribute("loans");
    StringBuilder options = new StringBuilder("");
    for (LoanType loan :loans){
        /*<option value="australia"> </option>*/
    String string = "\n<option value='"+loan.getLOAN_TYPE_ID()+"'>"+loan.getName()+"</option>";
        options.append(string);
    }
%>

<div align="center" style="margin-top: 50px;">

    <form method="post" id="search" action="/createLoanFile">
        <div id="retrieve">
            <label for="customerNumber">شماره مشتری :</label>
            <input type="text" id="customerNumber" name="customerNumber" size="40" oninvalid="InvalidMsg(this);"
                   required/>
            <button type="button" size="30" onclick="retrieveCustomer()"> بازیابی</button>
            <br><br>
        </div>

        <div id="showCustomer" style="display: none">
            <label for="firstName">نام :</label>
            <input id="firstName" name="firstName" value="Norway" style="margin-right: 45px" readonly>
            <br><br>
            <label for="lastName">نام خانوادگی :</label>
            <input name="lastName" id="lastName" value="Norway" readonly><br>
            <br><br> <br>
        </div>
        <div><h4 id="nullCustomer" style="display: none">  مشتری با این شماره وجود ندارد.</h4> </div>

        <div id="dropDown" style="display: none">
          <label for="loanTypes">انواع تسهیلات : </label>
          <select id="loanTypes" name="loanTypeID">
              <%=options.toString()%>
          </select>
            <label for="period">مدت قرارداد :</label>
            <input id="period" name="period" type="text" >
            <label for="amount">مبلغ قرارداد :</label>
            <input name="amount" type="text" id="amount" ><br>
            <br><br>
        </div>
        <input id="submit" style="display: none" type="submit" value="ثبت">
    </form>
</div>

</body>
</html>
