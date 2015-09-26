/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence.Entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "modulogrupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modulogrupo.findAll", query = "SELECT m FROM Modulogrupo m"),
    @NamedQuery(name = "Modulogrupo.findByModuloGrupoID", query = "SELECT m FROM Modulogrupo m WHERE m.modulogrupoPK.moduloGrupoID = :moduloGrupoID"),
    @NamedQuery(name = "Modulogrupo.findByGrupoID", query = "SELECT m FROM Modulogrupo m WHERE m.modulogrupoPK.grupoID = :grupoID"),
    @NamedQuery(name = "Modulogrupo.findByModuloID", query = "SELECT m FROM Modulogrupo m WHERE m.modulogrupoPK.moduloID = :moduloID")})
public class Modulogrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ModulogrupoPK modulogrupoPK;
    @JoinColumn(name = "GrupoID", referencedColumnName = "GrupoID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Grupo grupo;
    @JoinColumn(name = "ModuloID", referencedColumnName = "ModuloID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Modulo modulo;

    public Modulogrupo() {
    }

    public Modulogrupo(ModulogrupoPK modulogrupoPK) {
        this.modulogrupoPK = modulogrupoPK;
    }

    public Modulogrupo(int moduloGrupoID, int grupoID, int moduloID) {
        this.modulogrupoPK = new ModulogrupoPK(moduloGrupoID, grupoID, moduloID);
    }

    public ModulogrupoPK getModulogrupoPK() {
        return modulogrupoPK;
    }

    public void setModulogrupoPK(ModulogrupoPK modulogrupoPK) {
        this.modulogrupoPK = modulogrupoPK;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modulogrupoPK != null ? modulogrupoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modulogrupo)) {
            return false;
        }
        Modulogrupo other = (Modulogrupo) object;
        if ((this.modulogrupoPK == null && other.modulogrupoPK != null) || (this.modulogrupoPK != null && !this.modulogrupoPK.equals(other.modulogrupoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Modulogrupo[ modulogrupoPK=" + modulogrupoPK + " ]";
    }
    
}
