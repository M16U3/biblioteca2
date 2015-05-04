/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.beans;

/**
 *
 * @author miguel
 */
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import myconector.Dao;
import com.mig.exception.BibliotecaException;



public class Usuario implements Serializable {
    
    private String usuaUsuario;
    private String usuaPassword;
    private String usuaNombre;
    private String usuaEmail;
    
    private String error;
    private List<Usuario> lista = new ArrayList();
    private Usuario usua;

    public String getUsuaNombre() {
        return usuaNombre;
    }

    public String getUsuaEmail() {
        return usuaEmail;
    }

    public String getUsuaPassword() {
        return usuaPassword;
    }

    public String getUsuaUsuario() {
        return usuaUsuario;
    }

    public void setUsuaEmail(String usuaEmail) {
        this.usuaEmail = usuaEmail;
    }

    public void setUsuaNombre(String usuaNombre) {
        this.usuaNombre = usuaNombre;
    }

    public void setUsuaPassword(String usuaPassword) {
        this.usuaPassword = usuaPassword;
    }

    public void setUsuaUsuario(String usuaUsuario) {
        this.usuaUsuario = usuaUsuario;
    }
    
    
    public void setId(String id) {
        
        try {
            
            Dao dao = new Dao();
            
            ResultSet rst = dao.consultar("SELECT USUA_USUARIO, " +
                                            "	USUA_PASSWORD, " +
                                            "	USUA_NOMBRE, " +
                                            "	USUA_EMAIL " +
                                            "FROM Usuario Where USUA_USUARIO = '"+id+"'"
                    );
            
            while (rst != null && rst.next()) {
                usua = new Usuario();
                usua.setUsuaUsuario(rst.getString("USUA_USUARIO"));
                usua.setUsuaPassword(rst.getString("USUA_PASSWORD"));
                usua.setUsuaEmail(rst.getString("USUA_EMAIL"));
                usua.setUsuaNombre(rst.getString("USUA_NOMBRE"));                
            }
            
            dao.salir();
            
        } catch (SQLException e) {
            e.printStackTrace();
            error = e.getMessage();
        }
        
    }
    
    
    public void setCondicion(String condicion) {
        
        try {
            
            Dao dao = new Dao();
            
            ResultSet rst = dao.consultar("SELECT USUA_USUARIO, " +
                                            "	USUA_PASSWORD, " +
                                            "	USUA_NOMBRE, " +
                                            "	USUA_EMAIL " +
                                            "FROM Usuario "+condicion
                    );
            
            while (rst != null && rst.next()) {
                usua = new Usuario();
                usua.setUsuaUsuario(rst.getString("USUA_USUARIO"));
                usua.setUsuaPassword(rst.getString("USUA_PASSWORD"));
                usua.setUsuaEmail(rst.getString("USUA_EMAIL"));
                usua.setUsuaNombre(rst.getString("USUA_NOMBRE"));    
                lista.add(usua);
            }
            
            dao.salir();
            
        } catch (SQLException e) {
            e.printStackTrace();
            error = e.getMessage();
        }
        
    }
     
    
    public List<Usuario> getUsuarios() {
        return lista;
    }
    
    public Usuario getUsuario() {
        return usua;
    }
    
    
    public Usuario agrUsuario(Usuario usuar) throws BibliotecaException {
     
        try {
                        
            Dao dao = new Dao();
            
            dao.modificar("Insert into Usuario "
                    + " (usua_usuario,usua_password,usua_email,usua_nombre) "
                    + " Values ('"+usuar.getUsuaUsuario()+"','"+usuar.getUsuaPassword()+"','"+usuar.getUsuaEmail()+"','"+usuar.getUsuaNombre()+"')");
            
            dao.salir();
            
        } catch (SQLException e) {
            throw new BibliotecaException(e);
        }
        
        
      return usuar;  
    }
    
    public void actUsuario(Usuario usuar) throws BibliotecaException {
        try {
            
            Dao dao = new Dao();
            
            dao.modificar("Update Usuario "
                    + " Set "
                    + "usua_nombre = '"+usuar.getUsuaNombre()+"', "
                    + "usua_email = '"+usuar.getUsuaEmail()+"',"
                    + "usua_password = '"+usuar.getUsuaPassword()+"' Where usua_usuario = '"+usuar.getUsuaUsuario()+"' ");
            
            dao.salir();
            
        } catch (SQLException e) {
            throw new BibliotecaException(e);
        }  
    }
    
    
     
    
    
}
