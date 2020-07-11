<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JDBC Debug, Created by Tanmoy Roy</title>
</head>
<body>

<h1> <center> Get Branches From List</h1>
<hr>
<form name="branchfetch" method="post" action="BranchFind">
    <input type="submit" value="Find All Branch" />
</form>
<form name="branchfetch" method="post" action="BranchFind">
<select name="branchState">
    <c:forEach items="${stateNames}" var="state">
        <option value="${state}">${state}</option>
    </c:forEach>
</select>
    <input type="submit" value="Find Branch" />
</form>

</body>
</html>