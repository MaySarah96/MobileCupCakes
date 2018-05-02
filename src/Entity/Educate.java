/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author escobar
 */
public class Educate {
    private String dateIscri;
    private String etatEduc;
    private String etatNotif;
    private Session session;
    private Utilisateur utilisateur;

    public Educate() {
    }
    public Educate(Session idSes) {
        this.session=idSes;
    }

    public String getEtatNotif() {
        return etatNotif;
    }

    public void setEtatNotif(String etatNotif) {
        this.etatNotif = etatNotif;
    }

    public Educate(String dateIscri, String etatEduc) {
        this.dateIscri = dateIscri;
        this.etatEduc = etatEduc;
    }

    


    public String getDateIscri() {
        return dateIscri;
    }

    public void setDateIscri(String dateIscri) {
        this.dateIscri = dateIscri;
    }

    public String getEtatEduc() {
        return etatEduc;
    }

    public void setEtatEduc(String etatEduc) {
        this.etatEduc = etatEduc;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    
}
