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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "Libro", catalog = "libreria", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l"),
    @NamedQuery(name = "Libro.findByLibrIsbn", query = "SELECT l FROM Libro l WHERE l.librIsbn = :librIsbn"),
    @NamedQuery(name = "Libro.findByLibrTitulo", query = "SELECT l FROM Libro l WHERE l.librTitulo = :librTitulo"),
    @NamedQuery(name = "Libro.findByLibrEdicion", query = "SELECT l FROM Libro l WHERE l.librEdicion = :librEdicion")})
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LIBR_ISBN")
    private String librIsbn;
    @Basic(optional = false)
    @Column(name = "LIBR_TITULO")
    private String librTitulo;
    @Basic(optional = false)
    @Column(name = "LIBR_EDICION")
    private String librEdicion;
    @ManyToMany(mappedBy = "libroList")
    private List<Autor> autorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aparIsbn")
    private List<Apartado> apartadoList;
    @JoinColumn(name = "LIBR_CATEGORIA", referencedColumnName = "CATE_CATEGORIA")
    @ManyToOne(optional = false)
    private Categoria librCategoria;
    @JoinColumn(name = "LIBR_EDITORIAL", referencedColumnName = "EDIT_EDITORIAL")
    @ManyToOne(optional = false)
    private Editorial librEditorial;

    public Libro() {
    }

    public Libro(String librIsbn) {
        this.librIsbn = librIsbn;
    }

    public Libro(String librIsbn, String librTitulo, String librEdicion) {
        this.librIsbn = librIsbn;
        this.librTitulo = librTitulo;
        this.librEdicion = librEdicion;
    }

    public String getLibrIsbn() {
        return librIsbn;
    }

    public void setLibrIsbn(String librIsbn) {
        this.librIsbn = librIsbn;
    }

    public String getLibrTitulo() {
        return librTitulo;
    }

    public void setLibrTitulo(String librTitulo) {
        this.librTitulo = librTitulo;
    }

    public String getLibrEdicion() {
        return librEdicion;
    }

    public void setLibrEdicion(String librEdicion) {
        this.librEdicion = librEdicion;
    }

    @XmlTransient
    public List<Autor> getAutorList() {
        return autorList;
    }

    public void setAutorList(List<Autor> autorList) {
        this.autorList = autorList;
    }

    @XmlTransient
    public List<Apartado> getApartadoList() {
        return apartadoList;
    }

    public void setApartadoList(List<Apartado> apartadoList) {
        this.apartadoList = apartadoList;
    }

    public Categoria getLibrCategoria() {
        return librCategoria;
    }

    public void setLibrCategoria(Categoria librCategoria) {
        this.librCategoria = librCategoria;
    }

    public Editorial getLibrEditorial() {
        return librEditorial;
    }

    public void setLibrEditorial(Editorial librEditorial) {
        this.librEditorial = librEditorial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (librIsbn != null ? librIsbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.librIsbn == null && other.librIsbn != null) || (this.librIsbn != null && !this.librIsbn.equals(other.librIsbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mig.entidad.Libro[ librIsbn=" + librIsbn + " ]";
    }
    
}
