/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import dao.UtenteDao;
import dao.UtenteDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import model.Utenti;


@ManagedBean
@RequestScoped
public class utentiBean {

    private List<Utenti> listaUtenti;  
    private Utenti selectedUtenti; 
    private DataModel nuovaLista;
  
    public utentiBean() {
        this.listaUtenti = new ArrayList<Utenti>();
    }

    public List<Utenti> getListaUtenti() {
        UtenteDao utenteDao = new UtenteDaoImpl();
        this.listaUtenti = utenteDao.findAll();
        return listaUtenti;
    }

    public Utenti getSelectedUtenti() {
        if(this.selectedUtenti == null) {
            this.selectedUtenti = new Utenti();
        }
            
        return selectedUtenti;
    }

    public void setSelectedUtenti(Utenti selectedUtenti) {
        this.selectedUtenti = selectedUtenti;
    }
    
    public void nuovoUtente(ActionEvent actionEvent) {
        this.selectedUtenti = new Utenti();
    }

    public void btnCreteUtente(ActionEvent actionEvent) {
        UtenteDao utenteDao = new UtenteDaoImpl();
        String msg;
        if (utenteDao.create(this.selectedUtenti)) {
            msg = "Utente creato";
        } else {
            msg = "Utente non creato";
        }
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void btnUpdateUtente(ActionEvent actionEvent) {
        UtenteDao utenteDao = new UtenteDaoImpl();
        String msg;
        if (utenteDao.update(this.selectedUtenti)) {
            msg = "Utente aggiornato";
        } else {
            msg = "Utente non aggiornato";
        }
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);        
    }

    public void btnDeleteUtente(ActionEvent actionEvent) {
        UtenteDao utenteDao = new UtenteDaoImpl();
        String msg;
        if (utenteDao.delete(this.selectedUtenti.getUserId())) {
            msg = "Utente cancellato";
        } else {
            msg = "Utente non cancellato";
        }
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);        
    }
    
}
