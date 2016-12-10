<%--
  Created by IntelliJ IDEA.
  User: dotinschool6
  Date: 12/7/2016
  Time: 10:00 AM
  To change this template use File | Settings | File Templates.
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
        <label for="Gname"> نام </label>
        <input type="text" id="Gname" name="grantName" size="40" style="margin-right:60px" oninvalid="InvalidMsg(this);"required />
        <br><br>
        <label for="minPeriod">حداقل مدت قرارداد</label>
        <input type="text" id="minPeriod" name="minPeriod" size="40" oninvalid="InvalidMsg(this);"required />

        <br><br>
        <label for="maxPeriod">حداکثر مدت قرارداد</label>
        <input type="text" id="maxPeriod" name="maxPeriod" size="40" oninvalid="InvalidMsg(this);"required />

        <br><br>
        <label for="minAmount">حداقل مبلغ قرارداد</label>
        <input type="text" id="minAmount" name="minAmount" size="40" oninvalid="InvalidMsg(this);"required />

        <br><br>
        <label for="maxAmount">حداکثر مبلغ قرارداد</label>
        <input type="text" id="maxAmount" name="maxAmount" size="40" oninvalid="InvalidMsg(this);"required />
        <br><br>
        <input type="submit" value=" تعریف شرط جدید">
        <input type="submit" value="ثبت نهایی">
    </form>
</div>
</body>
</html>
