<%-- 
    Document   : agrLibro
    Created on : 16-abr-2015, 14:02:26
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.mig.beans.Libro" %>
<%@page import="com.mig.beans.Categoria" %>
<%@page import="com.mig.beans.Editorial" %>
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
            String isbn = request.getParameter("isbn") != null ? request.getParameter("isbn") : null;

            Libro libr = null;
            Categoria cate = null;
            Autor auto = null;
            List<Categoria> lca = null;
            List<Autor> lau = null;

            if (isbn != null && opcion.equals("edit") || opcion.equals("detalle")) {

                libr = new Libro();
                libr.setId(isbn);
                libr = libr.getLibro();

                cate = new Categoria();
                cate.setCondicion(" Where cate_categoria in (Select lica_categoria From Librocategoria Where lica_isbn = '" + isbn + "') ");
                lca = cate.getCategorias();

                auto = new Autor();
                auto.setCondicion(" Where auto_autor in (Select alib_autor From AUTORLIBRO Where alib_isbn = '" + isbn + "') ");
                lau = auto.getAutores();
            }

        %>              

        <form name="forma" id="fform">
            <table style="width:600px;border:0">
                <tr>
                    <th>ISBN:</th>
                    <td>
                        <% if (opcion.equals("edit")) {%> 
                        <input type="hidden" name="isbn" value="<%= libr.getLibrIsbn()%>"/>
                        <%= libr.getLibrIsbn()%>
                        <% } else if (opcion.equals("detalle")) {%>
                        <%= libr.getLibrIsbn()%>
                        <% } else {%>
                        <input type="text" name="isbn" maxlength="20" size="21"/>
                        <% }%>
                    </td>
                </tr>
                <tr>
                    <th>Titulo:</th>
                    <td>
                        <% if (opcion.equals("edit")) {%> 
                        <textarea name="titulo" rows="8" cols="20" maxlength="200"><%= libr.getLibrTitulo()%></textarea>
                        <% } else if (opcion.equals("detalle")) {%>  
                        <%= libr.getLibrTitulo()%>
                        <% } else {%>
                        <textarea name="titulo" rows="8" cols="20" maxlength="200"></textarea>
                        <% }%>
                    </td>
                </tr>
                <tr>
                    <th>Edici&oacute;n:</th>
                    <td>
                        <% if (opcion.equals("edit")) {%>
                        <input type="text" name="edicion" maxlength="5" size="6" name="edicion" value="<%= libr.getLibrEdicion()%>"/>
                        <% } else if (opcion.equals("detalle")) {%>
                        <%= libr.getLibrEdicion()%>
                        <% } else {%>
                        <input type="text" name="edicion" maxlength="5" size="6" name="edicion"/>
                        <% }%>
                    </td>
                </tr>
                <tr>

                    <th>
                        Editorial:
                    </th>
                    <td>
                        <%@taglib prefix="edit" uri="/WEB-INF/tlds/editorial.tld" %>
                        <% if (opcion.equals("edit")) {%>
                        <edit:editorial nombre="editorial" select="<%= libr.getLibrEditorial().toString()%>" />
                        <% } else if (opcion.equals("detalle")) {%>
                        <%
                            String code = "disabled=\"true\" ";
                        %>
                        <edit:editorial nombre="editorial" select="<%= libr.getLibrEditorial().toString()%>" code="<%= code%>" />    
                        <% } else {%>
                        <edit:editorial nombre="editorial"/> 
                        <% }%>
                    </td>
                </tr>
                <tr>

                    <th>Cat&eacute;goria(s):</th>                
                    <td>                    
                        <%@taglib prefix="cate" uri="/WEB-INF/tlds/categoria.tld" %>
                        <% if (opcion.equals("edit")) {%>
                        <cate:categoria nombre="categoria" select="<%= lca%>"/>
                        <% } else if (opcion.equals("detalle")) {%>
                        <%
                            String code = "disabled=\"true\" ";
                        %>
                        <cate:categoria nombre="categoria" select="<%= lca%>" code="<%= code%>"/>
                        <% } else {%>
                        <cate:categoria nombre="categoria"/>
                        <% }%>
                    </td>
                </tr>
                <tr>

                    <th>Autor(es):</th>
                    <td>
                        <%@taglib prefix="auto" uri="/WEB-INF/tlds/autor.tld" %>
                        <% if (opcion.equals("edit")) {%>
                        <auto:autor nombre="autor" select="<%= lau%>" />
                        <% } else if (opcion.equals("detalle")) {%>
                        <%
                            String code = " disabled=\"true\" ";
                        %>
                        <auto:autor nombre="autor" select="<%= lau%>" code="<%= code%>" />
                        <% } else {%>
                        <auto:autor nombre="autor"/>
                        <% }%>
                    </td>
                </tr>
                <tr>

                    <th>Existencia:</th>
                    <td>
                        <% if (opcion.equals("edit")) {%> 
                        <input type="text" name="existencia" size="4" maxlength="8" value="<%= libr.getLibrExistencia()%>"/>
                        <% } else if (opcion.equals("detalle")) {%>
                        <%= libr.getLibrExistencia()%>
                        <% } else {%>
                        <input type="text" name="existencia" size="4" maxlength="8"/>
                        <% }%>
                    </td>
                </tr>
                <tr>                
                    <th>Numero de p&aacute:ginas:</th>
                    <td>
                        <% if (opcion.equals("edit")) {%> 
                        <input type="text" name="numPag" size="4" maxlength="8" value="<%= libr.getLibrNumPag()%>"/>
                        <% } else if (opcion.equals("detalle")) {%>
                        <%= libr.getLibrNumPag()%>
                        <% } else {%>
                        <input type="text" name="numPag" size="4" maxlength="8"/>
                        <% }%>
                    </td>
                </tr>
            </table>
            <div style="width:100%;text-align:center">
                <script type="text/javascript">
                    $(function() {
                        $('#env').click(function() {
                            $.ajax({
                                url: '/biblioteca2/app/libro.do',
                                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                                data: $('#fform').serialize(),
                                type: 'POST',
                                
                                success: function(html) {
                                    $('#msg').html('');
                                    $('#principal').html(html);
                                }
                            });
                            $('#msg').ajaxError(function(event, request, settings) {
                               $(this).html('<span style=\"padding:3px\">' + request.responseText + '</span>').addClass('ui-state-error ui-corner-all');                                                    
                            });
                        });
                    });
                </script>
                <% if (opcion.equals("insert")) {%>                 
                <input type="hidden" name="opcion" value="agrLibro"/>
                <input id="env" type="button" value="Agregar"/>
                <% } else if (opcion.equals("edit")) {%>
                <input type="hidden" name="opcion" value="actLibro"/>
                <input id="env" type="button" value="Actualizar"/>
                <% }%>
            </div>
        </form>


    </body>
</html>
