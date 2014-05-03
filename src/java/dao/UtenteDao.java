/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Utenti;

/**
 *
 * @author alberto.reali
 */
public interface UtenteDao {
    public Utenti findbyUtente(Utenti utenti);
    public Utenti Login(Utenti utenti);
    public List<Utenti> findAll();
    public boolean create (Utenti utente);
    public boolean update (Utenti utente);
    public boolean delete (String id);
}