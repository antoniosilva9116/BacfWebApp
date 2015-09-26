package Persistence.Entities.Controllers;

import Persistence.Entities.Campanha;
import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Controllers.util.PaginationHelper;
import Persistence.Entities.Enums.TipoCalendario;
import Persistence.Entities.SessionBeans.CampanhaFacade;
import Persistence.Entities.SessionBeans.VoluntarioFacade;
import Persistence.Entities.Voluntario;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import mail.MailReminder;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@Named("campanhaController")
@ManagedBean
@SessionScoped
public class CampanhaController implements Serializable {

    private Campanha current;
    private DataModel items = null;
    @EJB
    private Persistence.Entities.SessionBeans.CampanhaFacade ejbFacade;
     @EJB
    private Persistence.Entities.SessionBeans.VoluntarioFacade ejbVoluntarioFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private TipoCalendario tipoCalendario = TipoCalendario.GLOBAL;
    private ScheduleModel eventModel;
    private List<Campanha> campanhas = new ArrayList<Campanha>();
    @EJB
    private DataSelected selectedDate;
    private boolean first = false;

    public CampanhaController() {
    }

    /**
     * Metodo para inicializar a nossa agenda. Atraves deste metodo retornamos a
     * lista de eventos e montamos nosso calendario ScheduleModel atraves de um
     * DefaultScheduleModel
     */
    @PostConstruct
    private void init() {

        eventModel = new DefaultScheduleModel();

        //ejbFacade = new CampanhaFacade();
        //recupera a lista de eventos
        campanhas = getFacade().findAll();

        current = getCurrentCampanha();

        if (selectedDate != null && isCampanha() != false && current != null) {
            selectedDate.setDate(getMinRange(current.getDataInicio()));
        }
//        System.out.println("\n-----------------POST CONSTRUCT------------------\n[SET SELECTED DATE]=" + selectedDate.getDate().toString());

        //percorre a lista de eventos e cria o calendario
        for (Campanha campanhaAux : campanhas) {

            DefaultScheduleEvent evento = new DefaultScheduleEvent();
            evento.setAllDay(true);
            evento.setEndDate(campanhaAux.getDataFim());
            evento.setStartDate(campanhaAux.getDataInicio());
            evento.setTitle(campanhaAux.getDescricao());
            evento.setData(campanhaAux.getCampanhaID());
            evento.setEditable(true); //pertir que o usuario edite

            //alteracao do tipo especifico de css para cada tipo de evento
            evento.setStyleClass("global");

            eventModel.addEvent(evento); //o evento e adicionado na lista
        }
    }

    public Campanha getCurrentCampanha() {
        return ejbFacade.findByDateNow();
    }

    public DataSelected getDataSelected() {
        return selectedDate;
    }

    public void setDataSelected(DataSelected selectedDate) {
        this.selectedDate = selectedDate;
    }

    private VoluntarioFacade getVoluntarioFacade() {
        return ejbVoluntarioFacade;
    }
    
    public List<Date> getDaysOfCampanha() {

        System.out.println("\n---------DAYS OF CAMPANHA--------------\n"
                + "DataInicio=" + current.getDataInicio() + "\n"
                + "DataFim=" + current.getDataFim());
        return new ArrayList<Date>(Arrays.asList(current.getDataInicio(), current.getDataFim()));
    }

    public Date getMinRange(Date date) {
        if (date != null) {
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            System.out.println("\n---------------MIN RANGE---------------\n[DATE]=" + date.toString());
        }
        return date;
    }

    public Date getMaxRange(Date date) {
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        System.out.println("\n---------------MAX RANGE---------------\n[DATE]=" + date.toString());

        return date;
    }

    public boolean isCampanha() {
        System.out.println("\n--------------IS CAMPANHA-------------\n");
        Campanha campanha = null;
        System.out.println("\n--------------Data Selected------------\n [DataSelected]=" + selectedDate.getDate());

        try {
            campanha = ejbFacade.findByDateNow();
        } catch (ELException e) {
        }

        if (current == null) {
            current = campanha;
            if (campanha != null) {
                System.out.println("\n---------------CURRENT CAMPANHA------------------\n [Campanha]=" + campanha.toString());
            }
        }

        if (campanha.getCampanhaID() == null) {
            if (first == false) {
                first = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ResourceBundle.getBundle("/resources/Bundle").getString("ThereIsNoCampanha")));
            }
            return false;
        }

        return true;
    }

    public Campanha getSelected() {
        if (current == null) {
            System.out.println("\n---------------CURRENT NULL----------------\n");
            current = new Campanha();
            selectedItemIndex = -1;
        }
        System.out.println("\n----------------GET CURRENT CAMPANHA---------------\n" + current.toString());
        return current;
    }

    private CampanhaFacade getFacade() {
        return ejbFacade;
    }

    public TipoCalendario getTipoCalendario() {
        System.out.println("\n -----------------\nA fazer GET do Tipo Calendario " + tipoCalendario.toString());
        return tipoCalendario;
    }

    public void setTipoCalendario(TipoCalendario tipoCalendario) {
        System.out.println("\n -----------------\nA fazer SET do Tipo Calendario" + tipoCalendario.toString());
        this.tipoCalendario = tipoCalendario;
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

    public String prepareView() {
        current = (Campanha) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Campanha();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.setCampanhaID(0);
            current.setTipoDeCampanha(0);
            System.out.println("" + current.toString());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("CampanhaCreated"));
            return "";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Campanha) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            System.out.println("\n----------EDIT-----------\n" + current.toString());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("CampanhaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Campanha) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("CampanhaDeleted"));
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

    public Campanha getCampanha(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    /**
     * Metodo para guardar o evento
     */
    public void save() {

        //se o ID for zero guardar na base de dados
        //caso contrario nao e necessario
        if (getSelected().getCampanhaID() == null) {

            //uma validacao basica para verificar se o utilizador selecionou 
            //as datas corretamente
            if (getSelected().getDataInicio().getTime() <= getSelected().getDataFim().getTime()) {
                create();
                MailReminder emailReminder = new MailReminder();
                List<Voluntario> voluntarios = getVoluntarioFacade().findAll();
                emailReminder.mandaEmailVoluntarios("Campanha de Recolha", "vai haver uma campanha de recolha de alimentos entre a data de " 
                        + current.getDataInicio().toString() + " e " 
                        + current.getDataFim().toString() + 
                        "." + "<p> Agradecemos a sua participa&ccedil;&atilde;o nesta campanha.</p>"
                        + "<p>Com os melhores cumprimentos, </p>"
                        + "<p>Banco Alimentar contra a fome</p>", voluntarios);
                current = new Campanha();               

                init();

            } else {
                //a data de inicio nao pode ser maior que a data final
                if (getSelected().getDataInicio().getTime() <= getSelected().getDataFim().getTime()) {
                    System.out.println("\n ----------------UPDATE-----------------------\n");
                    update();
                }
            }

        } else {
            update();
            init();
        }
    }

    /**
     * Evento para quando o usuario clica em um espaco em branco no calendario
     *
     * @param selectEvent
     */
    public void addNewCampanha(SelectEvent selectEvent) {

        ScheduleEvent event = new DefaultScheduleEvent("",
                (Date) selectEvent.getObject(), (Date) selectEvent.getObject());

        current = new Campanha();
        //recupero a data em q ele clicou
        current.setDataInicio(event.getStartDate());
        current.setDataFim(event.getEndDate());
    }

    /**
     * Evento para quando usuario clica em um enveto ja existente
     *
     * @param selectEvent
     */
    public void showCampanha(SelectEvent selectEvent) {

        ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

        for (Campanha camp : campanhas) {
            if (camp.getCampanhaID() == (Integer) event.getData()) {
                current = camp;
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

        for (Campanha camp : campanhas) {

            if (camp.getCampanhaID() == (Integer) event.getScheduleEvent().getData()) {
                current = camp;
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

        for (Campanha camp : campanhas) {
            if (camp.getCampanhaID() == (Integer) event.getScheduleEvent().getData()) {
                current = camp;
                //salvar sua agenda no banco
                save();
                break;
            }
        }
    }

    public List<TipoCalendario> getTiposCalendario() {
        return Arrays.asList(TipoCalendario.values());
    }

    @FacesConverter(forClass = Campanha.class)
    public static class CampanhaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CampanhaController controller = (CampanhaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "campanhaController");
            return controller.getCampanha(getKey(value));
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
            if (object instanceof Campanha) {
                Campanha o = (Campanha) object;
                return getStringKey(o.getCampanhaID());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Campanha.class.getName());
            }
        }

    }

}
