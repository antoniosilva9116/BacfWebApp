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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "voluntariojunior")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voluntariojunior.findAll", query = "SELECT v FROM Voluntariojunior v"),
    @NamedQuery(name = "Voluntariojunior.findByVoluntarioJuniorID", query = "SELECT v FROM Voluntariojunior v WHERE v.voluntarioJuniorID = :voluntarioJuniorID"),
    @NamedQuery(name = "Voluntariojunior.findByNomeJunior", query = "SELECT v FROM Voluntariojunior v WHERE v.nomeJunior = :nomeJunior"),
    @NamedQuery(name = "Voluntariojunior.findByEmailJunior", query = "SELECT v FROM Voluntariojunior v WHERE v.emailJunior = :emailJunior"),
    @NamedQuery(name = "Voluntariojunior.findByTelefoneJunior", query = "SELECT v FROM Voluntariojunior v WHERE v.telefoneJunior = :telefoneJunior")})
public class Voluntariojunior implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VoluntarioJuniorID")
    private Integer voluntarioJuniorID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NomeJunior")
    private String nomeJunior;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EmailJunior")
    private String emailJunior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TelefoneJunior")
    private int telefoneJunior;
    @JoinColumn(name = "VoluntarioID", referencedColumnName = "VoluntarioID")
    @ManyToOne(optional = false)
    private Voluntario voluntarioID;

    public Voluntariojunior() {
    }

    public Voluntariojunior(Integer voluntarioJuniorID) {
        this.voluntarioJuniorID = voluntarioJuniorID;
    }

    public Voluntariojunior(Integer voluntarioJuniorID, String nomeJunior, String emailJunior, int telefoneJunior) {
        this.voluntarioJuniorID = voluntarioJuniorID;
        this.nomeJunior = nomeJunior;
        this.emailJunior = emailJunior;
        this.telefoneJunior = telefoneJunior;
    }

    public Integer getVoluntarioJuniorID() {
        return voluntarioJuniorID;
    }

    public void setVoluntarioJuniorID(Integer voluntarioJuniorID) {
        this.voluntarioJuniorID = voluntarioJuniorID;
    }

    public String getNomeJunior() {
        return nomeJunior;
    }

    public void setNomeJunior(String nomeJunior) {
        this.nomeJunior = nomeJunior;
    }

    public String getEmailJunior() {
        return emailJunior;
    }

    public void setEmailJunior(String emailJunior) {
        this.emailJunior = emailJunior;
    }

    public int getTelefoneJunior() {
        return telefoneJunior;
    }

    public void setTelefoneJunior(int telefoneJunior) {
        this.telefoneJunior = telefoneJunior;
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
        hash += (voluntarioJuniorID != null ? voluntarioJuniorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voluntariojunior)) {
            return false;
        }
        Voluntariojunior other = (Voluntariojunior) object;
        if ((this.voluntarioJuniorID == null && other.voluntarioJuniorID != null) || (this.voluntarioJuniorID != null && !this.voluntarioJuniorID.equals(other.voluntarioJuniorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Voluntariojunior[ voluntarioJuniorID=" + voluntarioJuniorID + " ]";
    }
    
}
