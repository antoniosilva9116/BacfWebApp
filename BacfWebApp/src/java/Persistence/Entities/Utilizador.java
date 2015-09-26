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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "utilizador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilizador.findAll", query = "SELECT u FROM Utilizador u"),
    @NamedQuery(name = "Utilizador.findByUtilizadorID", query = "SELECT u FROM Utilizador u WHERE u.utilizadorID = :utilizadorID"),
    @NamedQuery(name = "Utilizador.findByPassword", query = "SELECT u FROM Utilizador u WHERE u.password = :password"),
    @NamedQuery(name = "Utilizador.findByUserName", query = "SELECT u FROM Utilizador u WHERE u.userName = :userName"),
    @NamedQuery(name = "Utilizador.findByEmail", query = "SELECT u FROM Utilizador u WHERE u.email = :email")})
public class Utilizador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UtilizadorID")
    private Integer utilizadorID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "UserName")
    private String userName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizadorID")
    private List<Noticia> noticiaList;
    @JoinColumn(name = "GrupoID", referencedColumnName = "GrupoID")
    @ManyToOne(optional = false)
    private Grupo grupoID;
    @OneToMany(mappedBy = "utilizadorID")
    private List<Voluntario> voluntarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizadorRemetenteID")
    private List<Email> emailList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizadorDestinatarioID")
    private List<Email> emailList1;

    public Utilizador() {
    }

    public Utilizador(Integer utilizadorID) {
        this.utilizadorID = utilizadorID;
    }

    public Utilizador(Integer utilizadorID, String password, String userName, String email) {
        this.utilizadorID = utilizadorID;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public Integer getUtilizadorID() {
        return utilizadorID;
    }

    public void setUtilizadorID(Integer utilizadorID) {
        this.utilizadorID = utilizadorID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Noticia> getNoticiaList() {
        return noticiaList;
    }

    public void setNoticiaList(List<Noticia> noticiaList) {
        this.noticiaList = noticiaList;
    }

    public Grupo getGrupoID() {
        return grupoID;
    }

    public void setGrupoID(Grupo grupoID) {
        this.grupoID = grupoID;
    }

    @XmlTransient
    public List<Voluntario> getVoluntarioList() {
        return voluntarioList;
    }

    public void setVoluntarioList(List<Voluntario> voluntarioList) {
        this.voluntarioList = voluntarioList;
    }

    @XmlTransient
    public List<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }

    @XmlTransient
    public List<Email> getEmailList1() {
        return emailList1;
    }

    public void setEmailList1(List<Email> emailList1) {
        this.emailList1 = emailList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (utilizadorID != null ? utilizadorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilizador)) {
            return false;
        }
        Utilizador other = (Utilizador) object;
        if ((this.utilizadorID == null && other.utilizadorID != null) || (this.utilizadorID != null && !this.utilizadorID.equals(other.utilizadorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Utilizador[ utilizadorID=" + utilizadorID + " ]";
    }
    
}
