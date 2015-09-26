/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.Controllers;

import Persistence.Entities.Campanha;
import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Enums.TipoCalendario;
import Persistence.Entities.Estabelecimento;
import Persistence.Entities.SessionBeans.EquipaFacade;
import Persistence.Entities.SessionBeans.EstabelecimentoFacade;
import Persistence.Entities.SessionBeans.TurnoEstabelecimentoFacade;
import Persistence.Entities.SessionBeans.TurnoFacade;
import Persistence.Entities.SessionBeans.VoluntarioFacade;
import Persistence.Entities.SessionBeans.VoluntarioTurnoFacade;
import Persistence.Entities.Turno;
import Persistence.Entities.TurnoEstabelecimento;
import Persistence.Entities.Voluntario;
import Persistence.Entities.VoluntarioTurno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.inject.Named;
import mail.MailReminder;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DualListModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author António Silva
 */
@Named("turnosController")
@ManagedBean
@SessionScoped
public class TurnosController implements Serializable {

    private Turno current;
    private Campanha campanha;
    private Estabelecimento estabelecimento;
    private DataModel items = null;
    @EJB
    private Persistence.Entities.SessionBeans.EquipaFacade ejbEquipaFacade;
    @EJB
    private Persistence.Entities.SessionBeans.EstabelecimentoFacade ejbEstabelecimentoFacade;
    @EJB
    private Persistence.Entities.SessionBeans.TurnoFacade ejbFacade;
    @EJB
    private Persistence.Entities.SessionBeans.TurnoEstabelecimentoFacade ejbFacadeTurnoEstabelecimento;
    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioFacade ejbVoluntarioFacade;
    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioTurnoFacade ejbVoluntarioTurnoFacade;
    private TipoCalendario tipoCalendario = TipoCalendario.GLOBAL;
    private ScheduleModel eventModel;
    private List<Turno> turnos;

    private DualListModel<Voluntario> voluntarios;

    public TurnosController() {
    }

    @PostConstruct
    public void init() {
        //Voluntarios
        List<Voluntario> voluntariosSource = new ArrayList<Voluntario>();
        List<Voluntario> voluntariosTarget = new ArrayList<Voluntario>();

        getAllEstabelecimentos();
//        voluntariosSource = getVoluntarioFacade().findAll();
        voluntariosSource = getEquipaFacade().findVoluntarioByEstabelecimentoID(estabelecimento);

        if (getSelected().getTurnoID() != null) {
            if (!findVoluntariosByTurno() || !findEstabelecimentoByTurno()) {
                System.out.println("\n-----------GETING VOLUNTARIOS------------\n[Voluntarios");
                System.out.println("\nVoluntarios Disp=");
                for (Voluntario voluntario : voluntariosSource) {
                    System.out.println(voluntario.toString());
                }

                voluntarios = new DualListModel<Voluntario>(voluntariosSource, voluntariosTarget);
                System.out.println("\n--------------INITIALIZE VOLUNTARIOS------------------\n");
            }
        } else {
            System.out.println("\n-----------GETING VOLUNTARIOS------------\n[Voluntarios");
            System.out.println("\nVoluntarios Disp=");
            for (Voluntario voluntario : voluntariosSource) {
                System.out.println(voluntario.toString());
            }

            voluntarios = new DualListModel<Voluntario>(voluntariosSource, voluntariosTarget);
            System.out.println("\n--------------INITIALIZE VOLUNTARIOS------------------\nTarget=" + voluntarios.getTarget().size());
            System.out.println("\n--------------INITIALIZE VOLUNTARIOS------------------\nSource=" + voluntarios.getSource().size());

        }

        eventModel = new DefaultScheduleModel();

        System.out.println(
                "\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println(
                "\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println(
                "\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println(
                "\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println(
                "\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println(
                "\n****************************INIT INIT INIT INIT**********************************************************************\n");

        //recupera a lista de eventos
        turnos = getTurnoEstabelecimentoFacade().findTurnosByEstabelecimentoID(getEstabelecimento());

        System.out.println(
                "\n---------------GET ALL TURNOS--------\n SIZE=" + turnos.size());

        //percorre a lista de eventos e cria o calendario
        System.out.println(
                "\n---------------TURNOS SIZE--------\n SIZE=" + turnos.size());
        for (Turno turnoAux : turnos) {

            DefaultScheduleEvent evento = new DefaultScheduleEvent();
            evento.setAllDay(false);
            System.out.println("Data FIm" + turnoAux.getHoraFim());

            Date dataFim = (Date) turnoAux.getDia().clone();
            Date dataInicio = (Date) turnoAux.getDia().clone();

//            dataFim.setHours(turnoAux.getHoraFim().getHours());
//            dataFim.setMinutes(turnoAux.getHoraFim().getMinutes());
//            dataFim.setSeconds(turnoAux.getHoraFim().getSeconds());

            dataFim = (Date) turnoAux.getHoraInicio().clone();
            dataInicio = (Date) turnoAux.getHoraInicio().clone();
            
//            dataInicio.setHours(turnoAux.getHoraInicio().getHours());
//            dataInicio.setMinutes(turnoAux.getHoraInicio().getMinutes());
//            dataInicio.setSeconds(turnoAux.getHoraInicio().getSeconds());

            evento.setEndDate(dataFim);
            evento.setStartDate(dataInicio);
            evento.setTitle("Turno Campanha");
            evento.setData(turnoAux.getTurnoID());
            evento.setEditable(true); //pertir que o usuario edite

            //alteracao do tipo especifico de css para cada tipo de evento
            evento.setStyleClass("pessoal");

            eventModel.addEvent(evento); //o evento e adicionado na lista
        }

        System.out.println(
                "\n---------------\nConsegui criar o calendario");
    }

    private TurnoFacade getFacade() {
        return ejbFacade;
    }

    private TurnoEstabelecimentoFacade getTurnoEstabelecimentoFacade() {
        return ejbFacadeTurnoEstabelecimento;
    }

    private VoluntarioFacade getVoluntarioFacade() {
        return ejbVoluntarioFacade;
    }

    private VoluntarioTurnoFacade getVoluntarioTurnoFacade() {
        return ejbVoluntarioTurnoFacade;
    }

    public Turno getSelected() {
        if (current == null) {
            current = new Turno();
        }
        return current;
    }

    public Estabelecimento getEstabelecimento() {
        if (estabelecimento == null) {
            estabelecimento = new Estabelecimento();
        }

        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;

        turnos = getTurnoEstabelecimentoFacade().findTurnosByEstabelecimentoID(estabelecimento);

        init();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public EquipaFacade getEquipaFacade() {
        return ejbEquipaFacade;
    }

    public DualListModel<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(DualListModel<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    public Turno getTurno(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    private EstabelecimentoFacade getEstabelecimentoFacade() {
        return ejbEstabelecimentoFacade;
    }

    public boolean findVoluntariosByTurno() {
        if (current.getTurnoID() != null) {
            List<Voluntario> voluntariosAdded = ejbVoluntarioTurnoFacade.findVoluntariosByTurnoID(current);

            if (voluntariosAdded != null && voluntariosAdded.size() > 0) {
//                List<Voluntario> voluntariosDisp = ejbVoluntarioFacade.findAll();
                List<Voluntario> voluntariosDisp = getEquipaFacade().findVoluntarioByEstabelecimentoID(estabelecimento);

                voluntariosDisp.removeAll(voluntariosAdded);

//                voluntarios = null;
                setVoluntarios(new DualListModel<>(voluntariosDisp, voluntariosAdded));
            }

            return true;
        } else {
            List<Voluntario> voluntariosSource = new ArrayList<Voluntario>();
            List<Voluntario> voluntariosTarget = new ArrayList<Voluntario>();
            System.out.println("\n-----------GETING VOLUNTARIOS------------\n[Voluntarios");
            System.out.println("\nVoluntarios Disp=");
            for (Voluntario voluntario : voluntariosSource) {
                System.out.println(voluntario.toString());
            }

            voluntarios = new DualListModel<Voluntario>(voluntariosSource, voluntariosTarget);
            System.out.println("\n--------------INITIALIZE VOLUNTARIOS------------------\nTarget=" + voluntarios.getTarget().size());
            System.out.println("\n--------------INITIALIZE VOLUNTARIOS------------------\nSource=" + voluntarios.getSource().size());

        }

        return false;
    }

    public boolean findEstabelecimentoByTurno() {
        if (current.getTurnoID() != null) {
            estabelecimento = ejbFacadeTurnoEstabelecimento.findEstabelecimentoByTurnoID(current);

            return true;
        }

        return false;
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

    public String create() {
        try {
            current.setTurnoID(0);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("TurnoCreated"));
            return "";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("TurnoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public List<Estabelecimento> getAllEstabelecimentos() {
        List<Estabelecimento> estabelecimentos = getEstabelecimentoFacade().findAll();
        if (estabelecimento == null) {
            estabelecimento = estabelecimentos.get(0);
        }
        return estabelecimentos;
    }

    public void values(ActionEvent e) {
        System.out.println("\n**************************************************************************\n");
        System.out.println("\n---------------------------------A OBTER O ATRIBUTO ----------------------------------------------------\n");

        if (e.getComponent().getAttributes().get("campanha") instanceof Campanha) {
            System.out.println("\n-------------------------------CAMPANHA-------------------------\n");
            campanha = (Campanha) e.getComponent().getAttributes().get("campanha");
            System.out.println(campanha.toString());
        }

        if (e.getComponent().getAttributes().get("data") instanceof Date) {
            System.out.println("\n----------------------------------DaTa-------------------------------\n");
            Date date = (Date) e.getComponent().getAttributes().get("data");
            current.setDia(date);
        }

        if (estabelecimento != null && campanha != null) {
            System.out.println("\n*************************************************SAVE****************************************************************\n");
            System.out.println("\n*************************************************SAVE****************************************************************\n");
            System.out.println("\n*************************************************SAVE****************************************************************\n");
            save();
        }
        System.out.println("\n**************************************************************************\n");
    }

    /**
     * Metodo para guardar o evento
     *
     */
    public void save() {

        //se o ID for zero guardar na base de dados
        //caso contrario nao e necessario
        if (getSelected().getTurnoID() == null) {

            System.out.println("\n---------------------------------SAVE----------------------------------------------\n");

            //uma validacao basica para verificar se o utilizador selecionou 
            //as datas corretamente
            if (getSelected().getHoraInicio().getTime() <= getSelected().getHoraFim().getTime()) {
                current.setCampanhaID(campanha);
                create();
                if (voluntarios != null && estabelecimento != null && campanha != null) {
                    System.out.println("\n-----------------------------CURRENT--------------------------------\n" + current.toString());
                    System.out.println("\n-------------------LIST VOLUNTARIOS-----------------------\n SIZE=" + voluntarios.getTarget().size());
                    System.out.println("\n-------------------ESTABELECIMENTO-----------------------\n " + estabelecimento.toString());
                    System.out.println("\n-------------------CAMPANHA-----------------------\n " + campanha.toString());

                    TurnoEstabelecimento turnoEstabelecimento = new TurnoEstabelecimento();
                    turnoEstabelecimento.setTurnoID(current);
                    turnoEstabelecimento.setEstabelecimentoID(estabelecimento);

                    getTurnoEstabelecimentoFacade().create(turnoEstabelecimento);

                    MailReminder emailReminder = new MailReminder();

                    for (Voluntario voluntario : voluntarios.getTarget()) {
                        VoluntarioTurno voluntarioTurno = new VoluntarioTurno();
                        voluntarioTurno.setTurnoID(current);
                        voluntarioTurno.setAceite(true);
                        voluntarioTurno.setVoluntarioID(voluntario);

                        voluntarioTurno.setTurnoVoluntarioID(0);
                        getVoluntarioTurnoFacade().create(voluntarioTurno);
                    }

                    emailReminder.mandaEmailVoluntarios("Cria&ccedil;&atilde;o de Turno", "foi inserido no turno das  " + current.getHoraInicio().toString() + " at&eacute; "
                            + current.getHoraFim().toString()
                            + "." + "<p> Desde j&aacute; agradecemos a sua disponibilidade.</p>"
                            + "<p>Com os melhores cumprimentos, </p>"
                            + "<p>Banco Alimentar contra a fome</p>", voluntarios.getTarget());

                }
                current = new Turno();

                System.out.println("\n--------------------------VOLTANDO A DESENHAR--------------------------\n");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O Turno foi criado com sucesso."));

                init();

            } else {
                //a data de inicio nao pode ser maior que a data final
                if (getSelected().getHoraInicio().getTime() <= getSelected().getHoraFim().getTime()) {
                    System.out.println("\n ----------------UPDATE-----------------------\n");
                    update();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O Turno foi atualizado com sucesso."));
                }
            }

        } else {

            List<VoluntarioTurno> voluntarios = getVoluntarioTurnoFacade().findByTurnoID(current);

            for (VoluntarioTurno voluntario : voluntarios) {
                getVoluntarioTurnoFacade().remove(voluntario);
            }

            for (Voluntario voluntario : this.voluntarios.getTarget()) {
                VoluntarioTurno voluntarioTurno = new VoluntarioTurno();
                voluntarioTurno.setTurnoID(current);
                voluntarioTurno.setAceite(true);
                voluntarioTurno.setVoluntarioID(voluntario);

                voluntarioTurno.setTurnoVoluntarioID(0);
                getVoluntarioTurnoFacade().create(voluntarioTurno);
            }

            update();
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O Turno foi atualizado com sucesso."));

        }
        System.out.println("\n---------------------------------SAVE----------------------------------------------\n");
    }

    /**
     * Evento para quando o usuario clica em um espaco em branco no calendario
     *
     * @param selectEvent
     */
    public void addNewTurno(SelectEvent selectEvent) {

        ScheduleEvent event = new DefaultScheduleEvent("",
                (Date) selectEvent.getObject(), (Date) selectEvent.getObject());

        List<Voluntario> voluntariosSource = new ArrayList<Voluntario>();
        List<Voluntario> voluntariosTarget = new ArrayList<Voluntario>();

//        voluntariosSource = ejbVoluntarioFacade.findAll();
        voluntariosSource = getEquipaFacade().findVoluntarioByEstabelecimentoID(estabelecimento);

//        voluntarios = null;
        setVoluntarios(new DualListModel<Voluntario>(voluntariosSource, voluntariosTarget));

        current = new Turno();
        //recupero a data em q ele clicou
        current.setHoraInicio(event.getStartDate());
        current.setHoraFim(event.getEndDate());
    }

    /**
     * Evento para quando usuario clica em um enveto ja existente
     *
     * @param selectEvent
     */
    public void showTurno(SelectEvent selectEvent) {
        System.out.println("\n-----------------------------------SHOW TURNO--------------------------------------------------\n");

        ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

        System.out.println("\n-----------------------------------SHOW TURNO--------------------------------------------------\n");

        for (Turno turn : turnos) {
            if (turn.getTurnoID() == (Integer) event.getData()) {
                System.out.println("\n----------------------------OBTENDO O TURNO---------------------------------\n");
                System.out.println(turn.toString());
                System.out.println("\n----------------------------OBTENDO O TURNO---------------------------------\n");

                current = turn;

                if (current.getTurnoID() != null) {
                    findVoluntariosByTurno();
                }

                break;
            }
        }
    }

    /**
     * Evento para quando o usuario 'move' um evento atraves de 'drag and drop'
     *
     * @param event
     */
    public void whenMoved(ScheduleEntryMoveEvent event) {

        for (Turno turn : turnos) {

            if (turn.getTurnoID() == (Integer) event.getScheduleEvent().getData()) {
                current = turn;
                //salvar sua agenda no banco
                save();
                break;
            }
        }
    }

    /**
     * Evento para quando o usuario 'redimenciona' um evento
     *
     * @param event
     */
    public void whenSetDate(ScheduleEntryResizeEvent event) {

        for (Turno turn : turnos) {
            if (turn.getTurnoID() == (Integer) event.getScheduleEvent().getData()) {
                current = turn;
                //salvar sua agenda no banco
                save();
                break;
            }
        }
    }

    public List<TipoCalendario> getTiposCalendario() {
        return Arrays.asList(TipoCalendario.values());

    }

    @FacesConverter(forClass = Turno.class)
    public static class TurnosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TurnosController controller = (TurnosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "turnoController");
            return controller.getTurno(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Turno) {
                Turno o = (Turno) object;
                return getStringKey(o.getTurnoID());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Turno.class.getName());
            }
        }

    }

}
