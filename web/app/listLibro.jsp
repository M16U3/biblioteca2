<%-- 
    Document   : list
    Created on : 28-abr-2015, 11:30:24
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <script type="text/javascript">
                function detalle(isbn) {
                   var url = '/biblioteca2/app/fLibro.jsp?isbn='+isbn+'&opcion=detalle';
                   $.ajax({
                                url: url,
                                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                                type: 'POST',
                                
                                success: function(html) {
                                    $('#msg').html('');
                                    $('#principal').html(html);
                                }
                            });
                            $('#msg').ajaxError(function(event, request, settings) {
                               $(this).html('<span style=\"padding:3px\">' + request.responseText + '</span>').addClass('ui-state-error ui-corner-all');                                                    
                            });
                }
                
                function editar(isbn) {
                    var url = '/biblioteca2/app/fLibro.jsp?isbn='+isbn+'&opcion=edit';
                    $.ajax({
                                url: url,
                                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                                type: 'POST',
                                
                                success: function(html) {
                                    $('#msg').html('');
                                    $('#principal').html(html);
                                }
                            });
                            $('#msg').ajaxError(function(event, request, settings) {
                               $(this).html('<span style=\"padding:3px\">' + request.responseText + '</span>').addClass('ui-state-error ui-corner-all');                                                    
                            });
                            
                }
            </script>
            <%@taglib prefix="lista" uri="/WEB-INF/tlds/listaLibro.tld" %>
            <lista:libros/> 
    </body>
</html>
