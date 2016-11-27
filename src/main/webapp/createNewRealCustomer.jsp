<%--
  Created by IntelliJ IDEA.
  User: dotinschool6
  Date: 11/27/2016
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fa" dir="rtl" contentType="text/html;charset=UTF-8" >
<style type="text/css">
    body {
        background-image: url('images/background.png');
    }
    #create input[type="text"]{
        background:no-repeat 10px 6px #fcfcfc;
        border: 1px solid #d1d1d1;
        font: bold 12px Arial,Helvetica,Sans-serif;
        color: #bebebe;
        width: 150px;
        padding: 6px 15px 6px 35px;
        -webkit-border-radius: 20px;
        -moz-border-radius: 20px;
        border-radius: 20px;
        text-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);
        -webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.15) inset;
        -moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.15) inset;
        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.15) inset;
        -webkit-transition: all 0.7s ease 0s;
        -moz-transition: all 0.7s ease 0s;
        -o-transition: all 0.7s ease 0s;
        transition: all 0.7s ease 0s;
    }
    #create input[type="text"]:focus {
        width: 200px;
    }
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create New Real Customer </title>
    <link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
</head>
<body>
<div align="center" style="margin-top: 50px;">
    <form method="post" action="/AddNewRealCustomer" id="create">
        <br><br><br>
        <label> لطفا اطلاعات خود را وارد کنید : </label><br><br>
        <input name="firstName" type="text" size="40" placeholder="نام..." /><br><br>
        <input name="lastName" type="text" size="40" placeholder="نام خانوادگی..." /><br><br>
        <input name="fatherName" type="text" size="40" placeholder="نام پدر..." /><br><br>
        <input name="birthDay" type="text" size="40" placeholder="تاریخ تولد..." /><br><br>
        <input name="NationalId" type="text" size="40" placeholder="کد ملی..." /><br><br>
        <input type="submit" value="ایجاد" style=" border: 1px solid #d1d1d1; -webkit-border-radius: 20px; border-width: 3px;
        border-style: ridge; font: bold 13px Arial,Helvetica,Sans-serif; width: 110px;padding: 6px 15px 6px 10px;" > <br><br>
    </form>
</div>
</body>
</html>
