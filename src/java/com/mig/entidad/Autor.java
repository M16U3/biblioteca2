/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miguel
 */
@Entity
@Table(name = "Autor", catalog = "libreria", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autor.findAll", query = "SELECT a FROM Autor a"),
    @NamedQuery(name = "Autor.findByAutoAutor", query = "SELECT a FROM Autor a WHERE a.autoAutor = :autoAutor"),
    @NamedQuery(name = "Autor.findByAutoNombre", query = "SELECT a FROM Autor a WHERE a.autoNombre = :autoNombre"),
    @NamedQuery(name = "Autor.findByAutoApellidos", query = "SELECT a FROM Autor a WHERE a.autoApellidos = :autoApellidos")})
public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AUTO_AUTOR")
    private Integer autoAutor;
    @Basic(optional = false)
    @Column(name = "AUTO_NOMBRE")
    private String autoNombre;
    @Basic(optional = false)
    @Column(name = "AUTO_APELLIDOS")
    private String autoApellidos;
    @JoinTable(name = "Autorlibro", joinColumns = {
        @JoinColumn(name = "ALIB_AUTOR", referencedColumnName = "AUTO_AUTOR")}, inverseJoinColumns = {
        @JoinColumn(name = "ALIB_ISBN", referencedColumnName = "LIBR_ISBN")})
    @ManyToMany
    private List<Libro> libroList;

    public Autor() {
    }

    public Autor(Integer autoAutor) {
        this.autoAutor = autoAutor;
    }

    public Autor(Integer autoAutor, String autoNombre, String autoApellidos) {
        this.autoAutor = autoAutor;
        this.autoNombre = autoNombre;
        this.autoApellidos = autoApellidos;
    }

    public Integer getAutoAutor() {
        return autoAutor;
    }

    public void setAutoAutor(Integer autoAutor) {
        this.autoAutor = autoAutor;
    }

    public String getAutoNombre() {
        return autoNombre;
    }

    public void setAutoNombre(String autoNombre) {
        this.autoNombre = autoNombre;
    }

    public String getAutoApellidos() {
        return autoApellidos;
    }

    public void setAutoApellidos(String autoApellidos) {
        this.autoApellidos = autoApellidos;
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
        hash += (autoAutor != null ? autoAutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.autoAutor == null && other.autoAutor != null) || (this.autoAutor != null && !this.autoAutor.equals(other.autoAutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mig.entidad.Autor[ autoAutor=" + autoAutor + " ]";
    }
    
}
