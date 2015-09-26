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
public class VoluntarioMaterialPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Voluntario_MaterialID")
    private int voluntarioMaterialID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RelogioPontoID")
    private int relogioPontoID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MaterialID")
    private int materialID;

    public VoluntarioMaterialPK() {
    }

    public VoluntarioMaterialPK(int voluntarioMaterialID, int relogioPontoID, int materialID) {
        this.voluntarioMaterialID = voluntarioMaterialID;
        this.relogioPontoID = relogioPontoID;
        this.materialID = materialID;
    }

    public int getVoluntarioMaterialID() {
        return voluntarioMaterialID;
    }

    public void setVoluntarioMaterialID(int voluntarioMaterialID) {
        this.voluntarioMaterialID = voluntarioMaterialID;
    }

    public int getRelogioPontoID() {
        return relogioPontoID;
    }

    public void setRelogioPontoID(int relogioPontoID) {
        this.relogioPontoID = relogioPontoID;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) voluntarioMaterialID;
        hash += (int) relogioPontoID;
        hash += (int) materialID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoluntarioMaterialPK)) {
            return false;
        }
        VoluntarioMaterialPK other = (VoluntarioMaterialPK) object;
        if (this.voluntarioMaterialID != other.voluntarioMaterialID) {
            return false;
        }
        if (this.relogioPontoID != other.relogioPontoID) {
            return false;
        }
        if (this.materialID != other.materialID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.VoluntarioMaterialPK[ voluntarioMaterialID=" + voluntarioMaterialID + ", relogioPontoID=" + relogioPontoID + ", materialID=" + materialID + " ]";
    }
    
}
