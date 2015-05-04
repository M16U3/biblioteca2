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
import com.mig.beans.Categoria;


public class TagCategoria extends BodyTagSupport {

    private String nombre;
    private String code;
    private List<Categoria> select = new ArrayList();

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSelect(List<Categoria> select) {
        this.select = select;
    }

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        Categoria cate = new Categoria();
        cate.setCondicion("Order by 1");
        List<Categoria> lc = cate.getCategorias();

        try {

            out.println("<table style=\"width:100%;border:0px\">");

            for (Categoria c : lc) {
                out.println("<tr>");

                out.println("<td style=\"width:50px\">");

                if (!select.isEmpty()) {
                    boolean existe = false;
                    
                    for (Categoria cc : select) {
                        if (cc.getCateCategoria().intValue() == c.getCateCategoria().intValue()) {
                            existe = true;
                        }
                    }
                   if (existe) { 
                     out.println("<input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + c.getCateCategoria() + "\" " + code + " checked/>");                    
                   } else {
                     out.println("<input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + c.getCateCategoria() + "\" " + code + "/>");                      
                   }  
                } else {
                    out.println("<input type=\"checkbox\" name=\"" + nombre + "\" value=\"" + c.getCateCategoria() + "\" " + code + "/>");
                }

                out.println("</td>");
                out.println("<td>");
                out.println(c.getCateDescrip());
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
