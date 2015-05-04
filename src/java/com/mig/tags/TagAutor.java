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
import java.util.ArrayList;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import com.mig.beans.Autor;

public class TagAutor extends BodyTagSupport {

        
    private String nombre;
    private List<Autor> select = new ArrayList();
    private String code;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSelect(List<Autor> select) {
        this.select = select;
    }

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        Autor autor = new Autor();
        autor.setCondicion("Order by 1");
        List<Autor> la = autor.getAutores();

        
 
        
        try {
            out.println("<table style=\"width:100%;border:0px\">");

            for (Autor a : la) {
                out.println("<tr>");

                out.println("<td style=\"width:50px\">");

                
                if (!select.isEmpty()) { 
                  boolean existe = false;
                  for (Autor aa : select) {
                      if (aa.getAutoAutor().intValue() == a.getAutoAutor().intValue()) {
                          existe = true;
                      }
                  }  
                  
                  if (existe) {
                    out.println("<input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + a.getAutoAutor() + "\" " + code + " checked/>");                    
                  } else {
                    out.println("<input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + a.getAutoAutor() + "\" " + code + "/>");                      
                  }  
                } else {
                    out.println("<input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + a.getAutoAutor() + "\" " + code + "/>");
                }

                out.println("</td>");
                out.println("<td>");
                out.println(a.getAutoNombre()+" "+a.getAutoApellidos());
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
