package Persistence.Entities.Controllers;

import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Controllers.util.PaginationHelper;
import Persistence.Entities.Equipa;
import Persistence.Entities.Estabelecimento;
import Persistence.Entities.SessionBeans.EquipaFacade;
import Persistence.Entities.SessionBeans.EstabelecimentoFacade;
import Persistence.Entities.SessionBeans.VoluntarioEquipaFacade;
import Persistence.Entities.SessionBeans.VoluntarioFacade;
import Persistence.Entities.Voluntario;
import Persistence.Entities.VoluntarioEquipa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

@Named("equipaController")
@SessionScoped
public class EquipaController implements Serializable {

    private Equipa current;
    private Estabelecimento estabelecimento;
    private DataModel items = null;
    @EJB
    private Persistence.Entities.SessionBeans.EquipaFacade ejbFacade;
    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioFacade ejbVoluntarioFacade;
    @EJB
    private Persistence.Entities.SessionBeans.EstabelecimentoFacade ejbEstabelecimentoFacade;
    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioEquipaFacade ejbVoluntarioEquipaFacade;

    private PaginationHelper pagination;
    private int selectedItemIndex;

    private DualListModel<Voluntario> voluntarios;

    public EquipaController() {
    }

    @PostConstruct
    public void init() {
        //Voluntarios
        List<Voluntario> voluntariosSource = new ArrayList<Voluntario>();
        List<Voluntario> voluntariosTarget = new ArrayList<Voluntario>();

        voluntariosSource = ejbVoluntarioFacade.findAll();
        getAllEstabelecimentos();

        voluntarios = new DualListModel<Voluntario>(voluntariosSource, voluntariosTarget);
        System.out.println("\n--------------INITIALIZE VOLUNTARIOS------------------\n");

    }

    public Equipa getSelected() {
        if (current == null) {
            current = new Equipa();
            selectedItemIndex = -1;
        }
        return current;
    }

    public DualListModel<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(DualListModel<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }

    private VoluntarioFacade getVoluntarioFacade() {
        return ejbVoluntarioFacade;
    }

    private EquipaFacade getFacade() {
        return ejbFacade;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    private EstabelecimentoFacade getEstabelecimentoFacade() {
        return ejbEstabelecimentoFacade;
    }

    private VoluntarioEquipaFacade getVoluntarioEquipaFacade() {
        return ejbVoluntarioEquipaFacade;
    }

    public List<Estabelecimento> getAllEstabelecimentos() {
        List<Estabelecimento> estabelecimentos = getEstabelecimentoFacade().findAll();
        if (estabelecimento == null) {
            estabelecimento = estabelecimentos.get(0);
        }
        return estabelecimentos;
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
        current = (Equipa) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Equipa();
        selectedItemIndex = -1;
        return "Create";
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((Voluntario) item).toString()).append("<br />");
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
            getSelected().setEstabelecimentoID(estabelecimento);
            getSelected().setEquipaID(0);
            getFacade().create(current);

            for (Voluntario voluntario : voluntarios.getTarget()) {
                VoluntarioEquipa voluntarioEquipa = new VoluntarioEquipa(0);

                voluntarioEquipa.setEquipaID(current);
                voluntarioEquipa.setVoluntarioID(voluntario);
                
                getVoluntarioEquipaFacade().create(voluntarioEquipa);
            }

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("EquipaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Equipa) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("EquipaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Equipa) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("EquipaDeleted"));
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

    public Equipa getEquipa(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Equipa.class)
    public static class EquipaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EquipaController controller = (EquipaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "equipaController");
            return controller.getEquipa(getKey(value));
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
            if (object instanceof Equipa) {
                Equipa o = (Equipa) object;
                return getStringKey(o.getEquipaID());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Equipa.class.getName());
            }
        }

    }

}
