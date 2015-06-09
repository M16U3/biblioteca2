<%-- 
    Document   : buscarLibro
    Created on : 04-jun-2015, 14:03:06
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="/biblioteca2/js/jquery.js"></script>
        
        <script type="text/javascript">
            $(function() {
                
                $('#bus').click(function() {
                    
                    $.ajax({
                        url: '/biblioteca2/app/apartado.do',
                        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                        data: $('form[name="buscar"]').serialize(),
                        type: 'POST',
                        success: function(html) {
                            $('#regreso').html(html);
                        },
                        
                    });
                    
                    $('#msg').ajaxError(function (event, request, settings) {
                                $(this).html('<span style=\"padding:3px\">' + request.responseText + '</span>').addClass('ui-state-error ui-corner-all');
                            });
                    
                });
                
            });
            
        </script>
        
    </head>
    <div id="msg"></div>
    <body>
        <form name="buscar">
            
            <input type="text" maxlength="32" size="32" name="busca"/>
            <input id="bus" type="button" value="Buscar"/>
            <input type="hidden" name="opcion" value="busqueda"/>
        </form>
        <div id="regreso">
            
        </div>
    </body>
</html>
