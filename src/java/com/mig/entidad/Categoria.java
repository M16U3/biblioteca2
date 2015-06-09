/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguel
 */
@Entity
@Table(name = "Categoria", catalog = "libreria", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByCateCategoria", query = "SELECT c FROM Categoria c WHERE c.cateCategoria = :cateCategoria"),
    @NamedQuery(name = "Categoria.findByCateDescrip", query = "SELECT c FROM Categoria c WHERE c.cateDescrip = :cateDescrip")})
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CATE_CATEGORIA")
    private Integer cateCategoria;
    @Column(name = "CATE_DESCRIP")
    private String cateDescrip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "librCategoria")
    private List<Libro> libroList;

    public Categoria() {
    }

    public Categoria(Integer cateCategoria) {
        this.cateCategoria = cateCategoria;
    }

    public Integer getCateCategoria() {
        return cateCategoria;
    }

    public void setCateCategoria(Integer cateCategoria) {
        this.cateCategoria = cateCategoria;
    }

    public String getCateDescrip() {
        return cateDescrip;
    }

    public void setCateDescrip(String cateDescrip) {
        this.cateDescrip = cateDescrip;
    }

    @XmlTransient
    public List<Libro> getLibroList() {
        return libroList;
    }

    public void setLibroList(List<Libro> libroList) {
        this.libroList = libroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cateCategoria != null ? cateCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.cateCategoria == null && other.cateCategoria != null) || (this.cateCategoria != null && !this.cateCategoria.equals(other.cateCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mig.entidad.Categoria[ cateCategoria=" + cateCategoria + " ]";
    }
    
}
