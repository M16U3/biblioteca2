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
import javax.servlet.http.HttpServletRequest;
import com.mig.beans.Usuario;

public class TagUsuarios extends BodyTagSupport {
    
    private String nombre;
    private List<Usuario> usuas;
    private String rol;
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public void setUsuarios(List<Usuario> usuas) {
        this.usuas = usuas;
    }
    
    @Override
    public int doStartTag() throws JspException {
        
        JspWriter out = pageContext.getOut();
        
        try {
        Usuario usua = new Usuario();
        usua.setCondicion(" Where usua_usuario in "
                        + "(Select usro_usuario From Usrol Where usro_rol = '"+rol+"') ");
        List<Usuario> lis = usua.getUsuarios();
        
        out.println("<select name=\""+nombre+"\">");
        for (Usuario u : lis) {
          out.println("<option value=\""+u.getUsuaUsuario()+"\">"+u.getUsuaUsuario()+"</option>");    
        }
        out.println("</select>");
        } catch (IOException e) {
            
        }
     return SKIP_BODY;   
    }
    
}
