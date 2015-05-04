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


public class Categoria implements Serializable {
    
    private Long cateCategoria;
    private String cateDescrip;

    private String error;
    
    private List<Categoria> lista = new ArrayList();
    private Categoria cate;
    
    public Long getCateCategoria() {
        return cateCategoria;
    }

    public String getCateDescrip() {
        return cateDescrip;
    }

    public void setCateCategoria(Long cateCategoria) {
        this.cateCategoria = cateCategoria;
    }

    public void setCateDescrip(String cateDescrip) {
        this.cateDescrip = cateDescrip;
    }
    
    public void setId(Long id) {        
        try {
            
            Dao dao = new Dao();
            ResultSet rst = dao.consultar("Select cate_categoria,cate_Descrip From categoria Where cate_categoria = '"+id+"'");
            
            while (rst != null && rst.next()) {
                cate = new Categoria();
                cate.setCateCategoria(rst.getLong("cate_categoria"));
                cate.setCateDescrip(rst.getString("cate_descrip"));
            }
            
            dao.salir();
            
        } catch (SQLException e) {
            error = e.getMessage();
        }                
    }
    
    public void setCondicion(String condicion) {        
        try {
            
            Dao dao = new Dao();
            ResultSet rst = dao.consultar("Select cate_categoria,cate_Descrip From categoria "+condicion);
            
            while (rst != null && rst.next()) {
                cate = new Categoria();
                cate.setCateCategoria(rst.getLong("cate_categoria"));
                cate.setCateDescrip(rst.getString("cate_descrip"));
                lista.add(cate);
            }
            
            dao.salir();
            
        } catch (SQLException e) {
            error = e.getMessage();
        }                
    }
    
    public Categoria agrCategoria(Categoria cate) {
        
        try {
            
            Dao dao = new Dao();
            cate.setCateCategoria(dao.insertar("Insert into Categoria (cate_descrip) values ('"+cate.getCateDescrip()+"')"));
            dao.salir();
            
        } catch (SQLException e) {
            error = e.getMessage();
        }
     return cate;   
    }

    
    public void actCategoria(Categoria cate) {
        
        try {
            
            Dao dao = new Dao();
            dao.modificar("Update Categoria Set cate_descrip = '"+cate.getCateDescrip()+"' Where cate_categoria = '"+cate.getCateCategoria()+"'");
            dao.salir();
            
        } catch (SQLException e) {
            error = e.getMessage();
        }
        
    }
    
    public List<Categoria> getCategorias() {
        return lista;
    }
    
    public Categoria getCategoria() {
        return cate;
    }
    
    public String getError() {
        return error;
    }
    
}
