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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "estabelecimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estabelecimento.findAll", query = "SELECT e FROM Estabelecimento e"),
    @NamedQuery(name = "Estabelecimento.findByEstabelecimentoID", query = "SELECT e FROM Estabelecimento e WHERE e.estabelecimentoID = :estabelecimentoID"),
    @NamedQuery(name = "Estabelecimento.findByCodigo", query = "SELECT e FROM Estabelecimento e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Estabelecimento.findByNome", query = "SELECT e FROM Estabelecimento e WHERE e.nome = :nome"),
    @NamedQuery(name = "Estabelecimento.findByCadeia", query = "SELECT e FROM Estabelecimento e WHERE e.cadeia = :cadeia"),
    @NamedQuery(name = "Estabelecimento.findByGerente", query = "SELECT e FROM Estabelecimento e WHERE e.gerente = :gerente"),
    @NamedQuery(name = "Estabelecimento.findByTelefone", query = "SELECT e FROM Estabelecimento e WHERE e.telefone = :telefone"),
    @NamedQuery(name = "Estabelecimento.findByFax", query = "SELECT e FROM Estabelecimento e WHERE e.fax = :fax"),
    @NamedQuery(name = "Estabelecimento.findByTelemovel", query = "SELECT e FROM Estabelecimento e WHERE e.telemovel = :telemovel"),
    @NamedQuery(name = "Estabelecimento.findByTipo", query = "SELECT e FROM Estabelecimento e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "Estabelecimento.findByArea", query = "SELECT e FROM Estabelecimento e WHERE e.area = :area"),
    @NamedQuery(name = "Estabelecimento.findByHrams", query = "SELECT e FROM Estabelecimento e WHERE e.hrams = :hrams"),
    @NamedQuery(name = "Estabelecimento.findByHrfms", query = "SELECT e FROM Estabelecimento e WHERE e.hrfms = :hrfms"),
    @NamedQuery(name = "Estabelecimento.findByHrfts", query = "SELECT e FROM Estabelecimento e WHERE e.hrfts = :hrfts"),
    @NamedQuery(name = "Estabelecimento.findByHramd", query = "SELECT e FROM Estabelecimento e WHERE e.hramd = :hramd"),
    @NamedQuery(name = "Estabelecimento.findByHrfmd", query = "SELECT e FROM Estabelecimento e WHERE e.hrfmd = :hrfmd"),
    @NamedQuery(name = "Estabelecimento.findByHratd", query = "SELECT e FROM Estabelecimento e WHERE e.hratd = :hratd"),
    @NamedQuery(name = "Estabelecimento.findByHrftd", query = "SELECT e FROM Estabelecimento e WHERE e.hrftd = :hrftd")})
public class Estabelecimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EstabelecimentoID")
    private Integer estabelecimentoID;
    @Column(name = "Codigo")
    private Integer codigo;
    @Size(max = 100)
    @Column(name = "Nome")
    private String nome;
    @Size(max = 100)
    @Column(name = "Cadeia")
    private String cadeia;
    @Size(max = 100)
    @Column(name = "Gerente")
    private String gerente;
    @Size(max = 100)
    @Column(name = "Telefone")
    private String telefone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "Fax")
    private String fax;
    @Size(max = 100)
    @Column(name = "Telemovel")
    private String telemovel;
    @Size(max = 100)
    @Column(name = "Tipo")
    private String tipo;
    @Size(max = 100)
    @Column(name = "Area")
    private String area;
    @Size(max = 10)
    @Column(name = "HRAMS")
    private String hrams;
    @Size(max = 10)
    @Column(name = "HRFMS")
    private String hrfms;
    @Size(max = 10)
    @Column(name = "HRFTS")
    private String hrfts;
    @Size(max = 10)
    @Column(name = "HRAMD")
    private String hramd;
    @Size(max = 10)
    @Column(name = "HRFMD")
    private String hrfmd;
    @Size(max = 10)
    @Column(name = "HRATD")
    private String hratd;
    @Size(max = 10)
    @Column(name = "HRFTD")
    private String hrftd;
    @Lob
    @Column(name = "TipoDoEstabelecimento")
    private byte[] tipoDoEstabelecimento;
    @JoinColumn(name = "CorreiosID", referencedColumnName = "CorreiosID")
    @ManyToOne(optional = false)
    private Correios correiosID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estabelecimentoID")
    private List<TurnoEstabelecimento> turnoEstabelecimentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estabelecimentoID")
    private List<Equipa> equipaList;

    public Estabelecimento() {
    }

    public Estabelecimento(Integer estabelecimentoID) {
        this.estabelecimentoID = estabelecimentoID;
    }

    public Integer getEstabelecimentoID() {
        return estabelecimentoID;
    }

    public void setEstabelecimentoID(Integer estabelecimentoID) {
        this.estabelecimentoID = estabelecimentoID;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCadeia() {
        return cadeia;
    }

    public void setCadeia(String cadeia) {
        this.cadeia = cadeia;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHrams() {
        return hrams;
    }

    public void setHrams(String hrams) {
        this.hrams = hrams;
    }

    public String getHrfms() {
        return hrfms;
    }

    public void setHrfms(String hrfms) {
        this.hrfms = hrfms;
    }

    public String getHrfts() {
        return hrfts;
    }

    public void setHrfts(String hrfts) {
        this.hrfts = hrfts;
    }

    public String getHramd() {
        return hramd;
    }

    public void setHramd(String hramd) {
        this.hramd = hramd;
    }

    public String getHrfmd() {
        return hrfmd;
    }

    public void setHrfmd(String hrfmd) {
        this.hrfmd = hrfmd;
    }

    public String getHratd() {
        return hratd;
    }

    public void setHratd(String hratd) {
        this.hratd = hratd;
    }

    public String getHrftd() {
        return hrftd;
    }

    public void setHrftd(String hrftd) {
        this.hrftd = hrftd;
    }

    public byte[] getTipoDoEstabelecimento() {
        return tipoDoEstabelecimento;
    }

    public void setTipoDoEstabelecimento(byte[] tipoDoEstabelecimento) {
        this.tipoDoEstabelecimento = tipoDoEstabelecimento;
    }

    public Correios getCorreiosID() {
        return correiosID;
    }

    public void setCorreiosID(Correios correiosID) {
        this.correiosID = correiosID;
    }

    @XmlTransient
    public List<TurnoEstabelecimento> getTurnoEstabelecimentoList() {
        return turnoEstabelecimentoList;
    }

    public void setTurnoEstabelecimentoList(List<TurnoEstabelecimento> turnoEstabelecimentoList) {
        this.turnoEstabelecimentoList = turnoEstabelecimentoList;
    }

    @XmlTransient
    public List<Equipa> getEquipaList() {
        return equipaList;
    }

    public void setEquipaList(List<Equipa> equipaList) {
        this.equipaList = equipaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estabelecimentoID != null ? estabelecimentoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estabelecimento)) {
            return false;
        }
        Estabelecimento other = (Estabelecimento) object;
        if ((this.estabelecimentoID == null && other.estabelecimentoID != null) || (this.estabelecimentoID != null && !this.estabelecimentoID.equals(other.estabelecimentoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return estabelecimentoID + "-" + nome + " - " + cadeia + " - " + correiosID.getRua();
    }

}
