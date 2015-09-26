/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistence.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Marcos Magalhaes
 */
@Entity
@Table(name = "doacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doacao.findAll", query = "SELECT d FROM Doacao d"),
    @NamedQuery(name = "Doacao.findByDoacaoID", query = "SELECT d FROM Doacao d WHERE d.doacaoID = :doacaoID"),
    @NamedQuery(name = "Doacao.findByDataDoacao", query = "SELECT d FROM Doacao d WHERE d.dataDoacao = :dataDoacao"),
    @NamedQuery(name = "Doacao.findByNomeDoador", query = "SELECT d FROM Doacao d WHERE d.nomeDoador = :nomeDoador"),
    @NamedQuery(name = "Doacao.findByBi", query = "SELECT d FROM Doacao d WHERE d.bi = :bi"),
    @NamedQuery(name = "Doacao.findByTelefone", query = "SELECT d FROM Doacao d WHERE d.telefone = :telefone"),
    @NamedQuery(name = "Doacao.findByDataRecolha", query = "SELECT d FROM Doacao d WHERE d.dataRecolha = :dataRecolha"),
    @NamedQuery(name = "Doacao.findByLocalRecolha", query = "SELECT d FROM Doacao d WHERE d.localRecolha = :localRecolha")})
public class Doacao implements Serializable {
    
    @Column(name = "Disponibilidade")
    private boolean disponibilidade; 
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DoacaoID")
    private Integer doacaoID;
    @Column(name = "DataDoacao")
    @Temporal(TemporalType.DATE)
    private Date dataDoacao;
    @Size(max = 255)
    @Column(name = "NomeDoador")
    private String nomeDoador;
    @Column(name = "BI")
    private Integer bi;
    @Column(name = "Telefone")
    private Integer telefone;
    @Column(name = "DataRecolha")
    @Temporal(TemporalType.DATE)
    private Date dataRecolha;
    @Size(max = 255)
    @Column(name = "LocalRecolha")
    private String localRecolha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doacaoDoacaoID")
    private Collection<Itemdoacao> itemdoacaoCollection;

    public Doacao() {
    }

    public Doacao(Integer doacaoID) {
        this.doacaoID = doacaoID;
    }

    public Integer getDoacaoID() {
        return doacaoID;
    }

    public void setDoacaoID(Integer doacaoID) {
        this.doacaoID = doacaoID;
    }

    public Date getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(Date dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public String getNomeDoador() {
        return nomeDoador;
    }

    public void setNomeDoador(String nomeDoador) {
        this.nomeDoador = nomeDoador;
    }

    public Integer getBi() {
        return bi;
    }

    public void setBi(Integer bi) {
        this.bi = bi;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Date getDataRecolha() {
        return dataRecolha;
    }

    public void setDataRecolha(Date dataRecolha) {
        this.dataRecolha = dataRecolha;
    }

    public String getLocalRecolha() {
        return localRecolha;
    }

    public void setLocalRecolha(String localRecolha) {
        this.localRecolha = localRecolha;
    }

    @XmlTransient
    public Collection<Itemdoacao> getItemdoacaoCollection() {
        return itemdoacaoCollection;
    }

    public void setItemdoacaoCollection(Collection<Itemdoacao> itemdoacaoCollection) {
        this.itemdoacaoCollection = itemdoacaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doacaoID != null ? doacaoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doacao)) {
            return false;
        }
        Doacao other = (Doacao) object;
        if ((this.doacaoID == null && other.doacaoID != null) || (this.doacaoID != null && !this.doacaoID.equals(other.doacaoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Doacao[ doacaoID=" + doacaoID + " ]";
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    
}
