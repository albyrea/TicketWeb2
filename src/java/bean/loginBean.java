/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UtenteDao;
import dao.UtenteDaoImpl;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Utenti;
import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author alberto.reali
 */
@ManagedBean
@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable {

    private Utenti utente;
    private UtenteDao utenteDao;

    public loginBean() {
        this.utenteDao = new UtenteDaoImpl();

        if (this.utente == null) {
            this.utente = new Utenti();
        }
    }

    public Utenti getUtente() {
        return utente;
    }

    public void setUtente(Utenti utente) {
        this.utente = utente;
    }

    public void login(ActionEvent actionEvent) {

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        boolean loggedIn;
        String percorso = "";

        this.utente = this.utenteDao.Login(this.utente);

        if (this.utente != null) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("utente", this.utente.getUserId());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Benvenuto", this.utente.getFirstName());
            percorso = MyUtil.basepathlogin() + "views/inizio.xhtml";
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");
            if (this.utente == null) {
                this.utente = new Utenti();
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("percorso", percorso);
    }

    public void logout() {
        String percorso = MyUtil.basepathlogin() + "login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext faceContext = FacesContext.getCurrentInstance();

        HttpSession session = (HttpSession) faceContext.getExternalContext().getSession(false);
        session.invalidate();

        context.addCallbackParam("loggedOut", true);
        context.addCallbackParam("percorso", percorso);

    }
}
