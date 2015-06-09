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
@Table(name = "Editorial", catalog = "libreria", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Editorial.findAll", query = "SELECT e FROM Editorial e"),
    @NamedQuery(name = "Editorial.findByEditEditorial", query = "SELECT e FROM Editorial e WHERE e.editEditorial = :editEditorial"),
    @NamedQuery(name = "Editorial.findByEditDescrip", query = "SELECT e FROM Editorial e WHERE e.editDescrip = :editDescrip")})
public class Editorial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EDIT_EDITORIAL")
    private Integer editEditorial;
    @Basic(optional = false)
    @Column(name = "EDIT_DESCRIP")
    private String editDescrip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "librEditorial")
    private List<Libro> libroList;

    public Editorial() {
    }

    public Editorial(Integer editEditorial) {
        this.editEditorial = editEditorial;
    }

    public Editorial(Integer editEditorial, String editDescrip) {
        this.editEditorial = editEditorial;
        this.editDescrip = editDescrip;
    }

    public Integer getEditEditorial() {
        return editEditorial;
    }

    public void setEditEditorial(Integer editEditorial) {
        this.editEditorial = editEditorial;
    }

    public String getEditDescrip() {
        return editDescrip;
    }

    public void setEditDescrip(String editDescrip) {
        this.editDescrip = editDescrip;
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
        hash += (editEditorial != null ? editEditorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editorial)) {
            return false;
        }
        Editorial other = (Editorial) object;
        if ((this.editEditorial == null && other.editEditorial != null) || (this.editEditorial != null && !this.editEditorial.equals(other.editEditorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mig.entidad.Editorial[ editEditorial=" + editEditorial + " ]";
    }
    
}
