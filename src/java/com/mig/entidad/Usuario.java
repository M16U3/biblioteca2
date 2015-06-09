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
@Table(name = "Usuario", catalog = "libreria", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuaUsuario", query = "SELECT u FROM Usuario u WHERE u.usuaUsuario = :usuaUsuario"),
    @NamedQuery(name = "Usuario.findByUsuaPassword", query = "SELECT u FROM Usuario u WHERE u.usuaPassword = :usuaPassword"),
    @NamedQuery(name = "Usuario.findByUsuaNombre", query = "SELECT u FROM Usuario u WHERE u.usuaNombre = :usuaNombre"),
    @NamedQuery(name = "Usuario.findByUsuaEmail", query = "SELECT u FROM Usuario u WHERE u.usuaEmail = :usuaEmail")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USUA_USUARIO")
    private String usuaUsuario;
    @Basic(optional = false)
    @Column(name = "USUA_PASSWORD")
    private String usuaPassword;
    @Basic(optional = false)
    @Column(name = "USUA_NOMBRE")
    private String usuaNombre;
    @Basic(optional = false)
    @Column(name = "USUA_EMAIL")
    private String usuaEmail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aparUsuario")
    private List<Apartado> apartadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authUsuario")
    private List<Auth> authList;

    public Usuario() {
    }

    public Usuario(String usuaUsuario) {
        this.usuaUsuario = usuaUsuario;
    }

    public Usuario(String usuaUsuario, String usuaPassword, String usuaNombre, String usuaEmail) {
        this.usuaUsuario = usuaUsuario;
        this.usuaPassword = usuaPassword;
        this.usuaNombre = usuaNombre;
        this.usuaEmail = usuaEmail;
    }

    public String getUsuaUsuario() {
        return usuaUsuario;
    }

    public void setUsuaUsuario(String usuaUsuario) {
        this.usuaUsuario = usuaUsuario;
    }

    public String getUsuaPassword() {
        return usuaPassword;
    }

    public void setUsuaPassword(String usuaPassword) {
        this.usuaPassword = usuaPassword;
    }

    public String getUsuaNombre() {
        return usuaNombre;
    }

    public void setUsuaNombre(String usuaNombre) {
        this.usuaNombre = usuaNombre;
    }

    public String getUsuaEmail() {
        return usuaEmail;
    }

    public void setUsuaEmail(String usuaEmail) {
        this.usuaEmail = usuaEmail;
    }

    @XmlTransient
    public List<Apartado> getApartadoList() {
        return apartadoList;
    }

    public void setApartadoList(List<Apartado> apartadoList) {
        this.apartadoList = apartadoList;
    }

    @XmlTransient
    public List<Auth> getAuthList() {
        return authList;
    }

    public void setAuthList(List<Auth> authList) {
        this.authList = authList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuaUsuario != null ? usuaUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuaUsuario == null && other.usuaUsuario != null) || (this.usuaUsuario != null && !this.usuaUsuario.equals(other.usuaUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mig.entidad.Usuario[ usuaUsuario=" + usuaUsuario + " ]";
    }
    
}
