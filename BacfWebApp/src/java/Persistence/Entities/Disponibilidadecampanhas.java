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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author António Silva
 */
@Entity
@Table(name = "disponibilidadecampanhas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disponibilidadecampanhas.findAll", query = "SELECT d FROM Disponibilidadecampanhas d"),
    @NamedQuery(name = "Disponibilidadecampanhas.findByDisponibilidadeCampanhasID", query = "SELECT d FROM Disponibilidadecampanhas d WHERE d.disponibilidadeCampanhasID = :disponibilidadeCampanhasID"),
    @NamedQuery(name = "Disponibilidadecampanhas.findBySegunda", query = "SELECT d FROM Disponibilidadecampanhas d WHERE d.segunda = :segunda"),
    @NamedQuery(name = "Disponibilidadecampanhas.findByTerca", query = "SELECT d FROM Disponibilidadecampanhas d WHERE d.terca = :terca"),
    @NamedQuery(name = "Disponibilidadecampanhas.findByQuarta", query = "SELECT d FROM Disponibilidadecampanhas d WHERE d.quarta = :quarta"),
    @NamedQuery(name = "Disponibilidadecampanhas.findByQuinta", query = "SELECT d FROM Disponibilidadecampanhas d WHERE d.quinta = :quinta"),
    @NamedQuery(name = "Disponibilidadecampanhas.findBySexta", query = "SELECT d FROM Disponibilidadecampanhas d WHERE d.sexta = :sexta"),
    @NamedQuery(name = "Disponibilidadecampanhas.findBySabado", query = "SELECT d FROM Disponibilidadecampanhas d WHERE d.sabado = :sabado"),
    @NamedQuery(name = "Disponibilidadecampanhas.findByDomingo", query = "SELECT d FROM Disponibilidadecampanhas d WHERE d.domingo = :domingo"),
    @NamedQuery(name = "Disponibilidadecampanhas.findByVoluntarioID", query = "SELECT d FROM Disponibilidadecampanhas d WHERE d.voluntarioVoluntarioID = :voluntarioVoluntarioID")})

public class Disponibilidadecampanhas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DisponibilidadeCampanhasID")
    private Integer disponibilidadeCampanhasID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Segunda")
    private int segunda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Terca")
    private int terca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quarta")
    private int quarta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quinta")
    private int quinta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sexta")
    private int sexta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sabado")
    private int sabado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Domingo")
    private int domingo;
    @JoinColumn(name = "VoluntarioVoluntarioID", referencedColumnName = "VoluntarioID")
    @ManyToOne(optional = false)
    private Voluntario voluntarioVoluntarioID;

    public Disponibilidadecampanhas() {
    }

    public Disponibilidadecampanhas(Integer disponibilidadeCampanhasID) {
        this.disponibilidadeCampanhasID = disponibilidadeCampanhasID;
    }

    public Disponibilidadecampanhas(Integer disponibilidadeCampanhasID, int segunda, int terca, int quarta, int quinta, int sexta, int sabado, int domingo) {
        this.disponibilidadeCampanhasID = disponibilidadeCampanhasID;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
        this.domingo = domingo;
    }

    public Integer getDisponibilidadeCampanhasID() {
        return disponibilidadeCampanhasID;
    }

    public void setDisponibilidadeCampanhasID(Integer disponibilidadeCampanhasID) {
        this.disponibilidadeCampanhasID = disponibilidadeCampanhasID;
    }

    public int getSegunda() {
        return segunda;
    }

    public void setSegunda(int segunda) {
        this.segunda = segunda;
    }

    public int getTerca() {
        return terca;
    }

    public void setTerca(int terca) {
        this.terca = terca;
    }

    public int getQuarta() {
        return quarta;
    }

    public void setQuarta(int quarta) {
        this.quarta = quarta;
    }

    public int getQuinta() {
        return quinta;
    }

    public void setQuinta(int quinta) {
        this.quinta = quinta;
    }

    public int getSexta() {
        return sexta;
    }

    public void setSexta(int sexta) {
        this.sexta = sexta;
    }

    public int getSabado() {
        return sabado;
    }

    public void setSabado(int sabado) {
        this.sabado = sabado;
    }

    public int getDomingo() {
        return domingo;
    }

    public void setDomingo(int domingo) {
        this.domingo = domingo;
    }

    public Voluntario getVoluntarioVoluntarioID() {
        return voluntarioVoluntarioID;
    }

    public void setVoluntarioVoluntarioID(Voluntario voluntarioVoluntarioID) {
        this.voluntarioVoluntarioID = voluntarioVoluntarioID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (disponibilidadeCampanhasID != null ? disponibilidadeCampanhasID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disponibilidadecampanhas)) {
            return false;
        }
        Disponibilidadecampanhas other = (Disponibilidadecampanhas) object;
        if ((this.disponibilidadeCampanhasID == null && other.disponibilidadeCampanhasID != null) || (this.disponibilidadeCampanhasID != null && !this.disponibilidadeCampanhasID.equals(other.disponibilidadeCampanhasID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistence.Entities.Disponibilidadecampanhas[ disponibilidadeCampanhasID=" + disponibilidadeCampanhasID + ""
                + "segunda=" + segunda
                + "terca=" + terca
                + "quarta=" + quarta
                + "quinta=" + quinta
                + "sexta=" + sexta
                + "sabado=" + sabado
                + "domingo=" + domingo
                + "Voluntario=" + voluntarioVoluntarioID.toString()
                + " ]";
    }
    
}
