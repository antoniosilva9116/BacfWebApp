/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "voluntario_material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoluntarioMaterial.findAll", query = "SELECT v FROM VoluntarioMaterial v"),
    @NamedQuery(name = "VoluntarioMaterial.findByMaterialIRelogioPontoID", query = "SELECT v FROM VoluntarioMaterial v WHERE v.voluntarioMaterialPK.materialID = :materialID AND v.voluntarioMaterialPK.relogioPontoID = :relogioPontoID"),
    @NamedQuery(name = "VoluntarioMaterial.findByVoluntarioMaterialID", query = "SELECT v FROM VoluntarioMaterial v WHERE v.voluntarioMaterialPK.voluntarioMaterialID = :voluntarioMaterialID"),
    @NamedQuery(name = "VoluntarioMaterial.findByRelogioPontoID", query = "SELECT v FROM VoluntarioMaterial v WHERE v.voluntarioMaterialPK.relogioPontoID = :relogioPontoID"),
    @NamedQuery(name = "VoluntarioMaterial.findByMaterialID", query = "SELECT v FROM VoluntarioMaterial v WHERE v.voluntarioMaterialPK.materialID = :materialID")})
public class VoluntarioMaterial implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "entregue")
    private boolean entregue;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VoluntarioMaterialPK voluntarioMaterialPK;
    @JoinColumn(name = "MaterialID", referencedColumnName = "MaterialID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Material material;
    @JoinColumn(name = "RelogioPontoID", referencedColumnName = "RelogioPontoID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Relogioponto relogioponto;

    public VoluntarioMaterial() {
    }

    public VoluntarioMaterial(VoluntarioMaterialPK voluntarioMaterialPK) {
        this.voluntarioMaterialPK = voluntarioMaterialPK;
    }

    public VoluntarioMaterial(int voluntarioMaterialID, int relogioPontoID, int materialID) {
        this.voluntarioMaterialPK = new VoluntarioMaterialPK(voluntarioMaterialID, relogioPontoID, materialID);
    }

    public VoluntarioMaterialPK getVoluntarioMaterialPK() {
        return voluntarioMaterialPK;
    }

    public void setVoluntarioMaterialPK(VoluntarioMaterialPK voluntarioMaterialPK) {
        this.voluntarioMaterialPK = voluntarioMaterialPK;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Relogioponto getRelogioponto() {
        return relogioponto;
    }

    public void setRelogioponto(Relogioponto relogioponto) {
        this.relogioponto = relogioponto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voluntarioMaterialPK != null ? voluntarioMaterialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoluntarioMaterial)) {
            return false;
        }
        VoluntarioMaterial other = (VoluntarioMaterial) object;
        if ((this.voluntarioMaterialPK == null && other.voluntarioMaterialPK != null) || (this.voluntarioMaterialPK != null && !this.voluntarioMaterialPK.equals(other.voluntarioMaterialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.VoluntarioMaterial[ voluntarioMaterialPK=" + voluntarioMaterialPK + " ]";
    }

    public boolean getEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

}
