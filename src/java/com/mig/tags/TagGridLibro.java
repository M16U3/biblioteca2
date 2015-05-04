/*
 * To change this template, choose Tools | Templates
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
import com.mig.beans.Libro;
import com.mig.beans.Editorial;

public class TagGridLibro extends BodyTagSupport {

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();

        try {
            Libro libr = new Libro();
            libr.setCondicion(" Order by 1 ");
            List<Libro> lb = libr.getLibros();

            out.println("<table style=\"width:100%;border:0\">");
            out.println("<tr>");
            out.println("<th>&nbsp;</th>");
            out.println("<th>&nbsp;</th>");
            out.println("<th>ISBN</th>");
            out.println("<th>Titulo</th>");
            out.println("<th>Editorial</th>");
            out.println("<th nowrap>Edici&oacute;n</th>");
            out.println("</tr>");

            for (Libro l : lb) {
                out.println("<tr>");
                out.println("<td><input type=\"button\" value=\"Detalle\" onclick=\"detalle('"+l.getLibrIsbn()+"');\"></td>");
                out.println("<td><input type=\"button\" value=\"Editar\" onclick=\"editar('"+l.getLibrIsbn()+"');\"></td>");
                out.println("<td>"+l.getLibrIsbn()+"</td>");
                out.println("<td nowrap>"+l.getLibrTitulo()+"</td>");
                
                Editorial e = new Editorial();
                e.setId(l.getLibrEditorial());
                
                out.println("<td>"+e.getEditorial().getEditDescrip()+"</td>");
                out.println("<td>"+l.getLibrEdicion()+"</td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } catch (IOException e) {
            throw new JspException(e);
        }


        return SKIP_BODY;
    }
}
