/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import com.mig.beans.Libro;
/**
 *
 * @author miguel
 */
public class ServApartado extends HttpServlet {


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("busqueda") ) {
            
            String q = request.getParameter("busca");
            
            Libro libro = new Libro();
            List<Libro> ll = libro.buscarTitulo(q);
            
            if (ll.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                this.VeaPagina("/msg.jsp?msg=No hubo coincidencias para "+q, request, response);
            } else {
                request.getSession().setAttribute("lista", ll);
                request.getSession().setAttribute("consulta", true);
                this.VeaPagina("/app/lgrid.jsp", request, response);
            }
            
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            this.VeaPagina("/msg.jsp?msg=No se encontro esa opcion", request, response);
        }

    }
    private void VeaPagina(String direccion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(direccion);
        dispatcher.forward(request, response);
    }
    
    
}
