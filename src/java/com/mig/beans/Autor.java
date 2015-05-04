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
import java.sql.SQLException;
import java.sql.ResultSet;
import com.mig.exception.BibliotecaException;
import myconector.Dao;


public class Autor implements Serializable {
    
    private Long autoAutor;
    private String autoNombre;
    private String autoApellidos;
    
    private String error;
    
    private List<Autor> lista = new ArrayList();
    private Autor auto;

    public String getAutoApellidos() {
        return autoApellidos;
    }

    public Long getAutoAutor() {
        return autoAutor;
    }

    public String getAutoNombre() {
        return autoNombre;
    }

    public void setAutoApellidos(String autoApellidos) {
        this.autoApellidos = autoApellidos;
    }

    public void setAutoAutor(Long autoAutor) {
        this.autoAutor = autoAutor;
    }

    public void setAutoNombre(String autoNombre) {
        this.autoNombre = autoNombre;
    }
    
    
    public void setId(Long id) {
        
        try {
            
            Dao dao = new Dao();
            ResultSet rst = dao.consultar("Select auto_autor,auto_nombre,auto_apellidos From Autor Where auto_autor = '"+id+"'");
            
            while (rst != null && rst.next()) {
                auto = new Autor();
                auto.setAutoAutor(rst.getLong("auto_autor"));
                auto.setAutoNombre(rst.getString("auto_nombre"));
                auto.setAutoApellidos(rst.getString("auto_apellidos"));
            }
            
            dao.salir();
            
        } catch (SQLException e) {
            error = e.getMessage();
        }
        
    }
    
    public void setCondicion(String condicion) {
        
        try {
            
            Dao dao = new Dao();
            ResultSet rst = dao.consultar("Select auto_autor,auto_nombre,auto_apellidos From Autor "+condicion);
            
            while (rst != null && rst.next()) {
                auto = new Autor();
                auto.setAutoAutor(rst.getLong("auto_autor"));
                auto.setAutoNombre(rst.getString("auto_nombre"));
                auto.setAutoApellidos(rst.getString("auto_apellidos"));
                lista.add(auto);
            }
            
            dao.salir();
            
        } catch (SQLException e) {
            error = e.getMessage();
        }
        
    }
    
    
    
    public List<Autor> buscarXAutor(String cadena) {        
        setCondicion("Where auto_nombre like '%cadena%' ");
        return this.getAutores();
    }
    
    
    public Autor agrAutor(Autor auto) throws BibliotecaException {
        
        try {
            
            Dao dao = new Dao();
            auto.setAutoAutor(dao.insertar("Insert into Autor (auto_nombre,auto_apellidos) values ('"+auto.getAutoNombre()+"','"+auto.getAutoApellidos()+"') "));
            dao.salir();            
        } catch (SQLException e) {
            error = e.getMessage();
            throw new BibliotecaException(e);
        }        
        
      return auto;
    }
    
    public void actAutor(Autor auto) throws BibliotecaException {
    
        try {
            
            Dao dao = new Dao();
            dao.modificar("Update Autor Set auto_nombre = '"+auto.getAutoNombre()+"', auto_apellidos = '"+auto.getAutoApellidos()+"' Where auto_autor = '"+auto.getAutoAutor()+"'");
            dao.salir();
        } catch (SQLException e) {
            error = e.getMessage();
            throw new BibliotecaException(e);
        }
          
    }
    
    public List<Autor> getAutores() {
        return lista;
    }
    
    public Autor getAutor() {
        return auto;
    }
    
    public String getError() {
        return error;
    }
}
