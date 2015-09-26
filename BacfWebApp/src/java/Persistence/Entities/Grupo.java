/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByGrupoID", query = "SELECT g FROM Grupo g WHERE g.grupoID = :grupoID"),
    @NamedQuery(name = "Grupo.findByNomeGrupo", query = "SELECT g FROM Grupo g WHERE g.nomeGrupo = :nomeGrupo")})
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GrupoID")
    private Integer grupoID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NomeGrupo")
    private String nomeGrupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoID")
    private List<Utilizador> utilizadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo")
    private List<Modulogrupo> modulogrupoList;

    public Grupo() {
    }

    public Grupo(Integer grupoID) {
        this.grupoID = grupoID;
    }

    public Grupo(Integer grupoID, String nomeGrupo) {
        this.grupoID = grupoID;
        this.nomeGrupo = nomeGrupo;
    }

    public Integer getGrupoID() {
        return grupoID;
    }

    public void setGrupoID(Integer grupoID) {
        this.grupoID = grupoID;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    @XmlTransient
    public List<Utilizador> getUtilizadorList() {
        return utilizadorList;
    }

    public void setUtilizadorList(List<Utilizador> utilizadorList) {
        this.utilizadorList = utilizadorList;
    }

    @XmlTransient
    public List<Modulogrupo> getModulogrupoList() {
        return modulogrupoList;
    }

    public void setModulogrupoList(List<Modulogrupo> modulogrupoList) {
        this.modulogrupoList = modulogrupoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupoID != null ? grupoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.grupoID == null && other.grupoID != null) || (this.grupoID != null && !this.grupoID.equals(other.grupoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Grupo[ grupoID=" + grupoID + " ]";
    }
    
}
