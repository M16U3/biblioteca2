/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.serv;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mig.beans.Autor;
import com.mig.exception.BibliotecaException;

/**
 *
 * @author miguel
 */
public class ServAutor extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        String error = null;
        
        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agrAutor")) {
         
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            
            Autor autor = new Autor();
            autor.setAutoNombre(nombre);
            autor.setAutoApellidos(apellidos);
            
            try {
               autor = autor.agrAutor(autor);            
            } catch (BibliotecaException e) {
                error = e.getMessage();
            }
            
            if (error == null) {
                this.VeaPagina("/app/indexAutor.jsp", request, response);                
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/error.jsp?msg="+error, request, response);
            }
            
        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("actAutor")) {
            
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            Long id = Long.valueOf(request.getParameter("autor"));
            
            Autor autor = new Autor();
            autor.setAutoAutor(id);
            autor.setAutoNombre(nombre);
            autor.setAutoApellidos(apellidos);            
            
            try {               
                autor.actAutor(autor);                
            } catch (BibliotecaException e) {
                error = e.getMessage();
            }
            
            
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            this.VeaPagina("/error.jsp?msg=Peticion erronea", request, response);
        }
        
        
    }

    
    private void VeaPagina(String direccion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(direccion);
        dispatcher.forward(request, response);
    }
}
