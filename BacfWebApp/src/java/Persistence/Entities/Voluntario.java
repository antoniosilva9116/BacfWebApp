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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "voluntario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voluntario.findAll", query = "SELECT v FROM Voluntario v"),
    @NamedQuery(name = "Voluntario.findByVoluntarioID", query = "SELECT v FROM Voluntario v WHERE v.voluntarioID = :voluntarioID"),
    @NamedQuery(name = "Voluntario.findByNome", query = "SELECT v FROM Voluntario v WHERE v.nome = :nome"),
    @NamedQuery(name = "Voluntario.findByLetterNome", query = "SELECT v FROM Voluntario v WHERE v.nome LIKE :nome"),
    @NamedQuery(name = "Voluntario.findByBi", query = "SELECT v FROM Voluntario v WHERE v.bi = :bi"),
    @NamedQuery(name = "Voluntario.findByDigitBi", query = "SELECT v FROM Voluntario v WHERE v.bi LIKE :bi"),
    @NamedQuery(name = "Voluntario.findByTelefoneCasa", query = "SELECT v FROM Voluntario v WHERE v.telefoneCasa = :telefoneCasa"),
    @NamedQuery(name = "Voluntario.findByTelemovel", query = "SELECT v FROM Voluntario v WHERE v.telemovel = :telemovel"),
    @NamedQuery(name = "Voluntario.findByTelefoneTrabalho", query = "SELECT v FROM Voluntario v WHERE v.telefoneTrabalho = :telefoneTrabalho"),
    @NamedQuery(name = "Voluntario.findByEmail", query = "SELECT v FROM Voluntario v WHERE v.email = :email"),
    @NamedQuery(name = "Voluntario.findByLetterEmail", query = "SELECT v FROM Voluntario v WHERE v.email LIKE :email"),
    @NamedQuery(name = "Voluntario.findByDataNascimento", query = "SELECT v FROM Voluntario v WHERE v.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "Voluntario.findByProfissao", query = "SELECT v FROM Voluntario v WHERE v.profissao = :profissao"),
    @NamedQuery(name = "Voluntario.findBySexo", query = "SELECT v FROM Voluntario v WHERE v.sexo = :sexo"),
    @NamedQuery(name = "Voluntario.findByUtilizadorID", query = "SELECT v FROM Voluntario v WHERE v.utilizadorID = :utilizadorID")})

public class Voluntario implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voluntarioVoluntarioID")
    private List<Disponibilidadecampanhas> disponibilidadecampanhasList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VoluntarioID")
    private Integer voluntarioID;
    @Size(min = 1, max = 100)
    @Column(name = "Nome")
    private String nome;
    @Column(name = "BI")
    private Integer bi;
    @Column(name = "TelefoneCasa")
    private Integer telefoneCasa;
    @Column(name = "Telemovel")
    private Integer telemovel;
    @Column(name = "TelefoneTrabalho")
    private Integer telefoneTrabalho;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "Email")
    private String email;
    @Column(name = "DataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Size(max = 255)
    @Column(name = "Profissao")
    private String profissao;
    @Size(max = 255)
    @Column(name = "Sexo")
    private String sexo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voluntarioID")
    private List<Voluntariojunior> voluntariojuniorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voluntarioID")
    private List<VoluntarioTurno> voluntarioTurnoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voluntarioID")
    private List<VoluntariosCampanhas> voluntariosCampanhasList;
    @JoinColumn(name = "UtilizadorID", referencedColumnName = "UtilizadorID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilizador utilizadorID;
    @JoinColumn(name = "CorreiosID", referencedColumnName = "CorreiosID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Correios correiosID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voluntarioID")
    private List<VoluntarioEquipa> voluntarioEquipaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voluntarioID")
    private List<Relogioponto> relogiopontoList;

    public Voluntario() {
    }

    public Voluntario(Integer voluntarioID) {
        this.voluntarioID = voluntarioID;
    }

    public Voluntario(Integer voluntarioID, String nome) {
        this.voluntarioID = voluntarioID;
        this.nome = nome;
    }

    public Integer getVoluntarioID() {
        return voluntarioID;
    }

    public void setVoluntarioID(Integer voluntarioID) {
        this.voluntarioID = voluntarioID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getBi() {
        return bi;
    }

    public void setBi(Integer bi) {
        this.bi = bi;
    }

    public Integer getTelefoneCasa() {
        return telefoneCasa;
    }

    public void setTelefoneCasa(Integer telefoneCasa) {
        this.telefoneCasa = telefoneCasa;
    }

    public Integer getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(Integer telemovel) {
        this.telemovel = telemovel;
    }

    public Integer getTelefoneTrabalho() {
        return telefoneTrabalho;
    }

    public void setTelefoneTrabalho(Integer telefoneTrabalho) {
        this.telefoneTrabalho = telefoneTrabalho;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @XmlTransient
    public List<Voluntariojunior> getVoluntariojuniorList() {
        return voluntariojuniorList;
    }

    public void setVoluntariojuniorList(List<Voluntariojunior> voluntariojuniorList) {
        this.voluntariojuniorList = voluntariojuniorList;
    }

    @XmlTransient
    public List<VoluntarioTurno> getVoluntarioTurnoList() {
        return voluntarioTurnoList;
    }

    public void setVoluntarioTurnoList(List<VoluntarioTurno> voluntarioTurnoList) {
        this.voluntarioTurnoList = voluntarioTurnoList;
    }

    @XmlTransient
    public List<VoluntariosCampanhas> getVoluntariosCampanhasList() {
        return voluntariosCampanhasList;
    }

    public void setVoluntariosCampanhasList(List<VoluntariosCampanhas> voluntariosCampanhasList) {
        this.voluntariosCampanhasList = voluntariosCampanhasList;
    }

    public Utilizador getUtilizadorID() {
        return utilizadorID;
    }

    public void setUtilizadorID(Utilizador utilizadorID) {
        this.utilizadorID = utilizadorID;
    }

    public Correios getCorreiosID() {
        return correiosID;
    }

    public void setCorreiosID(Correios correiosID) {
        this.correiosID = correiosID;
    }

    @XmlTransient
    public List<VoluntarioEquipa> getVoluntarioEquipaList() {
        return voluntarioEquipaList;
    }

    public void setVoluntarioEquipaList(List<VoluntarioEquipa> voluntarioEquipaList) {
        this.voluntarioEquipaList = voluntarioEquipaList;
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
        hash += (voluntarioID != null ? voluntarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voluntario)) {
            return false;
        }
        Voluntario other = (Voluntario) object;
        if ((this.voluntarioID == null && other.voluntarioID != null) || (this.voluntarioID != null && !this.voluntarioID.equals(other.voluntarioID))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "Persistence.Entities.Voluntario[ voluntarioID=" + voluntarioID 
//                + "\n Email=" + email
//                + "\n nome=" + nome
//                + "\n profissao=" + profissao
//                + "\n sexo=" + sexo
//                + correiosID.toString()
//                + utilizadorID.toString()
//                + " ]";
//    }
   @Override
    public String toString() {
        return voluntarioID + "-" + nome;
    }
    
    @XmlTransient
    public List<Disponibilidadecampanhas> getDisponibilidadecampanhasList() {
        return disponibilidadecampanhasList;
    }

    public void setDisponibilidadecampanhasList(List<Disponibilidadecampanhas> disponibilidadecampanhasList) {
        this.disponibilidadecampanhasList = disponibilidadecampanhasList;
    }
    
}
