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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "equipa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipa.findAll", query = "SELECT e FROM Equipa e"),
    @NamedQuery(name = "Equipa.findByEstabelecimentoID", query = "SELECT e FROM Equipa e WHERE e.estabelecimentoID = :estabelecimentoID"),
    @NamedQuery(name = "Equipa.findByEquipaID", query = "SELECT e FROM Equipa e WHERE e.equipaID = :equipaID")})
public class Equipa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EquipaID")
    private Integer equipaID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipaID")
    private List<EquipaTurno> equipaTurnoList;
    @JoinColumn(name = "EstabelecimentoID", referencedColumnName = "EstabelecimentoID")
    @ManyToOne(optional = false)
    private Estabelecimento estabelecimentoID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipaID")
    private List<VoluntarioEquipa> voluntarioEquipaList;

    public Equipa() {
    }

    public Equipa(Integer equipaID) {
        this.equipaID = equipaID;
    }

    public Integer getEquipaID() {
        return equipaID;
    }

    public void setEquipaID(Integer equipaID) {
        this.equipaID = equipaID;
    }

    @XmlTransient
    public List<EquipaTurno> getEquipaTurnoList() {
        return equipaTurnoList;
    }

    public void setEquipaTurnoList(List<EquipaTurno> equipaTurnoList) {
        this.equipaTurnoList = equipaTurnoList;
    }

    public Estabelecimento getEstabelecimentoID() {
        return estabelecimentoID;
    }

    public void setEstabelecimentoID(Estabelecimento estabelecimentoID) {
        this.estabelecimentoID = estabelecimentoID;
    }

    @XmlTransient
    public List<VoluntarioEquipa> getVoluntarioEquipaList() {
        return voluntarioEquipaList;
    }

    public void setVoluntarioEquipaList(List<VoluntarioEquipa> voluntarioEquipaList) {
        this.voluntarioEquipaList = voluntarioEquipaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipaID != null ? equipaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipa)) {
            return false;
        }
        Equipa other = (Equipa) object;
        if ((this.equipaID == null && other.equipaID != null) || (this.equipaID != null && !this.equipaID.equals(other.equipaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Equipa[ equipaID=" + equipaID + " ]";
    }

}
