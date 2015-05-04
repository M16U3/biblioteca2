<%-- 
    Document   : error
    Created on : 28-abr-2015, 11:26:00
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
    </head>
    <body>
        <h1 style="color:red"><%= request.getParameter("msg") %></h1>
    </body>
</html>
