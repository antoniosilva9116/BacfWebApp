package Persistence.Entities.Controllers;

import Persistence.Entities.Campanha;
import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Controllers.util.PaginationHelper;
import Persistence.Entities.Enums.TipoCalendario;
import Persistence.Entities.Estabelecimento;
import Persistence.Entities.SessionBeans.TurnoEstabelecimentoFacade;
import Persistence.Entities.SessionBeans.TurnoFacade;
import Persistence.Entities.SessionBeans.VoluntarioTurnoFacade;
import Persistence.Entities.Turno;
import Persistence.Entities.Voluntario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@Named("turnoController")
@SessionScoped
public class TurnoController implements Serializable {

    private Turno current;
    private DataModel items = null;
    @EJB
    private Persistence.Entities.SessionBeans.TurnoFacade ejbFacade;
    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioTurnoFacade ejbFacadeVoluntarioTurno;
    @EJB
    private Persistence.Entities.SessionBeans.TurnoEstabelecimentoFacade ejbFacadeTurnoEstabelecimento;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private TipoCalendario tipoCalendario = TipoCalendario.GLOBAL;
    private ScheduleModel eventModel;
    private List<Turno> turnos;

    public TurnoController() {
    }

    /**
     * Metodo para inicializar a nossa agenda. Atraves deste metodo retornamos a
     * lista de eventos e montamos nosso calendario ScheduleModel atraves de um
     * DefaultScheduleModel
     */
    @PostConstruct
    private void init() {
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");

        System.out.println("\n---------------GET ALL TURNOS--------\n SIZE=" + turnos.size());

        eventModel = new DefaultScheduleModel();
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");
        System.out.println("\n****************************INIT INIT INIT INIT**********************************************************************\n");

        System.out.println("\n---------------GET ALL TURNOS--------\n SIZE=" + turnos.size());

        //recupera a lista de eventos
        turnos = ejbFacade.findAll();

        //percorre a lista de eventos e cria o calendario
        System.out.println("\n---------------TURNOS SIZE--------\n SIZE=" + turnos.size());
        for (Turno turnoAux : turnos) {

            DefaultScheduleEvent evento = new DefaultScheduleEvent();
            evento.setAllDay(false);
            System.out.println("Data FIm" + turnoAux.getHoraFim());
            evento.setEndDate(turnoAux.getHoraFim());
            evento.setStartDate(turnoAux.getHoraInicio());
            evento.setTitle("Turno Campanha");
            evento.setData(turnoAux.getTurnoID());
            evento.setEditable(true); //pertir que o usuario edite

            //alteracao do tipo especifico de css para cada tipo de evento
            evento.setStyleClass("pessoal");

            eventModel.addEvent(evento); //o evento e adicionado na lista
        }

        System.out.println("\n---------------\nConsegui criar o calendario");
    }

    public Turno getSelected() {
        if (current == null) {
            current = new Turno();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TurnoFacade getFacade() {
        return ejbFacade;
    }

    private VoluntarioTurnoFacade getEjbFacadeVoluntarioTurno() {
        return ejbFacadeVoluntarioTurno;
    }

    private TurnoEstabelecimentoFacade getEjbFacadeTurnoEstabelecimento() {
        return ejbFacadeTurnoEstabelecimento;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public String prepareView() {
        current = (Turno) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Turno();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.setDia(new Date());
            current.setTurnoID(0);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("TurnoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Turno) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
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

    public String destroy() {
        current = (Turno) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("TurnoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public TipoCalendario getTipoCalendario() {
        return tipoCalendario;
    }

    public void setTipoCalendario(TipoCalendario tipoCalendario) {
        this.tipoCalendario = tipoCalendario;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Turno getTurno(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    private List<Voluntario> voluntarios;
    private Estabelecimento estabelecimento;
    private Campanha campanha;

    public void values(ActionEvent e) {
        System.out.println("\n**************************************************************************\n");
        System.out.println("\n---------------------------------A OBTER O ATRIBUTO ----------------------------------------------------\n");

        if (e.getComponent().getAttributes().get("campanha") instanceof Campanha) {
            System.out.println("\n-------------------------------CAMPANHA-------------------------\n");
            campanha = (Campanha) e.getComponent().getAttributes().get("campanha");
            System.out.println(campanha.toString());
        }

        if (e.getComponent().getAttributes().get("voluntarios") instanceof List<?>) {
            System.out.println("\n-------------------------------VOLUNTARIOS-------------------------\n");

            voluntarios = (List<Voluntario>) e.getComponent().getAttributes().get("voluntarios");
            System.out.println("Voluntarios Size=" + voluntarios.size());
        }

        if (e.getComponent().getAttributes().get("estabelecimento") instanceof Estabelecimento) {
            System.out.println("\n----------------------------------ESTABELECIMENTO-------------------------------\n");
            estabelecimento = (Estabelecimento) e.getComponent().getAttributes().get("estabelecimento");
            System.out.println(estabelecimento.toString());
        }

        if (voluntarios != null && voluntarios.size() > 0 && estabelecimento != null && campanha != null) {
            System.out.println("\n*************************************************SAVE****************************************************************\n");
            System.out.println("\n*************************************************SAVE****************************************************************\n");
            System.out.println("\n*************************************************SAVE****************************************************************\n");
            save();
        }
        System.out.println("\n**************************************************************************\n");
    }

    public void value(ActionEvent e) {
        System.out.println("\n**************************************************************************\n");
        System.out.println("\n---------------------------------A OBTER O ATRIBUTO ----------------------------------------------------\n");

        if (e.getComponent().getAttributes().get("campanha") instanceof Campanha) {
            System.out.println("\n-------------------------------CAMPANHA-------------------------\n");
            campanha = (Campanha) e.getComponent().getAttributes().get("campanha");
            System.out.println(campanha.toString());
            current.setCampanhaID(campanha);
        }

        if (campanha != null) {
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
     * @param voluntarios
     * @param estabelecimento
     */
    public void save() {

        //se o ID for zero guardar na base de dados
        //caso contrario nao e necessario
        if (getSelected().getTurnoID() == null) {

            System.out.println("\n---------------------------------SAVE----------------------------------------------\n");
            if (voluntarios != null && estabelecimento != null && campanha != null) {
                System.out.println("\n-------------------LIST VOLUNTARIOS-----------------------\n SIZE=" + voluntarios.size());
                System.out.println("\n-------------------ESTABELECIMENTO-----------------------\n " + estabelecimento.toString());
                System.out.println("\n-------------------ESTABELECIMENTO-----------------------\n " + campanha.toString());

                current.setCampanhaID(campanha);
                
                

            }
            //uma validacao basica para verificar se o utilizador selecionou 
            //as datas corretamente
            if (getSelected().getHoraInicio().getTime() <= getSelected().getHoraFim().getTime()) {
                create();
                current = new Turno();

                System.out.println("\n--------------------------VOLTANDO A DESENHAR--------------------------\n");
                init();

            } else {
                //a data de inicio nao pode ser maior que a data final
                if (getSelected().getHoraInicio().getTime() <= getSelected().getHoraFim().getTime()) {
                    System.out.println("\n ----------------UPDATE-----------------------\n");
                    update();
                }
            }

        } else {
            update();
            init();
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
    public static class TurnoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TurnoController controller = (TurnoController) facesContext.getApplication().getELResolver().
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
