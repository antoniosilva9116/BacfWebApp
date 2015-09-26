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
@Table(name = "turno_estabelecimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TurnoEstabelecimento.findAll", query = "SELECT t FROM TurnoEstabelecimento t"),
    @NamedQuery(name = "TurnoEstabelecimento.findByEstabelecimentoID", query = "SELECT t FROM TurnoEstabelecimento t WHERE t.estabelecimentoID = :estabelecimentoID"),
    @NamedQuery(name = "TurnoEstabelecimento.findByTurnoID", query = "SELECT t FROM TurnoEstabelecimento t WHERE t.turnoID = :turnoID"),
    @NamedQuery(name = "TurnoEstabelecimento.findByTurnoEstabelecimentoID", query = "SELECT t FROM TurnoEstabelecimento t WHERE t.turnoEstabelecimentoID = :turnoEstabelecimentoID")})
public class TurnoEstabelecimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Turno_EstabelecimentoID")
    private Integer turnoEstabelecimentoID;
    @JoinColumn(name = "EstabelecimentoID", referencedColumnName = "EstabelecimentoID")
    @ManyToOne(optional = false)
    private Estabelecimento estabelecimentoID;
    @JoinColumn(name = "TurnoID", referencedColumnName = "TurnoID")
    @ManyToOne(optional = false)
    private Turno turnoID;

    public TurnoEstabelecimento() {
    }

    public TurnoEstabelecimento(Integer turnoEstabelecimentoID) {
        this.turnoEstabelecimentoID = turnoEstabelecimentoID;
    }

    public Integer getTurnoEstabelecimentoID() {
        return turnoEstabelecimentoID;
    }

    public void setTurnoEstabelecimentoID(Integer turnoEstabelecimentoID) {
        this.turnoEstabelecimentoID = turnoEstabelecimentoID;
    }

    public Estabelecimento getEstabelecimentoID() {
        return estabelecimentoID;
    }

    public void setEstabelecimentoID(Estabelecimento estabelecimentoID) {
        this.estabelecimentoID = estabelecimentoID;
    }

    public Turno getTurnoID() {
        return turnoID;
    }

    public void setTurnoID(Turno turnoID) {
        this.turnoID = turnoID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (turnoEstabelecimentoID != null ? turnoEstabelecimentoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurnoEstabelecimento)) {
            return false;
        }
        TurnoEstabelecimento other = (TurnoEstabelecimento) object;
        if ((this.turnoEstabelecimentoID == null && other.turnoEstabelecimentoID != null) || (this.turnoEstabelecimentoID != null && !this.turnoEstabelecimentoID.equals(other.turnoEstabelecimentoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.TurnoEstabelecimento[ turnoEstabelecimentoID=" + turnoEstabelecimentoID + " ]";
    }

}
