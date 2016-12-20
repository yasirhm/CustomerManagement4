<%@ page import="DataAccess.LoanFile" %><%--
  Created by IntelliJ IDEA.
  User: dotinschool6
  Date: 12/13/2016
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fa" dir="rtl"  contentType="text/html  ؛charset=UTF-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show Result</title>
    <link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
    <script src="functions.js"></script>
</head>
<body>
<%LoanFile loanfile = (LoanFile)request.getAttribute("loanFile");%>
<div align="center" style="margin-top: 50px; ">
    <div>
        <h4>پرونده ی تسهیلاتی جدید ثبت شد.:</h4>
    </div>
    <table class="fixed_headers" border="3"  border-collapse="collapse" cellpadding="4" cellspacing="3"  >
        <thead>
        <tr>
            <th>شماره پرونده</th>
            <th>شماره تسهیلات</th>
            <th>شماره مشتری</th>
            <th>مدت قرارداد</th>
            <th>مبلغ قرارداد</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><%=loanfile.getLoanFileID()%></td>
            <td><%=loanfile.getLoanTypeID()%></td>
            <td><%=loanfile.getCustomerNumber()%></td>
            <td><%=loanfile.getPeriod()%></td>
            <td><%=loanfile.getAmount()%></td>
        </tr>
    </table>
</div>

</body>
</html>
