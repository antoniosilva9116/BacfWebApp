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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcos Magalhaes
 */
@Entity
@Table(name = "itemdoacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemdoacao.findAll", query = "SELECT i FROM Itemdoacao i"),
    @NamedQuery(name = "Itemdoacao.findByItemDoacao", query = "SELECT i FROM Itemdoacao i WHERE i.itemDoacao = :itemDoacao"),
    @NamedQuery(name = "Itemdoacao.findByDescricao", query = "SELECT i FROM Itemdoacao i WHERE i.descricao = :descricao"),
    @NamedQuery(name = "Itemdoacao.findByQuantidade", query = "SELECT i FROM Itemdoacao i WHERE i.quantidade = :quantidade"),
    @NamedQuery(name = "Itemdoacao.findByTipoUnidade", query = "SELECT i FROM Itemdoacao i WHERE i.tipoUnidade = :tipoUnidade")})
public class Itemdoacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ItemDoacao")
    private Integer itemDoacao;
    @Size(max = 255)
    @Column(name = "Descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Quantidade")
    private Double quantidade;
    @Size(max = 255)
    @Column(name = "TipoUnidade")
    private String tipoUnidade;
    @JoinColumn(name = "DoacaoDoacaoID", referencedColumnName = "DoacaoID")
    @ManyToOne(optional = false)
    private Doacao doacaoDoacaoID;

    public Itemdoacao() {
    }

    public Itemdoacao(Integer itemDoacao) {
        this.itemDoacao = itemDoacao;
    }

    public Integer getItemDoacao() {
        return itemDoacao;
    }

    public void setItemDoacao(Integer itemDoacao) {
        this.itemDoacao = itemDoacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(String tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public Doacao getDoacaoDoacaoID() {
        return doacaoDoacaoID;
    }

    public void setDoacaoDoacaoID(Doacao doacaoDoacaoID) {
        this.doacaoDoacaoID = doacaoDoacaoID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemDoacao != null ? itemDoacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemdoacao)) {
            return false;
        }
        Itemdoacao other = (Itemdoacao) object;
        if ((this.itemDoacao == null && other.itemDoacao != null) || (this.itemDoacao != null && !this.itemDoacao.equals(other.itemDoacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Itemdoacao[ itemDoacao=" + itemDoacao + " ]";
    }
    
}
