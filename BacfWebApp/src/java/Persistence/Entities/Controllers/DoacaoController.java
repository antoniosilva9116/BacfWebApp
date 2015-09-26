package Persistence.Entities.Controllers;

import Persistence.Entities.Doacao;
import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Controllers.util.PaginationHelper;
import Persistence.Entities.Itemdoacao;

import Persistence.Entities.SessionBeans.DoacaoFacade;
import Persistence.Entities.SessionBeans.ItemdoacaoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

@Named("doacaoController")
@SessionScoped
public class DoacaoController implements Serializable {

    private ArrayList<ItemDoacao> orderList;
    private ItemDoacao currentItem;

    private Doacao current;
    private DataModel items = null;
    @EJB
    private Persistence.Entities.SessionBeans.DoacaoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private List<Doacao> selectedDoacoes;

    private Itemdoacao itemdoar;
    @EJB
    private Persistence.Entities.SessionBeans.ItemdoacaoFacade ejbItemdoacaoFacade;

    public DoacaoController() {
    }

    public ArrayList<ItemDoacao> getOrderList() {
        if (orderList == null) {
            orderList = new ArrayList<ItemDoacao>();
            return orderList;
        }
        System.out.println("Size: " + orderList.size());
        return orderList;
    }

    public ItemDoacao getCurrentItem() {
        if (currentItem != null) {
            return currentItem;
        } else {
            currentItem = new ItemDoacao();
            return currentItem;
        }
    }

    public void setCurrentItem(ItemDoacao currentItem) {
        this.currentItem = currentItem;
    }

    public void addAction() {
        if (orderList == null) {
            orderList = new ArrayList<ItemDoacao>();
        }
        ItemDoacao doacao = new ItemDoacao(currentItem.getDescricao(), currentItem.getQuantidade(), currentItem.getTipoUnidade());
        orderList.add(doacao);
    }

    public void deleteAction(ItemDoacao order) {
        orderList.remove(order);
    }

    public Doacao getSelected() {
        if (current == null) {
            current = new Doacao();
            selectedItemIndex = -1;
        }
        return current;
    }

    public void setSelected(Doacao doacao) {
        current = doacao;
    }

    public List<Doacao> getSelectedDoacoes() {
        return selectedDoacoes;
    }

    public void setSelectedDoacoes(List<Doacao> selectedDoacoes) {
        this.selectedDoacoes = selectedDoacoes;
    }

    public List<Doacao> getAll() {
        List<Doacao> doacoes = new ArrayList<>(getFacade().findAll());

        return doacoes;
    }

    private DoacaoFacade getFacade() {
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
        current = (Doacao) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Doacao();
        selectedItemIndex = -1;
        return "Create";
    }

    public Itemdoacao getItemdoar() {
        if (itemdoar == null) {
            itemdoar = new Itemdoacao();
        }
        return itemdoar;
    }

    public ItemdoacaoFacade getEjbItemdoacaoFacade() {
        return ejbItemdoacaoFacade;
    }

    public String create() {
        try {
            Date now = new Date();
            current.setDoacaoID(0);
            current.setDataDoacao(now);
            System.out.println(current);
            getFacade().create(current);

            System.out.println(orderList.size());
            for (ItemDoacao i : orderList) {
                getItemdoar().setItemDoacao(0);
                getItemdoar().setDoacaoDoacaoID(current);
                getItemdoar().setDescricao(i.getDescricao());
                getItemdoar().setQuantidade(Double.parseDouble(i.getQuantidade()));
                getItemdoar().setTipoUnidade(i.getTipoUnidade());
                getEjbItemdoacaoFacade().create(getItemdoar());

                itemdoar = null;
            }
            //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DoacaoCreated"));
            return "agradecimentos";
        } catch (Exception e) {

            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Doacao) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {

            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DoacaoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Doacao) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DoacaoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
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

    public void clearData() {
        HttpSession session = null;

        try {
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        } catch (FacesException e) {
        }
        if (session != null) {
            session.invalidate();
        }

        current = null;
        currentItem = null;
        if (orderList != null) {
            orderList.clear();
        }
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

    public Doacao getDoacao(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Doacao.class)
    public static class DoacaoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DoacaoController controller = (DoacaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "doacaoController");
            return controller.getDoacao(getKey(value));
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
            if (object instanceof Doacao) {
                Doacao o = (Doacao) object;
                return getStringKey(o.getDoacaoID());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Doacao.class.getName());
            }
        }
    }

    public class ItemDoacao implements Serializable {

        private String descricao;
        private String quantidade;
        private String tipoUnidade;

        public ItemDoacao() {
        }

        public ItemDoacao(String des, String quan, String tipo) {
            this.descricao = des;
            this.quantidade = quan;
            this.tipoUnidade = tipo;
        }

        public String getDescricao() {
            return descricao;
        }

        public String getQuantidade() {
            return quantidade;
        }

        public String getTipoUnidade() {
            return tipoUnidade;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public void setQuantidade(String quantidade) {
            this.quantidade = quantidade;
        }

        public void setTipoUnidade(String tipoUnidade) {
            this.tipoUnidade = tipoUnidade;
        }
    }

}
