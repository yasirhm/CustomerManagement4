<%--
  Created by IntelliJ IDEA.
  User: Yasi
  Date: 12/6/2016
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fa" dir="rtl"  contentType="text/html  ؛charset=UTF-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Loan Type</title>
    <link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
    <script src="functions.js"></script>
</head>
<body>

<div align="center" style="margin-top: 50px;">
    <div>
        <h4>اطلاعات نوع تسهیلات جدید را وارد بکنید:</h4>
    </div>

    <form method="get" id="search" action="createGrantCondition.jsp">
        <label for="Lname">نام نوع تسهیلات</label>
        <input type="text" id="Lname" name="loanName" size="40" oninvalid="InvalidMsg(this);"required />
        <br><br>
        <label for="interestRate">نرخ سود</label>
        <input type="text" id="interestRate" name="interestRate" size="40" style="margin-right:35px" oninvalid="InvalidMsg(this);"required/>
        <br><br>
        <input type="submit" value="صفحه ی بعد"/>
        <%--
        action="createGrantCondition.jsp"
          <label for="country">State</label>
          <select id="country" name="country">
              <option value="australia">Australia</option>
              <option value="canada">Canada</option>
              <option value="usa">USA</option>
          </select>
            <input type="submit" value="ایجاد">
          --%>
    </form>
</div>

</body>
</html>
