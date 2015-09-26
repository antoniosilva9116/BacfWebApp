/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.Controllers;

import Persistence.Entities.SessionBeans.VoluntarioFacade;
import Persistence.Entities.Turno;
import Persistence.Entities.Voluntario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author António Silva
 */
@ManagedBean
public class VoluntariosPickListView {

    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioFacade ejbFacade;

    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioTurnoFacade ejbVoluntarioTurnoFacade;
    private DualListModel<Voluntario> voluntarios;

    @PostConstruct
    public void init() {
        //Voluntarios
        List<Voluntario> voluntariosSource = new ArrayList<Voluntario>();
        List<Voluntario> voluntariosTarget = new ArrayList<Voluntario>();

        voluntariosSource = ejbFacade.findAll();

        System.out.println("\n-----------GETING VOLUNTARIOS------------\n[Voluntarios");
        for (Voluntario voluntario : voluntariosSource) {
            System.out.println(voluntario.toString());
        }
        System.out.println("]");

        voluntarios = new DualListModel<Voluntario>(voluntariosSource, voluntariosTarget);
        System.out.println("\n--------------INITIALIZE VOLUNTARIOS------------------\n");
    }

    private VoluntarioFacade getFacade() {
        return ejbFacade;
    }

    public DualListModel<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    public boolean findVoluntariosByTurno(Turno turno) {
        List<Voluntario> voluntariosAdded = ejbVoluntarioTurnoFacade.findVoluntariosByTurnoID(turno);

        if (voluntariosAdded != null && voluntariosAdded.size() > 0) {
            List<Voluntario> voluntariosDisp = ejbFacade.findAll();

            voluntariosDisp.removeAll(voluntariosAdded);

            this.voluntarios.setSource(voluntariosDisp);
            this.voluntarios.setTarget(voluntariosAdded);
        }
        
        return true;
    }

    public void setVoluntarios(DualListModel<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((String) item).toString()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        if (event.isAdd()) {
            msg.setSummary("Adicionado o volunt&aacute;rio");
        }
        if (event.isRemove()) {
            msg.setSummary("Removido o volunt&aacute;rio");
        }
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
