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
import com.mig.beans.Editorial;

public class TagEditorial extends BodyTagSupport {

    private String nombre;
    private String select;
    private String code;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        Editorial edit = new Editorial();
        edit.setCondicion(" Order by 1 ");
        List<Editorial> lista = edit.getEditoriales();

        try {
            out.println("<select name=\"" + nombre + "\" " + code + ">");
            if (select == null) {
                out.println("<option value=\"\">----</option>");
            }
            for (Editorial e : lista) {
                if (select != null && String.valueOf(e.getEditEditorial()).equals(select)) {
                    out.println("<option value=\"" + e.getEditEditorial() + "\" selected>" + e.getEditDescrip() + "</option>");
                } else {
                    out.println("<option value=\"" + e.getEditEditorial() + "\">" + e.getEditDescrip() + "</option>");
                }
            }
            out.println("</select>");
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
