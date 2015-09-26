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
@Table(name = "noticia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticia.findAll", query = "SELECT n FROM Noticia n"),
    @NamedQuery(name = "Noticia.findByNoticiaID", query = "SELECT n FROM Noticia n WHERE n.noticiaID = :noticiaID"),
    @NamedQuery(name = "Noticia.findByLead", query = "SELECT n FROM Noticia n WHERE n.lead = :lead"),
    @NamedQuery(name = "Noticia.findByTitulo", query = "SELECT n FROM Noticia n WHERE n.titulo = :titulo"),
    @NamedQuery(name = "Noticia.findByCorpo", query = "SELECT n FROM Noticia n WHERE n.corpo = :corpo")})
public class Noticia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NoticiaID")
    private Integer noticiaID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Lead")
    private String lead;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Corpo")
    private String corpo;
    @JoinColumn(name = "UtilizadorID", referencedColumnName = "UtilizadorID")
    @ManyToOne(optional = false)
    private Utilizador utilizadorID;

    public Noticia() {
    }

    public Noticia(Integer noticiaID) {
        this.noticiaID = noticiaID;
    }

    public Noticia(Integer noticiaID, String lead, String titulo, String corpo) {
        this.noticiaID = noticiaID;
        this.lead = lead;
        this.titulo = titulo;
        this.corpo = corpo;
    }

    public Integer getNoticiaID() {
        return noticiaID;
    }

    public void setNoticiaID(Integer noticiaID) {
        this.noticiaID = noticiaID;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Utilizador getUtilizadorID() {
        return utilizadorID;
    }

    public void setUtilizadorID(Utilizador utilizadorID) {
        this.utilizadorID = utilizadorID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noticiaID != null ? noticiaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.noticiaID == null && other.noticiaID != null) || (this.noticiaID != null && !this.noticiaID.equals(other.noticiaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Noticia[ noticiaID=" + noticiaID + " ]";
    }
    
}
