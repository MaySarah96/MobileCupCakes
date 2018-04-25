/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 *
 * @author escobar
 */
public class FeedBack  {

    private Integer idFeed;
    private String sujet;
    private String description;
    private Commande idCmd;

    public FeedBack(String sujet, String description, Commande idCmd) {
        this.sujet = sujet;
        this.description = description;
        this.idCmd = idCmd;
    }

    public FeedBack(String sujet, String description) {
        this.sujet = sujet;
        this.description = description;
    }

    public FeedBack(Integer idFeed, String sujet, String description, Commande idCmd) {
        this.idFeed = idFeed;
        this.sujet = sujet;
        this.description = description;
        this.idCmd = idCmd;
    }

    public FeedBack() {
    }

    public FeedBack(Integer idFeed) {
        this.idFeed = idFeed;
    }

    public Integer getIdFeed() {
        return idFeed;
    }

    public void setIdFeed(Integer idFeed) {
        this.idFeed = idFeed;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Commande getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(Commande idCmd) {
        this.idCmd = idCmd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFeed != null ? idFeed.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeedBack)) {
            return false;
        }
        FeedBack other = (FeedBack) object;
        if ((this.idFeed == null && other.idFeed != null) || (this.idFeed != null && !this.idFeed.equals(other.idFeed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FeedBack{" + "idFeed=" + idFeed + ", sujet=" + sujet + ", description=" + description + ", idCmd=" + idCmd + '}';
    }

    
}
