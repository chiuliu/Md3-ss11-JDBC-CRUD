<%--
  Created by IntelliJ IDEA.
  User: Giang
  Date: 5/6/2024
  Time: 11:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Student</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/StudentServlet" method="post">
    <table>
        <tr>
            <td>Student ID</td>
            <td><input type="text" name="studentId"></td>
        </tr>
        <tr>
            <td>Student Name</td>
            <td><input type="text" name="studentName"></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="number" name="age"></td>
        </tr>
        <tr>
            <td>Birth Date</td>
            <td><input type="date" name="birthDate"></td>
        </tr>
        <tr>
            <td>Student ID</td>
            <td>
                <input type="radio" name="status" id="active" value="true" checked/><label for="active">Active</label>
                <input type="radio" name="status" id="inactive" value="false"/><label for="inactive">InActive</label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Create" name="action">
            </td>
        </tr>
    </table>
</form>
</body>
</html>