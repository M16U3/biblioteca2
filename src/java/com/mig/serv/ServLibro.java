/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.serv;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import com.mig.beans.Libro;
import com.mig.exception.BibliotecaException;

/**
 *
 * @author miguel
 */
public class ServLibro extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String error = null;
        if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("agrLibro")) {
            String isbn = request.getParameter("isbn");
            String titulo = request.getParameter("titulo");
            String edicion = request.getParameter("edicion");
            Long editorial = Long.valueOf(request.getParameter("editorial"));
            Long numPag = Long.valueOf(request.getParameter("numPag"));
            Long existencia = Long.valueOf(request.getParameter("existencia"));

            try {
                Libro lib = new Libro();
                lib.setLibrIsbn(isbn);
                lib.setLibrEdicion(edicion);
                lib.setLibrEditorial(editorial);
                lib.setLibrExistencia(existencia);
                lib.setLibrNumPag(numPag);
                lib.setLibrTitulo(titulo);
                lib = lib.agrLibro(lib);

                // Agrego autores
                for (int i = 0; i < request.getParameterValues("autor").length; i++) {
                    lib.asignarAutor(isbn, Long.valueOf(request.getParameterValues("autor")[i]));
                }

                for (int i = 0; i < request.getParameterValues("categoria").length; i++) {
                    lib.asignarCategoria(isbn, Long.valueOf(request.getParameterValues("categoria")[i]));
                }
                
            } catch (BibliotecaException e) {
                error = e.getMessage();
            }

            if (error == null) {
                this.VeaPagina("/app/listLibro.jsp", request, response);
            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                this.VeaPagina("/error.jsp?msg=" + error, request, response);
            }

        } else if (request.getParameter("opcion") != null && request.getParameter("opcion").equals("actLibro")) {

            String isbn = request.getParameter("isbn");
            String titulo = request.getParameter("titulo");
            String edicion = request.getParameter("edicion");
            Long editorial = Long.valueOf(request.getParameter("editorial"));
            Long numPag = Long.valueOf(request.getParameter("numPag"));
            Long existencia = Long.valueOf(request.getParameter("existencia"));

            try {
                Libro lib = new Libro();

                lib.setLibrIsbn(isbn);
                lib.setLibrEdicion(edicion);
                lib.setLibrEditorial(editorial);
                lib.setLibrExistencia(existencia);
                lib.setLibrNumPag(numPag);
                lib.setLibrTitulo(titulo);                
                lib.actLibro(lib);

                for (int i = 0; i < request.getParameterValues("autor").length; i++) {
                    lib.desasinarAutor(isbn, Long.valueOf(request.getParameterValues("autor")[i]));
                    lib.asignarAutor(isbn, Long.valueOf(request.getParameterValues("autor")[i]));
                }

                for (int i = 0; i < request.getParameterValues("categoria").length; i++) {
                    lib.desasinarCategoria(isbn, Long.valueOf(request.getParameterValues("categoria")[i]));
                    lib.asignarCategoria(isbn, Long.valueOf(request.getParameterValues("categoria")[i]));
                }
                
            } catch (BibliotecaException e) {
                error = e.getMessage();
            }

            if (error == null) {
                this.VeaPagina("/app/listLibro.jsp", request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                this.VeaPagina("/error.jsp?msg=" + error, request, response);
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
