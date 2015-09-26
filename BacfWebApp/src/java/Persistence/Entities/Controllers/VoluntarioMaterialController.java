package Persistence.Entities.Controllers;

import Persistence.Entities.VoluntarioMaterial;
import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Controllers.util.PaginationHelper;
import Persistence.Entities.SessionBeans.VoluntarioMaterialFacade;

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

@Named("voluntarioMaterialController")
@SessionScoped
public class VoluntarioMaterialController implements Serializable {

    private VoluntarioMaterial current;
    private DataModel items = null;
    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioMaterialFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public VoluntarioMaterialController() {
    }

    public VoluntarioMaterial getSelected() {
        if (current == null) {
            current = new VoluntarioMaterial();
            current.setVoluntarioMaterialPK(new Persistence.Entities.VoluntarioMaterialPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private VoluntarioMaterialFacade getFacade() {
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
        current = (VoluntarioMaterial) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new VoluntarioMaterial();
        current.setVoluntarioMaterialPK(new Persistence.Entities.VoluntarioMaterialPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getVoluntarioMaterialPK().setRelogioPontoID(current.getRelogioponto().getRelogioPontoID());
            current.getVoluntarioMaterialPK().setMaterialID(current.getMaterial().getMaterialID());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("VoluntarioMaterialCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (VoluntarioMaterial) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getVoluntarioMaterialPK().setRelogioPontoID(current.getRelogioponto().getRelogioPontoID());
            current.getVoluntarioMaterialPK().setMaterialID(current.getMaterial().getMaterialID());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("VoluntarioMaterialUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (VoluntarioMaterial) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("VoluntarioMaterialDeleted"));
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

    public VoluntarioMaterial getVoluntarioMaterial(Persistence.Entities.VoluntarioMaterialPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = VoluntarioMaterial.class)
    public static class VoluntarioMaterialControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VoluntarioMaterialController controller = (VoluntarioMaterialController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "voluntarioMaterialController");
            return controller.getVoluntarioMaterial(getKey(value));
        }

        Persistence.Entities.VoluntarioMaterialPK getKey(String value) {
            Persistence.Entities.VoluntarioMaterialPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Persistence.Entities.VoluntarioMaterialPK();
            key.setVoluntarioMaterialID(Integer.parseInt(values[0]));
            key.setRelogioPontoID(Integer.parseInt(values[1]));
            key.setMaterialID(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(Persistence.Entities.VoluntarioMaterialPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getVoluntarioMaterialID());
            sb.append(SEPARATOR);
            sb.append(value.getRelogioPontoID());
            sb.append(SEPARATOR);
            sb.append(value.getMaterialID());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof VoluntarioMaterial) {
                VoluntarioMaterial o = (VoluntarioMaterial) object;
                return getStringKey(o.getVoluntarioMaterialPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + VoluntarioMaterial.class.getName());
            }
        }

    }

}
