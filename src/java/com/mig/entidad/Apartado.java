/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguel
 */
@Entity
@Table(name = "Apartado", catalog = "libreria", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apartado.findAll", query = "SELECT a FROM Apartado a"),
    @NamedQuery(name = "Apartado.findByAparApartado", query = "SELECT a FROM Apartado a WHERE a.aparApartado = :aparApartado"),
    @NamedQuery(name = "Apartado.findByAparFecha", query = "SELECT a FROM Apartado a WHERE a.aparFecha = :aparFecha")})
public class Apartado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "APAR_APARTADO")
    private Integer aparApartado;
    @Basic(optional = false)
    @Column(name = "APAR_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aparFecha;
    @JoinColumn(name = "APAR_ISBN", referencedColumnName = "LIBR_ISBN")
    @ManyToOne(optional = false)
    private Libro aparIsbn;
    @JoinColumn(name = "APAR_USUARIO", referencedColumnName = "USUA_USUARIO")
    @ManyToOne(optional = false)
    private Usuario aparUsuario;

    public Apartado() {
    }

    public Apartado(Integer aparApartado) {
        this.aparApartado = aparApartado;
    }

    public Apartado(Integer aparApartado, Date aparFecha) {
        this.aparApartado = aparApartado;
        this.aparFecha = aparFecha;
    }

    public Integer getAparApartado() {
        return aparApartado;
    }

    public void setAparApartado(Integer aparApartado) {
        this.aparApartado = aparApartado;
    }

    public Date getAparFecha() {
        return aparFecha;
    }

    public void setAparFecha(Date aparFecha) {
        this.aparFecha = aparFecha;
    }

    public Libro getAparIsbn() {
        return aparIsbn;
    }

    public void setAparIsbn(Libro aparIsbn) {
        this.aparIsbn = aparIsbn;
    }

    public Usuario getAparUsuario() {
        return aparUsuario;
    }

    public void setAparUsuario(Usuario aparUsuario) {
        this.aparUsuario = aparUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aparApartado != null ? aparApartado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apartado)) {
            return false;
        }
        Apartado other = (Apartado) object;
        if ((this.aparApartado == null && other.aparApartado != null) || (this.aparApartado != null && !this.aparApartado.equals(other.aparApartado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mig.entidad.Apartado[ aparApartado=" + aparApartado + " ]";
    }
    
}
