/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.Converters;

import Persistence.Entities.Controllers.EstabelecimentoController;
import Persistence.Entities.Estabelecimento;
import Persistence.Entities.SessionBeans.EstabelecimentoFacade;
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
@FacesConverter(value = "EstabelecimentoConverter")
public class EstabelecimentoConverter implements Converter, Serializable {

    @ManagedProperty(value = "#{estabelecimentoController}")
    private EstabelecimentoController estabelecimentoController;
    @EJB
    private Persistence.Entities.SessionBeans.EstabelecimentoFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        System.out.println("\n-----------------GET AS OBJECT-----------\n");

        System.out.println("\n----------------------Value---------------------\n" + value);
        String[] aux = value.split("-");
        Integer pk = Integer.parseInt(aux[0]);
        Estabelecimento estabelecimento = null;

        estabelecimento = ejbFacade.find(pk);
        System.out.println("\n---------------RETRIEVE OBJECT----------------------\n");
        System.out.println(estabelecimento.toString());

        return estabelecimento;
    }

    private EstabelecimentoFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        System.out.println("\n-----------------GET AS STRING-----------\n Class=" + value.getClass());

        Estabelecimento estabelecimento = ((Estabelecimento) value);
        
        if (estabelecimento.getCadeia() != null) {
            return estabelecimento.toString();
        }
        return null;
    }

    public EstabelecimentoController getEstabelecimentoController() {
        return estabelecimentoController;
    }

    public void setEstabelecimentoController(EstabelecimentoController estabelecimentoController) {
        this.estabelecimentoController = estabelecimentoController;
    }

}
