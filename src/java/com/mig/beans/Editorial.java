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
import myconector.Dao;

public class Editorial implements Serializable {
    
    private Long editEditorial;
    private String editDescrip;

    private String error;
    private List<Editorial> lista = new ArrayList();
    private Editorial edit;
    
    
    
    public Editorial() {
    }

    public String getEditDescrip() {
        return editDescrip;
    }

    public Long getEditEditorial() {
        return editEditorial;
    }

    public void setEditDescrip(String editDescrip) {
        this.editDescrip = editDescrip;
    }

    public void setEditEditorial(Long editEditorial) {
        this.editEditorial = editEditorial;
    }
    
    
    public void setId(Long id) {
        
        try {
            
            Dao dao = new Dao();
            ResultSet rst = dao.consultar("Select edit_editorial,edit_descrip From Editorial Where edit_editorial = '"+id+"'");
            while (rst != null && rst.next()) {
                edit = new Editorial();
                edit.setEditEditorial(rst.getLong("edit_editorial"));
                edit.setEditDescrip(rst.getString("edit_descrip"));
            }
            
            dao.salir();
            
        } catch (SQLException e) {
            error = e.getMessage();
        }        
    }
    
    
    public void setCondicion(String condicion) {
        try {
            
            Dao dao = new Dao();
            ResultSet rst = dao.consultar("Select edit_editorial,edit_descrip From Editorial "+condicion);
            while (rst != null && rst.next()) {
                edit = new Editorial();
                edit.setEditEditorial(rst.getLong("edit_editorial"));
                edit.setEditDescrip(rst.getString("edit_descrip"));
                lista.add(edit);
            }
            
            dao.salir();
            
        } catch (SQLException e) {
            error = e.getMessage();
        }                     
    }
    
    
    public Editorial agrEditorial(Editorial edit) {         
        try {
            
            Dao dao = new Dao();
            edit.setEditEditorial(dao.insertar("Insert into Editorial (edit_descrip) values ('"+edit.getEditDescrip()+"') "));        
            dao.salir();
        } catch (SQLException e) {
            error = e.getMessage();
        }
     return edit;   
    } 
    
    public void actEditorial(Editorial edit) {        
        try {            
            Dao dao = new Dao();
            dao.modificar("Update Editorial Set edit_descrip = '"+edit.getEditDescrip()+"' Where edit_editorial = '"+edit.getEditEditorial()+"'");
            dao.salir();
        } catch (SQLException e) {
            error = e.getMessage();
        }        
    }
    
    public List<Editorial> getEditoriales() {
        return lista;
    }
    
    public Editorial getEditorial() {
        return edit;
    }
}
