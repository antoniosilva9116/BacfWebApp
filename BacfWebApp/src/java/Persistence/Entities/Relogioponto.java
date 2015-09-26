/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "relogioponto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relogioponto.findAll", query = "SELECT r FROM Relogioponto r"),
    @NamedQuery(name = "Relogioponto.findByRelogioPontoID", query = "SELECT r FROM Relogioponto r WHERE r.relogioPontoID = :relogioPontoID"),
    @NamedQuery(name = "Relogioponto.findByDataRegisto", query = "SELECT r FROM Relogioponto r WHERE r.dataRegisto = :dataRegisto"),
    @NamedQuery(name = "Relogioponto.findByVoluntarioIDDataRegisto", query = "SELECT r FROM Relogioponto r WHERE r.dataRegisto = CURRENT_DATE AND r.voluntarioID = :voluntarioID"),
    @NamedQuery(name = "Relogioponto.findByVoluntarioID", query = "SELECT r FROM Relogioponto r WHERE r.voluntarioID = :voluntarioID"),
    @NamedQuery(name = "Relogioponto.findVoluntariosByDateNow", query = "SELECT r FROM Relogioponto r WHERE r.dataRegisto = CURRENT_DATE"),
    @NamedQuery(name = "Relogioponto.findByTipoRegisto", query = "SELECT r FROM Relogioponto r WHERE r.tipoRegisto = :tipoRegisto")})
public class Relogioponto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RelogioPontoID")
    private Integer relogioPontoID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataRegisto")
    @Temporal(TemporalType.DATE)
    private Date dataRegisto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TipoRegisto")
    private boolean tipoRegisto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relogioponto")
    private List<VoluntarioMaterial> voluntarioMaterialList;
    @JoinColumn(name = "VoluntarioID", referencedColumnName = "VoluntarioID")
    @ManyToOne(optional = false)
    private Voluntario voluntarioID;
    @JoinColumn(name = "CampanhaID", referencedColumnName = "CampanhaID")
    @ManyToOne(optional = false)
    private Campanha campanhaID;

    public Relogioponto() {
    }

    public Relogioponto(Integer relogioPontoID) {
        this.relogioPontoID = relogioPontoID;
    }

    public Relogioponto(Integer relogioPontoID, Date dataRegisto, boolean tipoRegisto) {
        this.relogioPontoID = relogioPontoID;
        this.dataRegisto = dataRegisto;
        this.tipoRegisto = tipoRegisto;
    }

    public Integer getRelogioPontoID() {
        return relogioPontoID;
    }

    public void setRelogioPontoID(Integer relogioPontoID) {
        this.relogioPontoID = relogioPontoID;
    }

    public Date getDataRegisto() {
        return dataRegisto;
    }

    public void setDataRegisto(Date dataRegisto) {
        this.dataRegisto = dataRegisto;
    }

    public boolean getTipoRegisto() {
        return tipoRegisto;
    }

    public void setTipoRegisto(boolean tipoRegisto) {
        this.tipoRegisto = tipoRegisto;
    }

    @XmlTransient
    public List<VoluntarioMaterial> getVoluntarioMaterialList() {
        return voluntarioMaterialList;
    }

    public void setVoluntarioMaterialList(List<VoluntarioMaterial> voluntarioMaterialList) {
        this.voluntarioMaterialList = voluntarioMaterialList;
    }

    public Voluntario getVoluntarioID() {
        return voluntarioID;
    }

    public void setVoluntarioID(Voluntario voluntarioID) {
        this.voluntarioID = voluntarioID;
    }

    public Campanha getCampanhaID() {
        return campanhaID;
    }

    public void setCampanhaID(Campanha campanhaID) {
        this.campanhaID = campanhaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relogioPontoID != null ? relogioPontoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relogioponto)) {
            return false;
        }
        Relogioponto other = (Relogioponto) object;
        if ((this.relogioPontoID == null && other.relogioPontoID != null) || (this.relogioPontoID != null && !this.relogioPontoID.equals(other.relogioPontoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Relogioponto[ relogioPontoID=" + relogioPontoID + " ]";
    }
    
}
