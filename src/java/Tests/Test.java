/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tests;

import dao.UtenteDao;
import dao.UtenteDaoImpl;
import model.Utenti;

/**
 *
 * @author alberto.reali
 */
public class Test {
     
    public static void main(String[] args) {
        UtenteDao utenteDao = new UtenteDaoImpl();
        Utenti utente = new Utenti();
        utente.setUserId("LOLA");
        utente.setUserPwd("LOLA");
        utente.setFirstName("DANIELA");
        utente.setLastName("AMADIO");
        
        utenteDao.create(utente);
        
    }
}
