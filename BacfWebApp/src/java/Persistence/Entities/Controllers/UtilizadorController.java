package Persistence.Entities.Controllers;

import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Controllers.util.PaginationHelper;
import Persistence.Entities.*;
import Persistence.Entities.SessionBeans.CorreiosFacade;
import Persistence.Entities.SessionBeans.DisponibilidadecampanhasFacade;
import Persistence.Entities.SessionBeans.UtilizadorFacade;
import Persistence.Entities.SessionBeans.VoluntarioFacade;
import Persistence.Entities.Utilizador;
import Persistence.Entities.Voluntario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;
import javax.servlet.http.HttpSession;

@Named("utilizadorController")
@SessionScoped
public class UtilizadorController implements Serializable {

    private Utilizador current;
    private DataModel items = null;
    @EJB
    private Persistence.Entities.SessionBeans.UtilizadorFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private Correios correios;
    @EJB
    private Persistence.Entities.SessionBeans.CorreiosFacade ejbCorreiosFacade;

    private Voluntario voluntario;
    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioFacade ejbVoluntarioFacade;

    private Disponibilidadecampanhas disponibilidadecampanhas;
    @EJB
    private Persistence.Entities.SessionBeans.DisponibilidadecampanhasFacade ejbDisponibilidadecampanhasFacade;

    public UtilizadorController() {
    }

    public Utilizador getSelected() {
        if (current == null) {
            current = new Utilizador();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UtilizadorFacade getFacade() {
        return ejbFacade;
    }

    public Utilizador getUser() {
        return current;
    }

    public void setUser(Utilizador utilizador) {
        current = utilizador;
    }

    public Correios getCorreios() {
        if (correios == null) {
            correios = new Correios();
        }
        return correios;
    }

    public Voluntario getVoluntario() {
        if (voluntario == null) {
            voluntario = new Voluntario();
        }
        return voluntario;
    }

    public Disponibilidadecampanhas getDisponibilidadecampanhas() {
        if (disponibilidadecampanhas == null) {
            disponibilidadecampanhas = new Disponibilidadecampanhas();
        }

        return disponibilidadecampanhas;
    }

    private CorreiosFacade getEjbCorreiosFacade() {
        return ejbCorreiosFacade;
    }

    private VoluntarioFacade getEjbVoluntarioFacade() {
        return ejbVoluntarioFacade;
    }

    private DisponibilidadecampanhasFacade getEjbDisponibilidadecampanhasFacade() {
        return ejbDisponibilidadecampanhasFacade;
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
        current = (Utilizador) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Utilizador();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            Grupo grupo = new Grupo(1);
            current.setGrupoID(grupo);
            getFacade().create(current);

            getCorreios().setCodPostal(null);
            getCorreios().setConcelho(null);
            getCorreios().setCorreiosID(0);
            getCorreios().setDistrito(null);
            getCorreios().setLocal(null);
            getCorreios().setLocalidade(null);
            getCorreios().setNomePostal(null);
            getCorreios().setRua(null);
            getEjbCorreiosFacade().create(correios);

            getVoluntario().setBi(null);
            getVoluntario().setCorreiosID(correios);
            getVoluntario().setDataNascimento(null);
            getVoluntario().setEmail(null);
            getVoluntario().setNome(null);
            getVoluntario().setProfissao(null);
            getVoluntario().setSexo(null);
            getVoluntario().setTelefoneCasa(null);
            getVoluntario().setTelefoneTrabalho(null);
            getVoluntario().setTelemovel(null);
            getVoluntario().setUtilizadorID(current);
            getVoluntario().setVoluntarioID(0);
            System.out.println("" + voluntario.toString());
            getEjbVoluntarioFacade().create(voluntario);

            getDisponibilidadecampanhas().setDisponibilidadeCampanhasID(0);
            getDisponibilidadecampanhas().setDomingo(3);
            getDisponibilidadecampanhas().setQuarta(3);
            getDisponibilidadecampanhas().setQuinta(3);
            getDisponibilidadecampanhas().setSabado(3);
            getDisponibilidadecampanhas().setSegunda(3);
            getDisponibilidadecampanhas().setSexta(3);
            getDisponibilidadecampanhas().setTerca(3);
            getDisponibilidadecampanhas().setVoluntarioVoluntarioID(voluntario);
            getEjbDisponibilidadecampanhasFacade().create(disponibilidadecampanhas);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("UtilizadorCreated"));
            return "voluntario";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Utilizador) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("UtilizadorUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Utilizador) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("UtilizadorDeleted"));
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

    public Utilizador getUtilizador(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public String login() {

        try {
            Utilizador userAux = ejbFacade.findUtilizadorByEmail(current.getEmail());
            if (userAux != null) {
                if (current.getPassword().equals(userAux.getPassword())) {
                    current = userAux;
                } else {
                    throw new Exception("");
                }
            }
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("EmailIncorrect"));
        } catch (NoResultException ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("EmailIncorrect"));

        } catch (NonUniqueResultException ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("EmailIncorrect"));

        } catch (IllegalStateException ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("EmailIncorrect"));

        } catch (QueryTimeoutException ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("EmailIncorrect"));

        } catch (PersistenceException ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("EmailIncorrect"));
        } catch (NullPointerException ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("EmailIncorrect"));
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("EmailIncorrect"));
        }

        try {
            if (current != null) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            } else {
                throw new Exception("");
            }
        } catch (EJBException e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PasswordIncorrect"));
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PasswordIncorrect"));
        }

        if (current != null && current.getGrupoID() != null) {
            if (current.getGrupoID().getGrupoID() == 1) {
                System.out.println("\n-------REDIRECT TO VOLUNTARIO----------\n");
                return "voluntario";
            }

            if (current.getGrupoID().getGrupoID() == 2) {
                System.out.println("\n-------REDIRECT TO ADMINISTRADOR----------\n");
                return "administrador";
            }

            if (current.getGrupoID().getGrupoID() == 3) {
                System.out.println("\n-------REDIRECT TO CHEFEEQUIPA----------\n");
                return "chefeequipa";
            }
        }

        return null;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }

        current = null;

        return "signin";
    }

    public void validateSession() throws IOException {

        if (current == null || current.getUserName() == null) {

            FacesContext.getCurrentInstance().getExternalContext().redirect("../account/signin.xhtml?faces-redirect=true");
        }
    }

    @FacesConverter(forClass = Utilizador.class)
    public static class UtilizadorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UtilizadorController controller = (UtilizadorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "utilizadorController");
            return controller.getUtilizador(getKey(value));
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
            if (object instanceof Utilizador) {
                Utilizador o = (Utilizador) object;
                return getStringKey(o.getUtilizadorID());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Utilizador.class.getName());
            }
        }

    }

}
