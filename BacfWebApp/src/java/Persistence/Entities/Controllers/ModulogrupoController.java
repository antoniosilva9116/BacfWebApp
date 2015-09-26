package Persistence.Entities.Controllers;

import Persistence.Entities.Modulogrupo;
import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Controllers.util.PaginationHelper;
import Persistence.Entities.SessionBeans.ModulogrupoFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("modulogrupoController")
@SessionScoped
public class ModulogrupoController implements Serializable {

    private Modulogrupo current;
    private DataModel items = null;
    @EJB
    private Persistence.Entities.SessionBeans.ModulogrupoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ModulogrupoController() {
    }

    public Modulogrupo getSelected() {
        if (current == null) {
            current = new Modulogrupo();
            current.setModulogrupoPK(new Persistence.Entities.ModulogrupoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ModulogrupoFacade getFacade() {
        return ejbFacade;
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
        current = (Modulogrupo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Modulogrupo();
        current.setModulogrupoPK(new Persistence.Entities.ModulogrupoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getModulogrupoPK().setModuloID(current.getModulo().getModuloID());
            current.getModulogrupoPK().setGrupoID(current.getGrupo().getGrupoID());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("ModulogrupoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Modulogrupo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getModulogrupoPK().setModuloID(current.getModulo().getModuloID());
            current.getModulogrupoPK().setGrupoID(current.getGrupo().getGrupoID());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("ModulogrupoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Modulogrupo) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("ModulogrupoDeleted"));
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

    public Modulogrupo getModulogrupo(Persistence.Entities.ModulogrupoPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Modulogrupo.class)
    public static class ModulogrupoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ModulogrupoController controller = (ModulogrupoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "modulogrupoController");
            return controller.getModulogrupo(getKey(value));
        }

        Persistence.Entities.ModulogrupoPK getKey(String value) {
            Persistence.Entities.ModulogrupoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Persistence.Entities.ModulogrupoPK();
            key.setModuloGrupoID(Integer.parseInt(values[0]));
            key.setGrupoID(Integer.parseInt(values[1]));
            key.setModuloID(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(Persistence.Entities.ModulogrupoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getModuloGrupoID());
            sb.append(SEPARATOR);
            sb.append(value.getGrupoID());
            sb.append(SEPARATOR);
            sb.append(value.getModuloID());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Modulogrupo) {
                Modulogrupo o = (Modulogrupo) object;
                return getStringKey(o.getModulogrupoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Modulogrupo.class.getName());
            }
        }

    }

}
