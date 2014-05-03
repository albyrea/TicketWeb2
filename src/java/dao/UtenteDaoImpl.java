/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Utenti;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author alberto.reali
 */
public class UtenteDaoImpl implements UtenteDao {

    @Override
    public Utenti findbyUtente(Utenti utenti) {
        Utenti model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Utenti WHERE userId = '"+utenti.getUserId()+"'";
        try {
            sesion.beginTransaction();
            model = (Utenti) sesion.createQuery(sql).uniqueResult();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }
        return model;    
    }

    @Override
    public Utenti Login(Utenti utenti) {
        Utenti model = this.findbyUtente(utenti);
        if (model != null) {
            if (!utenti.getUserPwd().equals(model.getUserPwd())) {
                model = null;
            }
        }
        return model; 
    }

    @Override
    public List<Utenti> findAll() {
        List<Utenti> listato = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Utenti";
        try {
            sesion.beginTransaction();
            listato = sesion.createQuery(sql).list();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }
        return listato;    
    }

    @Override
    public boolean create(Utenti utente) {
        Boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(utente);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;    
    }

    @Override
    public boolean update(Utenti utente) {
        Boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.update(utente);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;    
    }

    @Override
    public boolean delete(String id) {
        Boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Utenti utente = (Utenti) sesion.load(Utenti.class, id);
            sesion.delete(utente);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }   
}
