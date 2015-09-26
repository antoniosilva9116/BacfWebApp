/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author António Silva
 */
@Embeddable
public class ModulogrupoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ModuloGrupoID")
    private int moduloGrupoID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GrupoID")
    private int grupoID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ModuloID")
    private int moduloID;

    public ModulogrupoPK() {
    }

    public ModulogrupoPK(int moduloGrupoID, int grupoID, int moduloID) {
        this.moduloGrupoID = moduloGrupoID;
        this.grupoID = grupoID;
        this.moduloID = moduloID;
    }

    public int getModuloGrupoID() {
        return moduloGrupoID;
    }

    public void setModuloGrupoID(int moduloGrupoID) {
        this.moduloGrupoID = moduloGrupoID;
    }

    public int getGrupoID() {
        return grupoID;
    }

    public void setGrupoID(int grupoID) {
        this.grupoID = grupoID;
    }

    public int getModuloID() {
        return moduloID;
    }

    public void setModuloID(int moduloID) {
        this.moduloID = moduloID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) moduloGrupoID;
        hash += (int) grupoID;
        hash += (int) moduloID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModulogrupoPK)) {
            return false;
        }
        ModulogrupoPK other = (ModulogrupoPK) object;
        if (this.moduloGrupoID != other.moduloGrupoID) {
            return false;
        }
        if (this.grupoID != other.grupoID) {
            return false;
        }
        if (this.moduloID != other.moduloID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.ModulogrupoPK[ moduloGrupoID=" + moduloGrupoID + ", grupoID=" + grupoID + ", moduloID=" + moduloID + " ]";
    }
    
}
