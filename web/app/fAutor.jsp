<%-- 
    Document   : fAutor
    Created on : 03-may-2015, 22:54:10
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mig.beans.Autor" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="/biblioteca2/js/jquery.js"></script>
    </head>
    <body>

        <%
            String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "insert";
            Long autor = Long.valueOf(request.getParameter("autor"));

            Autor auto = null;
            if (opcion.equals("edit") || opcion.equals("detalle")) {
                auto = new Autor();
                auto.setId(autor);
                auto = auto.getAutor();
            }

        %>


        <form name="forma" id="fform">
            <table style="width:600px;border:0">
                <tr>
                    <th>ID Autor:</th>
                    <td>
                        <% if (opcion.equals("edit")) {%> 
                        <input type="hidden" name="autor" value="<%= auto.getAutoAutor()%>"/>
                        <%= auto.getAutoAutor()%>
                        <% } else if (opcion.equals("detalle")) {%>
                        <%= auto.getAutoAutor()%>                            
                        <% }%>
                    </td>
                </tr>
                <tr>
                    <th>Nombre:</th>
                    <td>
                        <% if (opcion.equals("edit")) {%> 
                        <input type="text" name="autor" value="<%= auto.getAutoNombre()%>" size="32" maxlength="50"/>
                        <% } else if (opcion.equals("detalle")) {%>  
                        <%= auto.getAutoNombre()%>
                        <% } else {%>
                        <input type="text" name="autor" size="32" maxlength="50"/>
                        <% }%>
                    </td>
                </tr>
                <tr>
                    <th>Edici&oacute;n:</th>
                    <td>
                        <% if (opcion.equals("edit")) {%>
                        <input type="text" name="apellidos" maxlength="80" size="32" value="<%= auto.getAutoApellidos()%>"/>
                        <% } else if (opcion.equals("detalle")) {%>
                        <%= auto.getAutoApellidos()%>
                        <% } else {%>
                        <input type="text" name="apellidos" maxlength="80" size="32" />
                        <% }%>
                    </td>
                </tr>
            </table>
            <div style="width:100%;text-align:center">
                <script type="text/javascript">
                    $(function () {
                        $('#env').click(function () {
                            $.ajax({
                                url: '/biblioteca2/app/autor.do',
                                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                                data: $('#fform').serialize(),
                                type: 'POST',
                                success: function (html) {
                                    $('#msg').html('');
                                    $('#principal').html(html);
                                }
                            });
                            $('#msg').ajaxError(function (event, request, settings) {
                                $(this).html('<span style=\"padding:3px\">' + request.responseText + '</span>').addClass('ui-state-error ui-corner-all');
                            });
                        });
                    });
                </script>
                <% if (opcion.equals("insert")) {%>                 
                <input type="hidden" name="opcion" value="agrAutor"/>
                <input id="env" type="button" value="Agregar"/>
                <% } else if (opcion.equals("edit")) {%>
                <input type="hidden" name="opcion" value="actAutor"/>
                <input id="env" type="button" value="Actualizar"/>
                <% }%>
            </div>
        </form>


    </body>
</html>
