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
@Table(name = "voluntarios_campanhas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoluntariosCampanhas.findAll", query = "SELECT v FROM VoluntariosCampanhas v"),
    @NamedQuery(name = "VoluntariosCampanhas.findByVoluntariosCampanhasID", query = "SELECT v FROM VoluntariosCampanhas v WHERE v.voluntariosCampanhasID = :voluntariosCampanhasID")})
public class VoluntariosCampanhas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Voluntarios_CampanhasID")
    private Integer voluntariosCampanhasID;
    @JoinColumn(name = "CampanhaID", referencedColumnName = "CampanhaID")
    @ManyToOne(optional = false)
    private Campanha campanhaID;
    @JoinColumn(name = "VoluntarioID", referencedColumnName = "VoluntarioID")
    @ManyToOne(optional = false)
    private Voluntario voluntarioID;

    public VoluntariosCampanhas() {
    }

    public VoluntariosCampanhas(Integer voluntariosCampanhasID) {
        this.voluntariosCampanhasID = voluntariosCampanhasID;
    }

    public Integer getVoluntariosCampanhasID() {
        return voluntariosCampanhasID;
    }

    public void setVoluntariosCampanhasID(Integer voluntariosCampanhasID) {
        this.voluntariosCampanhasID = voluntariosCampanhasID;
    }

    public Campanha getCampanhaID() {
        return campanhaID;
    }

    public void setCampanhaID(Campanha campanhaID) {
        this.campanhaID = campanhaID;
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
        hash += (voluntariosCampanhasID != null ? voluntariosCampanhasID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoluntariosCampanhas)) {
            return false;
        }
        VoluntariosCampanhas other = (VoluntariosCampanhas) object;
        if ((this.voluntariosCampanhasID == null && other.voluntariosCampanhasID != null) || (this.voluntariosCampanhasID != null && !this.voluntariosCampanhasID.equals(other.voluntariosCampanhasID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.VoluntariosCampanhas[ voluntariosCampanhasID=" + voluntariosCampanhasID + " ]";
    }
    
}
