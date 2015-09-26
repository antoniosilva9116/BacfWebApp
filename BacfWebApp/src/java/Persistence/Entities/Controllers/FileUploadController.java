/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.Controllers;

import Persistence.Entities.Controllers.util.JsfUtil;
import Persistence.Entities.Correios;
import Persistence.Entities.SessionBeans.CorreiosFacade;
import Persistence.Entities.SessionBeans.VoluntarioFacade;
import Persistence.Entities.Voluntario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Marcos Magalhaes
 */
@ManagedBean(name = "fileUploadController")
public class FileUploadController implements Serializable {

    private UploadedFile uploadedFile;

    private Correios currentCorreios = null;
    @EJB
    private Persistence.Entities.SessionBeans.CorreiosFacade ejbCorreiosFacade;
    private Voluntario currentVoluntario = null;
    @EJB
    private Persistence.Entities.SessionBeans.VoluntarioFacade ejbVoluntarioFacade;

    public CorreiosFacade getEjbCorreiosFacade() {
        return ejbCorreiosFacade;
    }

    public VoluntarioFacade getEjbVoluntarioFacade() {
        return ejbVoluntarioFacade;
    }

    public void setEjbCorreiosFacade(CorreiosFacade ejbCorreiosFacade) {
        this.ejbCorreiosFacade = ejbCorreiosFacade;
    }

    public void setEjbVoluntarioFacade(VoluntarioFacade ejbVoluntarioFacade) {
        this.ejbVoluntarioFacade = ejbVoluntarioFacade;
    }

    public Correios getCurrentCorreios() {
        if(currentCorreios==null){
            currentCorreios = new Correios();
        }
        return currentCorreios;
    }

    public Voluntario getCurrentVoluntario() {
        
        if(currentVoluntario==null){
            currentVoluntario = new Voluntario();
        }
        return currentVoluntario;
    }

    public void setCurrentCorreios(Correios currentCorreios) {
        this.currentCorreios = currentCorreios;
    }

    public void setCurrentVoluntario(Voluntario currentVoluntario) {
        this.currentVoluntario = currentVoluntario;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public FileUploadController() {
    }

    public void TransferFile(String fileName, InputStream in) {

        BufferedReader br = null;
        String line = "";
        String voluntario[] = null;
        String cvsSplitBy = ",";

        BufferedReader in1 = new BufferedReader(new InputStreamReader(in));

        StringBuilder responseData = new StringBuilder();

        try {
            while ((line = in1.readLine()) != null) {
                responseData.append(line);
                voluntario = line.split(",");
                createVol(voluntario);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Done");
    }

    public void createVol(String vol[]) {

        System.out.println(vol);
        
        System.out.println("\n----------------CREATING VOLUNTARIO--------------------\n");
        try {
            getCurrentCorreios().setCorreiosID(0);
            getCurrentCorreios().setDistrito(vol[10]);
            getCurrentCorreios().setConcelho(vol[11]);
            getCurrentCorreios().setLocalidade(vol[12]);
            getCurrentCorreios().setRua(vol[13]);
            getCurrentCorreios().setLocal(vol[14]);
            getCurrentCorreios().setCodPostal(vol[15]);
            getCurrentCorreios().setNomePostal(vol[16]);
            getEjbCorreiosFacade().create(getCurrentCorreios());

            getCurrentVoluntario().setVoluntarioID(0);
            getCurrentVoluntario().setCorreiosID(getCurrentCorreios());
            getCurrentVoluntario().setNome(vol[1]);
            getCurrentVoluntario().setBi(Integer.parseInt(vol[2]));
            getCurrentVoluntario().setTelefoneCasa(Integer.parseInt(vol[3]));
            getCurrentVoluntario().setTelemovel(Integer.parseInt(vol[4]));
            getCurrentVoluntario().setTelefoneTrabalho(Integer.parseInt(vol[5]));
            getCurrentVoluntario().setEmail(vol[6]);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            try {
                date = formatter.parse(vol[7]);
                formatter.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            getCurrentVoluntario().setDataNascimento(date);
            getCurrentVoluntario().setProfissao(vol[8]);
            getCurrentVoluntario().setSexo(vol[9]);
            getEjbVoluntarioFacade().create(currentVoluntario);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("VoluntarioCreated"));
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    

    public String upload() {
        FacesMessage msg = new FacesMessage("Succesful", uploadedFile.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        String extValidate;
        if (getUploadedFile() != null) {
            String ext = getUploadedFile().getFileName();
            if (ext != null) {
                extValidate = ext.substring(ext.indexOf(".") + 1);
                if (extValidate.equals("csv")) {
                    try {
                        TransferFile(getUploadedFile().getFileName(), getUploadedFile().getInputstream());
                    } catch (IOException ex) {
                        Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Wrong", "Error Uploading file..."));
                    }
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Succesful", getUploadedFile().getFileName() + "is uploaded."));
                } else {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Wrong_ext", "only extension .csv"));
                }
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Wrong", "Select File!"));
        }
        return "?faces-redirect=false";
    }
}
