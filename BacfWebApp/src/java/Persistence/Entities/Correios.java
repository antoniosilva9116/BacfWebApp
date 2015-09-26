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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "correios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Correios.findAll", query = "SELECT c FROM Correios c"),
    @NamedQuery(name = "Correios.findByCorreiosID", query = "SELECT c FROM Correios c WHERE c.correiosID = :correiosID"),
    @NamedQuery(name = "Correios.findByDistrito", query = "SELECT c FROM Correios c WHERE c.distrito = :distrito"),
    @NamedQuery(name = "Correios.findByConcelho", query = "SELECT c FROM Correios c WHERE c.concelho = :concelho"),
    @NamedQuery(name = "Correios.findByLocalidade", query = "SELECT c FROM Correios c WHERE c.localidade = :localidade"),
    @NamedQuery(name = "Correios.findByRua", query = "SELECT c FROM Correios c WHERE c.rua = :rua"),
    @NamedQuery(name = "Correios.findByLocal", query = "SELECT c FROM Correios c WHERE c.local = :local"),
    @NamedQuery(name = "Correios.findByCodPostal", query = "SELECT c FROM Correios c WHERE c.codPostal = :codPostal"),
    @NamedQuery(name = "Correios.findByNomePostal", query = "SELECT c FROM Correios c WHERE c.nomePostal = :nomePostal")})
public class Correios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CorreiosID")
    private Integer correiosID;
    @Size(max = 255)
    @Column(name = "Distrito")
    private String distrito;
    @Size(max = 255)
    @Column(name = "Concelho")
    private String concelho;
    @Size(max = 255)
    @Column(name = "Localidade")
    private String localidade;
    @Size(max = 255)
    @Column(name = "Rua")
    private String rua;
    @Size(max = 255)
    @Column(name = "Local")
    private String local;
    @Size(max = 255)
    @Column(name = "CodPostal")
    private String codPostal;
    @Size(max = 255)
    @Column(name = "NomePostal")
    private String nomePostal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "correiosID")
    private List<Estabelecimento> estabelecimentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "correiosID")
    private List<Voluntario> voluntarioList;

    public Correios() {
    }

    public Correios(Integer correiosID) {
        this.correiosID = correiosID;
    }

    public Integer getCorreiosID() {
        return correiosID;
    }

    public void setCorreiosID(Integer correiosID) {
        this.correiosID = correiosID;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getConcelho() {
        return concelho;
    }

    public void setConcelho(String concelho) {
        this.concelho = concelho;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getNomePostal() {
        return nomePostal;
    }

    public void setNomePostal(String nomePostal) {
        this.nomePostal = nomePostal;
    }

    @XmlTransient
    public List<Estabelecimento> getEstabelecimentoList() {
        return estabelecimentoList;
    }

    public void setEstabelecimentoList(List<Estabelecimento> estabelecimentoList) {
        this.estabelecimentoList = estabelecimentoList;
    }

    @XmlTransient
    public List<Voluntario> getVoluntarioList() {
        return voluntarioList;
    }

    public void setVoluntarioList(List<Voluntario> voluntarioList) {
        this.voluntarioList = voluntarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correiosID != null ? correiosID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correios)) {
            return false;
        }
        Correios other = (Correios) object;
        if ((this.correiosID == null && other.correiosID != null) || (this.correiosID != null && !this.correiosID.equals(other.correiosID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Correios[ correiosID=" + correiosID + 
                "codPostal=" + codPostal +
                "concelho=" +  concelho +
                "distrito=" + distrito +
                "local=" + local +
                "localidade=" + localidade +
                "nomePostal=" + nomePostal +
                "rua=" + rua +                                
                " ]";
    }
    
}
