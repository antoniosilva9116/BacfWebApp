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
@Table(name = "material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m"),
    @NamedQuery(name = "Material.findByMaterialID", query = "SELECT m FROM Material m WHERE m.materialID = :materialID"),
    @NamedQuery(name = "Material.findByDescricao", query = "SELECT m FROM Material m WHERE m.descricao = :descricao")})
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaterialID")
    private Integer materialID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private List<VoluntarioMaterial> voluntarioMaterialList;

    public Material() {
    }

    public Material(Integer materialID) {
        this.materialID = materialID;
    }

    public Material(Integer materialID, String descricao) {
        this.materialID = materialID;
        this.descricao = descricao;
    }

    public Integer getMaterialID() {
        return materialID;
    }

    public void setMaterialID(Integer materialID) {
        this.materialID = materialID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<VoluntarioMaterial> getVoluntarioMaterialList() {
        return voluntarioMaterialList;
    }

    public void setVoluntarioMaterialList(List<VoluntarioMaterial> voluntarioMaterialList) {
        this.voluntarioMaterialList = voluntarioMaterialList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialID != null ? materialID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.materialID == null && other.materialID != null) || (this.materialID != null && !this.materialID.equals(other.materialID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Material[ materialID=" + materialID + " ]";
    }
    
}
