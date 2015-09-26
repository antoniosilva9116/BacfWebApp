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
import javax.persistence.Lob;
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
@Table(name = "voluntario_turno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoluntarioTurno.findAll", query = "SELECT v FROM VoluntarioTurno v"),
    @NamedQuery(name = "VoluntarioTurno.findByVoluntarioID", query = "SELECT v FROM VoluntarioTurno v WHERE v.voluntarioID = :voluntarioID"),
    @NamedQuery(name = "VoluntarioTurno.findByTurnoID", query = "SELECT v FROM VoluntarioTurno v WHERE v.turnoID = :turnoID"),
    @NamedQuery(name = "VoluntarioTurno.findByTurnoVoluntarioID", query = "SELECT v FROM VoluntarioTurno v WHERE v.turnoVoluntarioID = :turnoVoluntarioID")})
public class VoluntarioTurno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TurnoVoluntarioID")
    private Integer turnoVoluntarioID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Aceite")
    private boolean aceite;
    @JoinColumn(name = "TurnoID", referencedColumnName = "TurnoID")
    @ManyToOne(optional = false)
    private Turno turnoID;
    @JoinColumn(name = "VoluntarioID", referencedColumnName = "VoluntarioID")
    @ManyToOne(optional = false)
    private Voluntario voluntarioID;

    public VoluntarioTurno() {
    }

    public VoluntarioTurno(Integer turnoVoluntarioID) {
        this.turnoVoluntarioID = turnoVoluntarioID;
    }

    public VoluntarioTurno(Integer turnoVoluntarioID, boolean aceite) {
        this.turnoVoluntarioID = turnoVoluntarioID;
        this.aceite = aceite;
    }

    public Integer getTurnoVoluntarioID() {
        return turnoVoluntarioID;
    }

    public void setTurnoVoluntarioID(Integer turnoVoluntarioID) {
        this.turnoVoluntarioID = turnoVoluntarioID;
    }

    public boolean getAceite() {
        return aceite;
    }

    public void setAceite(boolean aceite) {
        this.aceite = aceite;
    }

    public Turno getTurnoID() {
        return turnoID;
    }

    public void setTurnoID(Turno turnoID) {
        this.turnoID = turnoID;
    }

    public Voluntario getVoluntarioID() {
        return voluntarioID;
    }

    public void setVoluntarioID(Voluntario voluntarioID) {
        this.voluntarioID = voluntarioID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (turnoVoluntarioID != null ? turnoVoluntarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoluntarioTurno)) {
            return false;
        }
        VoluntarioTurno other = (VoluntarioTurno) object;
        if ((this.turnoVoluntarioID == null && other.turnoVoluntarioID != null) || (this.turnoVoluntarioID != null && !this.turnoVoluntarioID.equals(other.turnoVoluntarioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.VoluntarioTurno[ turnoVoluntarioID=" + turnoVoluntarioID + " ]";
    }

}
