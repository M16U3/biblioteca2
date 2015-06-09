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
@Table(name = "Auth", catalog = "libreria", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auth.findAll", query = "SELECT a FROM Auth a"),
    @NamedQuery(name = "Auth.findByAuthId", query = "SELECT a FROM Auth a WHERE a.authId = :authId"),
    @NamedQuery(name = "Auth.findByAuthFecha", query = "SELECT a FROM Auth a WHERE a.authFecha = :authFecha")})
public class Auth implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AUTH_ID")
    private String authId;
    @Basic(optional = false)
    @Column(name = "AUTH_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date authFecha;
    @JoinColumn(name = "AUTH_USUARIO", referencedColumnName = "USUA_USUARIO")
    @ManyToOne(optional = false)
    private Usuario authUsuario;

    public Auth() {
    }

    public Auth(String authId) {
        this.authId = authId;
    }

    public Auth(String authId, Date authFecha) {
        this.authId = authId;
        this.authFecha = authFecha;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public Date getAuthFecha() {
        return authFecha;
    }

    public void setAuthFecha(Date authFecha) {
        this.authFecha = authFecha;
    }

    public Usuario getAuthUsuario() {
        return authUsuario;
    }

    public void setAuthUsuario(Usuario authUsuario) {
        this.authUsuario = authUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authId != null ? authId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auth)) {
            return false;
        }
        Auth other = (Auth) object;
        if ((this.authId == null && other.authId != null) || (this.authId != null && !this.authId.equals(other.authId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mig.entidad.Auth[ authId=" + authId + " ]";
    }
    
}
