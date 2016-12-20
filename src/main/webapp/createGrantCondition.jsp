<%@ page import="static org.hibernate.criterion.Projections.rowCount" %><%--
  Created by IntelliJ IDEA.
  User: dotinschool6
  Date: 12/7/2016
  Time: 10:00 AM
  To change this template use File | Settings | File Templates.
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="fa" dir="rtl"  contentType="text/html  ؛charset=UTF-8">
<%String loanName = (String) request.getAttribute("loanName"); String interestRate = (String) request.getAttribute("interestRate");
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Grant Condition</title>
    <link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
    <script src="functions.js"></script>
</head>
<body>

<div align="center" style="margin-top: 50px;">
    <div>
        <h4>شروط جدید اعطای تسهیلات را تعریف بکنید :</h4>
    </div>

    <form method="post" action="/AddNewLoanType?loanName=<%=request.getParameter("loanName")%>&interestRate=<%=request.getParameter("interestRate")%>" id="search">

    <table id="grants" class="fixed_headers" border="3"  border-collapse="collapse" cellpadding="4" cellspacing="3"  >
        <thead>
        <tr>
            <th>نام</th>
            <th>حداقل مدت قرارداد</th>
            <th>حداکثر مدت قرارداد</th>
            <th>حداقل مبلغ قرارداد</th>
            <th>حداکثر مبلغ قرارداد</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="data" name="grantName"  oninvalid="InvalidMsg(this);"required /></td>
            <td><input type="data" name="minPeriod"  oninvalid="InvalidMsg(this);"required /></td>
            <td><input type="data" name="maxPeriod"  oninvalid="InvalidMsg(this);"required /></td>
            <td><input type="data" name="minAmount"  oninvalid="InvalidMsg(this);"required /></td>
            <td><input type="data" name="maxAmount"  oninvalid="InvalidMsg(this);"required /></td>
            <td><input type="button" value = "حذف" onClick="deleteRow(this)"></td>
        </tr>
    </table>
        <br><br>
        <button type="button" id="newGrant" onclick="insertRow()"> تعریف شرط جدید</button>
        <input id="submit" type="submit" name="1" value="ثبت نهایی">
      <%--  <input id="rowCount" name="rowCount" value="1" style="display: none">--%>
    </form>
</div>
</body>
</html>
