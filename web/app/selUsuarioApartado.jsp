<%-- 
    Document   : selUsuarioApartado
    Created on : 09-jun-2015, 14:03:43
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mig.beans.Libro" %>
<% 
   String isbn = request.getParameter("isbn");
   Libro l = new Libro();
   l.setId(isbn);
%>
<form name="fapart">
<table style="width:400px">
    <tr>
        <th>Titulo: <%= l.getLibrTitulo() %></th>
    </tr>
    <tr>
        <td>
            
        </td>
    </tr>
    
</table>
    <div style="text-align:center">
        <input type="button" value="Apartar" id="bl"/>
    </div>
</form>
