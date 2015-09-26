/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence.Entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "historicocampanha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historicocampanha.findAll", query = "SELECT h FROM Historicocampanha h"),
    @NamedQuery(name = "Historicocampanha.findByHistoricoCampanhaID", query = "SELECT h FROM Historicocampanha h WHERE h.historicoCampanhaID = :historicoCampanhaID"),
    @NamedQuery(name = "Historicocampanha.findByDia", query = "SELECT h FROM Historicocampanha h WHERE h.dia = :dia"),
    @NamedQuery(name = "Historicocampanha.findByHoraInicio", query = "SELECT h FROM Historicocampanha h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "Historicocampanha.findByHoraFim", query = "SELECT h FROM Historicocampanha h WHERE h.horaFim = :horaFim")})
public class Historicocampanha implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HistoricoCampanhaID")
    private Integer historicoCampanhaID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dia")
    @Temporal(TemporalType.DATE)
    private Date dia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HoraInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HoraFim")
    @Temporal(TemporalType.TIME)
    private Date horaFim;
    @JoinColumn(name = "CampanhaID", referencedColumnName = "CampanhaID")
    @ManyToOne(optional = false)
    private Campanha campanhaID;

    public Historicocampanha() {
    }

    public Historicocampanha(Integer historicoCampanhaID) {
        this.historicoCampanhaID = historicoCampanhaID;
    }

    public Historicocampanha(Integer historicoCampanhaID, Date dia, Date horaInicio, Date horaFim) {
        this.historicoCampanhaID = historicoCampanhaID;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public Integer getHistoricoCampanhaID() {
        return historicoCampanhaID;
    }

    public void setHistoricoCampanhaID(Integer historicoCampanhaID) {
        this.historicoCampanhaID = historicoCampanhaID;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
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

    public Campanha getCampanhaID() {
        return campanhaID;
    }

    public void setCampanhaID(Campanha campanhaID) {
        this.campanhaID = campanhaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historicoCampanhaID != null ? historicoCampanhaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historicocampanha)) {
            return false;
        }
        Historicocampanha other = (Historicocampanha) object;
        if ((this.historicoCampanhaID == null && other.historicoCampanhaID != null) || (this.historicoCampanhaID != null && !this.historicoCampanhaID.equals(other.historicoCampanhaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Historicocampanha[ historicoCampanhaID=" + historicoCampanhaID + " ]";
    }
    
}
