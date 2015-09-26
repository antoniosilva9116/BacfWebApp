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
@Table(name = "turno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t"),
    @NamedQuery(name = "Turno.findByTurnoID", query = "SELECT t FROM Turno t WHERE t.turnoID = :turnoID"),
    @NamedQuery(name = "Turno.findByHoraInicio", query = "SELECT t FROM Turno t WHERE t.horaInicio = :horaInicio"),
    @NamedQuery(name = "Turno.findByHoraFim", query = "SELECT t FROM Turno t WHERE t.horaFim = :horaFim"),
    @NamedQuery(name = "Turno.findByDia", query = "SELECT t FROM Turno t WHERE t.dia = :dia")})
public class Turno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TurnoID")
    private Integer turnoID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HoraInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HoraFim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFim;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dia")
    @Temporal(TemporalType.DATE)
    private Date dia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turnoID")
    private List<EquipaTurno> equipaTurnoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turnoID")
    private List<VoluntarioTurno> voluntarioTurnoList;
    @JoinColumn(name = "CampanhaID", referencedColumnName = "CampanhaID")
    @ManyToOne(optional = false)
    private Campanha campanhaID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turnoID")
    private List<TurnoEstabelecimento> turnoEstabelecimentoList;

    public Turno() {
    }

    public Turno(Integer turnoID) {
        this.turnoID = turnoID;
    }

    public Turno(Integer turnoID, Date horaInicio, Date horaFim, Date dia) {
        this.turnoID = turnoID;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.dia = dia;
    }

    public Integer getTurnoID() {
        return turnoID;
    }

    public void setTurnoID(Integer turnoID) {
        this.turnoID = turnoID;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    @XmlTransient
    public List<EquipaTurno> getEquipaTurnoList() {
        return equipaTurnoList;
    }

    public void setEquipaTurnoList(List<EquipaTurno> equipaTurnoList) {
        this.equipaTurnoList = equipaTurnoList;
    }

    @XmlTransient
    public List<VoluntarioTurno> getVoluntarioTurnoList() {
        return voluntarioTurnoList;
    }

    public void setVoluntarioTurnoList(List<VoluntarioTurno> voluntarioTurnoList) {
        this.voluntarioTurnoList = voluntarioTurnoList;
    }

    public Campanha getCampanhaID() {
        return campanhaID;
    }

    public void setCampanhaID(Campanha campanhaID) {
        this.campanhaID = campanhaID;
    }

    @XmlTransient
    public List<TurnoEstabelecimento> getTurnoEstabelecimentoList() {
        return turnoEstabelecimentoList;
    }

    public void setTurnoEstabelecimentoList(List<TurnoEstabelecimento> turnoEstabelecimentoList) {
        this.turnoEstabelecimentoList = turnoEstabelecimentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (turnoID != null ? turnoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.turnoID == null && other.turnoID != null) || (this.turnoID != null && !this.turnoID.equals(other.turnoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Turno[ turnoID=" + turnoID + " ]";
    }
    
}
