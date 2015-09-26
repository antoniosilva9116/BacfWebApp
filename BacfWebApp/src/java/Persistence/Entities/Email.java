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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "email")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e"),
    @NamedQuery(name = "Email.findByEmailID", query = "SELECT e FROM Email e WHERE e.emailID = :emailID"),
    @NamedQuery(name = "Email.findByDataEnvio", query = "SELECT e FROM Email e WHERE e.dataEnvio = :dataEnvio"),
    @NamedQuery(name = "Email.findByLead", query = "SELECT e FROM Email e WHERE e.lead = :lead"),
    @NamedQuery(name = "Email.findByCorpo", query = "SELECT e FROM Email e WHERE e.corpo = :corpo"),
    @NamedQuery(name = "Email.findByDestinatarioID", query = "SELECT e FROM Email e WHERE e.utilizadorDestinatarioID = :utilizadorDestinatarioID")})
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EmailID")
    private Integer emailID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataEnvio")
    @Temporal(TemporalType.DATE)
    private Date dataEnvio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Lead")
    private String lead;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Corpo")
    private String corpo;
    @JoinColumn(name = "UtilizadorRemetenteID", referencedColumnName = "UtilizadorID")
    @ManyToOne(optional = false)
    private Utilizador utilizadorRemetenteID;
    @JoinColumn(name = "UtilizadorDestinatarioID", referencedColumnName = "UtilizadorID")
    @ManyToOne(optional = false)
    private Utilizador utilizadorDestinatarioID;

    public Email() {
    }

    public Email(Integer emailID) {
        this.emailID = emailID;
    }

    public Email(Integer emailID, Date dataEnvio, String lead, String corpo) {
        this.emailID = emailID;
        this.dataEnvio = dataEnvio;
        this.lead = lead;
        this.corpo = corpo;
    }

    public Integer getEmailID() {
        return emailID;
    }

    public void setEmailID(Integer emailID) {
        this.emailID = emailID;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Utilizador getUtilizadorRemetenteID() {
        return utilizadorRemetenteID;
    }

    public void setUtilizadorRemetenteID(Utilizador utilizadorRemetenteID) {
        this.utilizadorRemetenteID = utilizadorRemetenteID;
    }

    public Utilizador getUtilizadorDestinatarioID() {
        return utilizadorDestinatarioID;
    }

    public void setUtilizadorDestinatarioID(Utilizador utilizadorDestinatarioID) {
        this.utilizadorDestinatarioID = utilizadorDestinatarioID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailID != null ? emailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.emailID == null && other.emailID != null) || (this.emailID != null && !this.emailID.equals(other.emailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Email[ emailID=" + emailID + " ]";
    }

}
