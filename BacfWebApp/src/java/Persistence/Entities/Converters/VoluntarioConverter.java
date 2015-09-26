/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.Converters;

import Persistence.Entities.Controllers.TurnosController;
import Persistence.Entities.SessionBeans.VoluntarioFacade;
import Persistence.Entities.Voluntario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author António Silva
 */
@ManagedBean
@RequestScoped
@FacesConverter(value = "VoluntarioConverter")
public class VoluntarioConverter implements Converter, Serializable{

    @ManagedProperty(value = "#{turnosController}")
    private TurnosController turnosController;
    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("\n-----------------GET AS OBJECT-----------\n");

        System.out.println("\n----------------------Value---------------------\n" + value);
        String[] aux = value.split("-");
        Integer pk = Integer.parseInt(aux[0]);
        Voluntario voluntario = null;

        voluntario = ejbFacade.find(pk);
        System.out.println("\n---------------RETRIEVE OBJECT----------------------\n");
        System.out.println(voluntario.toString());

        return voluntario;
    }

    private VoluntarioFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        System.out.println("\n-----------------GET AS STRING-----------\n Class=" + value.getClass());

        Voluntario voluntario = ((Voluntario) value);
        
        if (voluntario.getNome() != null) {
            return voluntario.toString();
        }
        return null;
    }

    public TurnosController getTurnosController() {
        return turnosController;
    }

    public void setTurnosController(TurnosController turnosController) {
        this.turnosController = turnosController;
    }

    public VoluntarioFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(VoluntarioFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }


}
