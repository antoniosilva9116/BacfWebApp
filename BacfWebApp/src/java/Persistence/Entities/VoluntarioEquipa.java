/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "voluntario_equipa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoluntarioEquipa.findAll", query = "SELECT v FROM VoluntarioEquipa v"),
    @NamedQuery(name = "VoluntarioEquipa.findByVoluntarioEquipaID", query = "SELECT v FROM VoluntarioEquipa v WHERE v.voluntarioEquipaID = :voluntarioEquipaID")})
public class VoluntarioEquipa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Voluntario_EquipaID")
    private Integer voluntarioEquipaID;
    @JoinColumn(name = "VoluntarioID", referencedColumnName = "VoluntarioID")
    @ManyToOne(optional = false)
    private Voluntario voluntarioID;
    @JoinColumn(name = "EquipaID", referencedColumnName = "EquipaID")
    @ManyToOne(optional = false)
    private Equipa equipaID;

    public VoluntarioEquipa() {
    }

    public VoluntarioEquipa(Integer voluntarioEquipaID) {
        this.voluntarioEquipaID = voluntarioEquipaID;
    }

    public Integer getVoluntarioEquipaID() {
        return voluntarioEquipaID;
    }

    public void setVoluntarioEquipaID(Integer voluntarioEquipaID) {
        this.voluntarioEquipaID = voluntarioEquipaID;
    }

    public Voluntario getVoluntarioID() {
        return voluntarioID;
    }

    public void setVoluntarioID(Voluntario voluntarioID) {
        this.voluntarioID = voluntarioID;
    }

    public Equipa getEquipaID() {
        return equipaID;
    }

    public void setEquipaID(Equipa equipaID) {
        this.equipaID = equipaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voluntarioEquipaID != null ? voluntarioEquipaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoluntarioEquipa)) {
            return false;
        }
        VoluntarioEquipa other = (VoluntarioEquipa) object;
        if ((this.voluntarioEquipaID == null && other.voluntarioEquipaID != null) || (this.voluntarioEquipaID != null && !this.voluntarioEquipaID.equals(other.voluntarioEquipaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.VoluntarioEquipa[ voluntarioEquipaID=" + voluntarioEquipaID + " ]";
    }
    
}
