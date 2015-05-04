/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.tags;

/**
 *
 * @author miguel
 */
import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import com.mig.beans.Autor;

public class TagGridAutor extends BodyTagSupport {

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();

        try {

            Autor autor = new Autor();
            autor.setCondicion("Order by 1 ");
            List<Autor> la = autor.getAutores();
            out.println("<table style=\"width:100%;border:0\">");

            out.println("<tr>");
            out.println("<th>&nbsp;</th>");
            out.println("<th>&nbsp;</th>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Apellidos</th>");
            out.println("</tr>");

            for (Autor a : la) {
                out.println("<tr>");
                out.println("<td><input type=\"button\" value=\"Detalle\" onclick=\"detalle('" + a.getAutoAutor() + "');\"></td>");
                out.println("<td><input type=\"button\" value=\"Editar\" onclick=\"editar('" + a.getAutoAutor() + "');\"></td>");
                out.println("<td>" + autor.getAutoAutor().toString() + "</td>");
                out.println("<td>" + autor.getAutoNombre() + "</td>");
                out.println("<td>" + autor.getAutoApellidos() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

}
