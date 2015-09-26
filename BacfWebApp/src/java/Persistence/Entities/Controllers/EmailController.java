package Persistence.Entities.Controllers;

import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Controllers.util.PaginationHelper;
import Persistence.Entities.Email;
import Persistence.Entities.SessionBeans.EmailFacade;
import Persistence.Entities.SessionBeans.UtilizadorFacade;
import Persistence.Entities.SessionBeans.VoluntarioFacade;
import Persistence.Entities.Utilizador;
import Persistence.Entities.Voluntario;
import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
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
import mail.MailReminder;

@Named("emailController")
@SessionScoped
public class EmailController implements Serializable {

    private Email current;
    private DataModel items = null;
    @EJB
    private Persistence.Entities.SessionBeans.EmailFacade ejbFacade;
    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioFacade ejbVoluntarioFacade;
    @EJB
    private Persistence.Entities.SessionBeans.UtilizadorFacade ejbUtilizadorFacade;

    private PaginationHelper pagination;
    private int selectedItemIndex;
    String emailTo;
    String emailFrom;
    String emailCC;
    String message;
    String subject;

    public EmailController() {
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailCC() {
        return emailCC;
    }

    private VoluntarioFacade getVoluntarioFacade() {
        return ejbVoluntarioFacade;
    }

    private UtilizadorFacade getUtilizadorFacade() {
        return ejbUtilizadorFacade;
    }

    public void setEmailCC(String emailCC) {
        this.emailCC = emailCC;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Email getSelected() {
        if (current == null) {
            current = new Email();
            selectedItemIndex = -1;
        }
        return current;
    }

    private EmailFacade getFacade() {
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
        current = (Email) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Email();
        selectedItemIndex = -1;
        return "Create";
    }

    public void values(ActionEvent e) {
        System.out.println("\n**************************************************************************\n");
        System.out.println("\n---------------------------------A OBTER O ATRIBUTO ----------------------------------------------------\n");

        if (e.getComponent().getAttributes().get("utilizadorRemetente") instanceof Utilizador) {
            System.out.println("\n-------------------------------CAMPANHA-------------------------\n");
            Utilizador utilizador = (Utilizador) e.getComponent().getAttributes().get("utilizadorRemetente");

            getSelected().setUtilizadorRemetenteID(utilizador);
            create();
        }

    }

    public String create() {
        try {
            Utilizador utilizadorDestinario = getUtilizadorFacade().findUtilizadorByEmail(emailTo);
            if (utilizadorDestinario.getUtilizadorID() != null) {
                getSelected().setUtilizadorDestinatarioID(utilizadorDestinario);
                getSelected().setCorpo(message);
                getSelected().setLead(subject);
                getSelected().setDataEnvio(new Date());
                getSelected().setEmailID(0);

                getFacade().create(getSelected());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("EmailCreated"));

                MailReminder mailReminder = new MailReminder();
                mailReminder.mandarEmail(subject, utilizadorDestinario.getUserName(), utilizadorDestinario.getEmail(), message);
            }
            return "";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Email) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("EmailUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Email) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("EmailDeleted"));
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

    public Email getEmail(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Email.class)
    public static class EmailControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmailController controller = (EmailController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "emailController");
            return controller.getEmail(getKey(value));
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
            if (object instanceof Email) {
                Email o = (Email) object;
                return getStringKey(o.getEmailID());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Email.class.getName());
            }
        }

    }

}
