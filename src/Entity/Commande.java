/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author escobar
 */
public class Commande {

    private Integer idCmd;
    private Date dateCmd;
    private Double montantCmd;
    private Date dateLivCmd;
    private String addLiv;
    private String etatLivCmd;
    private String etatCmd;
    private Utilisateur idUser;
    private Collection<LineCmd> lineCmdCollection;
    private Collection<FeedBack> feedBackCollection;

    public Commande() {
    }

    public Commande(Integer idCmd) {
        this.idCmd = idCmd;
    }

    public Commande(Date dateCmd, Double montantCmd, Date dateLivCmd, String addLiv, String etatLivCmd, String etatCmd) {
        this.dateCmd = dateCmd;
        this.montantCmd = montantCmd;
        this.dateLivCmd = dateLivCmd;
        this.addLiv = addLiv;
        this.etatLivCmd = etatLivCmd;
        this.etatCmd = etatCmd;
    }

    public Commande(Integer idCmd, Date dateCmd, Double montantCmd, Date dateLivCmd, String addLiv) {
        this.idCmd = idCmd;
        this.dateCmd = dateCmd;
        this.montantCmd = montantCmd;
        this.dateLivCmd = dateLivCmd;
        this.addLiv = addLiv;
    }

    public Commande(Integer idCmd, String etatLivCmd) {
        this.idCmd = idCmd;
        this.etatLivCmd = etatLivCmd;
    }

   

   

    public Commande(Integer idCmd, Date dateCmd,Double Montant ,Date dateLivCmd, String addLiv, String etatLivCmd, Utilisateur idUser) {
        this.idCmd = idCmd;
        this.dateCmd = dateCmd;
        this.montantCmd=Montant;
        this.dateLivCmd = dateLivCmd;
        this.addLiv = addLiv;
        this.etatLivCmd = etatLivCmd;
        this.idUser = idUser;
    }

    public Commande(Integer idCmd, Date dateCmd,Double Montant , Date dateLivCmd, String addLiv, String etatLivCmd,  Utilisateur idUser, Collection<LineCmd> lineCmdCollection) {
        this.idCmd = idCmd;
        this.dateCmd = dateCmd;
        this.montantCmd=Montant;
        this.dateLivCmd = dateLivCmd;
        this.addLiv = addLiv;
        this.etatLivCmd = etatLivCmd;
        this.idUser = idUser;
        this.lineCmdCollection = lineCmdCollection;
    }

    
    public Integer getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(Integer idCmd) {
        this.idCmd = idCmd;
    }

    public Date getDateCmd() {
        return dateCmd;
    }

    public void setDateCmd(Date dateCmd) {
        this.dateCmd = dateCmd;
    }

    public Double getMontantCmd() {
        return montantCmd;
    }

    public void setMontantCmd(Double montantCmd) {
        this.montantCmd = montantCmd;
    }

    public Date getDateLivCmd() {
        return dateLivCmd;
    }

    public void setDateLivCmd(Date dateLivCmd) {
        this.dateLivCmd = dateLivCmd;
    }

    public String getAddLiv() {
        return addLiv;
    }

    public void setAddLiv(String addLiv) {
        this.addLiv = addLiv;
    }

    public String getEtatLivCmd() {
        return etatLivCmd;
    }

    public void setEtatLivCmd(String etatLivCmd) {
        this.etatLivCmd = etatLivCmd;
    }

    public String getEtatCmd() {
        return etatCmd;
    }

    public void setEtatCmd(String etatCmd) {
        this.etatCmd = etatCmd;
    }

    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    public Collection<LineCmd> getLineCmdCollection() {
        return lineCmdCollection;
    }

    public void setLineCmdCollection(Collection<LineCmd> lineCmdCollection) {
        this.lineCmdCollection = lineCmdCollection;
    }

    public Collection<FeedBack> getFeedBackCollection() {
        return feedBackCollection;
    }

    public void setFeedBackCollection(Collection<FeedBack> feedBackCollection) {
        this.feedBackCollection = feedBackCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCmd != null ? idCmd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.idCmd == null && other.idCmd != null) || (this.idCmd != null && !this.idCmd.equals(other.idCmd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCmd=" + idCmd + ", dateCmd=" + dateCmd + ", dateLivCmd=" + dateLivCmd + ", addLiv=" + addLiv + ", etatLivCmd=" + etatLivCmd + ", idUser=" + idUser + ", lineCmdCollection=" + lineCmdCollection + '}';
    }


    
}
