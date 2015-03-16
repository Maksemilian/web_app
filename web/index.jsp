<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="application.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Записная книга</title>
    </head>
    <body>
        <h2>Записная книжка</h2>
        <% EntityMy entity = new EntityMy();%>
        <% ReadFile f2 = new ReadFile();%>
        
        <h3>Добавление/Изменение/Удаление записи по номеру</h3>
        <form name="test" method="post" action="NewServlet">
        <input type="text" name="ID">
        <input type="text" name="Date">
        <input type="text" name="Note">
        <input type="submit" name="Button" value="Add">
        <input type="submit" name="Button" value="Edit">
        <input type="submit" name="Button" value="Delete">
        <input type="reset" name="Button" value="Reset">
        </form>
           
        <table>
           <tr>
               <th>Date</th>
               <th>Note</th>
           </tr>
           
           <% for(EntityMy e2: f2.readFile()){ %>
           <tr>
               <td><%=e2.getDt()%></td>
               <td><%=e2.getNote()%></td>
           </tr>
           <% }%>
        </table>
    </body>
</html>
