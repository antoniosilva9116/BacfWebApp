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
@Table(name = "equipa_turno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipaTurno.findAll", query = "SELECT e FROM EquipaTurno e"),
    @NamedQuery(name = "EquipaTurno.findByEquipaTurnoID", query = "SELECT e FROM EquipaTurno e WHERE e.equipaTurnoID = :equipaTurnoID")})
public class EquipaTurno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Equipa_TurnoID")
    private Integer equipaTurnoID;
    @JoinColumn(name = "TurnoID", referencedColumnName = "TurnoID")
    @ManyToOne(optional = false)
    private Turno turnoID;
    @JoinColumn(name = "EquipaID", referencedColumnName = "EquipaID")
    @ManyToOne(optional = false)
    private Equipa equipaID;

    public EquipaTurno() {
    }

    public EquipaTurno(Integer equipaTurnoID) {
        this.equipaTurnoID = equipaTurnoID;
    }

    public Integer getEquipaTurnoID() {
        return equipaTurnoID;
    }

    public void setEquipaTurnoID(Integer equipaTurnoID) {
        this.equipaTurnoID = equipaTurnoID;
    }

    public Turno getTurnoID() {
        return turnoID;
    }

    public void setTurnoID(Turno turnoID) {
        this.turnoID = turnoID;
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
        hash += (equipaTurnoID != null ? equipaTurnoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipaTurno)) {
            return false;
        }
        EquipaTurno other = (EquipaTurno) object;
        if ((this.equipaTurnoID == null && other.equipaTurnoID != null) || (this.equipaTurnoID != null && !this.equipaTurnoID.equals(other.equipaTurnoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.EquipaTurno[ equipaTurnoID=" + equipaTurnoID + " ]";
    }
    
}
