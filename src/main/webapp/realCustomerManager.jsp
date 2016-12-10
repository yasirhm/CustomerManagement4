<%--
  Created by IntelliJ IDEA.
  User: dotinschool6
  Date: 11/26/2016
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fa" dir="rtl" contentType="text/html;charset=UTF-8">
<style type="text/css">
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Real Customer Manage</title>
    <link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
</head>
<body>
<div align="center" style="margin-top: 50px;">
    <br><br>
    <a type="text" href="createNewRealCustomer.jsp"> ایجاد مشتری جدید </a><br><br>
    <a type="text" href="LoanTypeManager.jsp"> ایجاد تسهیلات جدید </a><br>
    <br><br>
    <form method="get" action="/searchReal" id="search">
        <h4> جستجوی مشتری : </h4>
        <input name="firstName" type="text" size="40" placeholder="نام..." /><br><br>
        <input name="lastName" type="text" size="40" placeholder="نام خانوادگی..." /><br><br>
        <input name="NationalId" type="text" size="40" placeholder="کد ملی..." /><br><br>
        <input name="customerNumber" type="text" size="40" placeholder="شماره مشتری..." /><br><br>
        <input type="submit" value="جستجو" style=" border: 1px solid #d1d1d1; -webkit-border-radius: 20px; border-width: 3px;
        border-style: ridge; font: bold 13px Arial,Helvetica,Sans-serif; width: 110px;padding: 6px 15px 6px 10px;" ><br><br>
    </form>
</div>
</body>
</html>