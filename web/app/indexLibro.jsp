<%-- 
    Document   : index
    Created on : 28-abr-2015, 10:58:54
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
        <script type="text/javascript" src="/biblioteca2/js/jquery.js"></script>
    </head>
    <body>
        <div id="msg"></div>
        <div id="menu">
            <script type="text/javascript">
                $(function() {
                  $('#lb').click(function(){  
                    $.ajax({
                                url: '/biblioteca2/app/listLibro.jsp',
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
                  });
                  
                  $('#agl').click(function(){  
                    $.ajax({
                                url: '/biblioteca2/app/fLibro.jsp',
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
                  });
                  
                });
            </script>
            
            <input id="lb" type="button" value="Lista libros"/><input id="agl" type="button" value="Agregar Libro"/>
        </div>
        <div id="principal">      
            <jsp:include page="listLibro.jsp"/>
        </div>
        
    </body>
</html>
