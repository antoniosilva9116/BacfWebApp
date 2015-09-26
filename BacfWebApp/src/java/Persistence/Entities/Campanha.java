/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "campanha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campanha.findAll", query = "SELECT c FROM Campanha c"),
    @NamedQuery(name = "Campanha.findByCampanhaID", query = "SELECT c FROM Campanha c WHERE c.campanhaID = :campanhaID"),
    @NamedQuery(name = "Campanha.findByDescricao", query = "SELECT c FROM Campanha c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Campanha.findByTipoDeCampanha", query = "SELECT c FROM Campanha c WHERE c.tipoDeCampanha = :tipoDeCampanha"),
    @NamedQuery(name = "Campanha.findByDataInicio", query = "SELECT c FROM Campanha c WHERE c.dataInicio = :dataInicio"),
    @NamedQuery(name = "Campanha.findByDateNow", query = "SELECT c FROM Campanha c WHERE CURRENT_DATE >= c.dataInicio AND CURRENT_DATE <= c.dataFim"),
    @NamedQuery(name = "Campanha.findByDataFim", query = "SELECT c FROM Campanha c WHERE c.dataFim = :dataFim")})
public class Campanha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CampanhaID")
    private Integer campanhaID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TipoDeCampanha")
    private int tipoDeCampanha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataInicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataFim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campanhaID")
    private List<Turno> turnoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campanhaID")
    private List<Historicocampanha> historicocampanhaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campanhaID")
    private List<VoluntariosCampanhas> voluntariosCampanhasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campanhaID")
    private List<Relogioponto> relogiopontoList;

    public Campanha() {
    }

    public Campanha(Integer campanhaID) {
        this.campanhaID = campanhaID;
    }

    public Campanha(Integer campanhaID, String descricao, int tipoDeCampanha, Date dataInicio, Date dataFim) {
        this.campanhaID = campanhaID;
        this.descricao = descricao;
        this.tipoDeCampanha = tipoDeCampanha;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Integer getCampanhaID() {
        return campanhaID;
    }

    public void setCampanhaID(Integer campanhaID) {
        this.campanhaID = campanhaID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipoDeCampanha() {
        return tipoDeCampanha;
    }

    public void setTipoDeCampanha(int tipoDeCampanha) {
        this.tipoDeCampanha = tipoDeCampanha;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    @XmlTransient
    public List<Turno> getTurnoList() {
        return turnoList;
    }

    public void setTurnoList(List<Turno> turnoList) {
        this.turnoList = turnoList;
    }

    @XmlTransient
    public List<Historicocampanha> getHistoricocampanhaList() {
        return historicocampanhaList;
    }

    public void setHistoricocampanhaList(List<Historicocampanha> historicocampanhaList) {
        this.historicocampanhaList = historicocampanhaList;
    }

    @XmlTransient
    public List<VoluntariosCampanhas> getVoluntariosCampanhasList() {
        return voluntariosCampanhasList;
    }

    public void setVoluntariosCampanhasList(List<VoluntariosCampanhas> voluntariosCampanhasList) {
        this.voluntariosCampanhasList = voluntariosCampanhasList;
    }

    @XmlTransient
    public List<Relogioponto> getRelogiopontoList() {
        return relogiopontoList;
    }

    public void setRelogiopontoList(List<Relogioponto> relogiopontoList) {
        this.relogiopontoList = relogiopontoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (campanhaID != null ? campanhaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campanha)) {
            return false;
        }
        Campanha other = (Campanha) object;
        if ((this.campanhaID == null && other.campanhaID != null) || (this.campanhaID != null && !this.campanhaID.equals(other.campanhaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String aux1 = (dataInicio != null)? dataInicio.toString() : new Date().toString();
        String aux2 = (dataInicio != null)? dataFim.toString() : new Date().toString();
        return "Persistence.Entities.Campanha[ campanhaID=" + campanhaID + " "
                + "\n descricao=" + descricao
                + "\n TipoDeCampanha=" + tipoDeCampanha
                + "\n DataInicio=" + aux1
                + "\n DataFim=" + aux2
                + "]";
    }

}
