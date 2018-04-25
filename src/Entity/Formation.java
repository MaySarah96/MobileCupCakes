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
public class Formation  {
private Integer idFor;
    private String nomFor;
    private String place;
    private String etatFor;
    private String descriptionFor;
    private Date dateFor;
    private String imageform;
    private Collection<Session> sessionCollection;
    private TypeFormation idTypeFor;
    private Utilisateur idUser;
     private String longitude;
    private String atitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAtitude() {
        return atitude;
    }

    public void setAtitude(String atitude) {
        this.atitude = atitude;
    }
    public Formation() {
    }
     public Formation(String nomFor, String descriptionFor, Date dateFor, String imageform, TypeFormation idTypeFor,String longitude,String atitude) {
        this.nomFor = nomFor;
        this.place = place;
        this.descriptionFor = descriptionFor;
        this.dateFor = dateFor;
        this.imageform = imageform;
        this.idTypeFor = idTypeFor;
        this.longitude=longitude;
        this.atitude=atitude;
    }


    
    public Formation(Integer idFor) {
        this.idFor = idFor;
    }

    public Formation(String nomFor,String place,String etatFor,String descriptionFor, 
            Utilisateur idUser, TypeFormation idTypeFor) {
        this.nomFor = nomFor;
        this.place = place;
        this.etatFor = etatFor;
        this.descriptionFor = descriptionFor;
        this.idUser = idUser;
        this.idTypeFor = idTypeFor;
           }

   

public Formation(int idFor,String nomFor, String place, String etatFor, String descriptionFor, 
            Date dateFor, Utilisateur idUser, TypeFormation idTypeFor) {
        this.idFor=idFor;
        this.nomFor = nomFor;
        this.place = place;
        this.etatFor = etatFor;
        this.descriptionFor = descriptionFor;
        this.dateFor = dateFor;
        this.idUser = idUser;
        this.idTypeFor = idTypeFor;
    }
    public Formation(String nomFor, String place, String etatFor, String descriptionFor, 
            Date dateFor, Utilisateur idUser, TypeFormation idTypeFor) {
        this.nomFor = nomFor;
        this.place = place;
        this.etatFor = etatFor;
        this.descriptionFor = descriptionFor;
        this.dateFor = dateFor;
        this.idUser = idUser;
        this.idTypeFor = idTypeFor;
    }

    public Formation(String nomFor, String place, String descriptionFor, Date dateFor, String imageform, TypeFormation idTypeFor) {
        this.nomFor = nomFor;
        this.place = place;
        this.descriptionFor = descriptionFor;
        this.dateFor = dateFor;
        this.imageform = imageform;
        this.idTypeFor = idTypeFor;
    }

   

    public Formation(Integer idFor, String nomFor) {
        this.idFor = idFor;
        this.nomFor = nomFor;
    }
    
    
    public Integer getIdFor() {
        return idFor;
    }

    public void setIdFor(Integer idFor) {
        this.idFor = idFor;
    }

    public String getNomFor() {
        return nomFor;
    }

    public void setNomFor(String nomFor) {
        this.nomFor = nomFor;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEtatFor() {
        return etatFor;
    }

    public void setEtatFor(String etatFor) {
        this.etatFor = etatFor;
    }

    public String getDescriptionFor() {
        return descriptionFor;
    }

    public void setDescriptionFor(String descriptionFor) {
        this.descriptionFor = descriptionFor;
    }

    public Date getDateFor() {
        return dateFor;
    }

    public void setDateFor(Date dateFor) {
        this.dateFor = dateFor;
    }

    public String getImageform() {
        return imageform;
    }

    public void setImageform(String imageform) {
        this.imageform = imageform;
    }

    public Collection<Session> getSessionCollection() {
        return sessionCollection;
    }

    public void setSessionCollection(Collection<Session> sessionCollection) {
        this.sessionCollection = sessionCollection;
    }

    public TypeFormation getIdTypeFor() {
        return idTypeFor;
    }

    public void setIdTypeFor(TypeFormation idTypeFor) {
        this.idTypeFor = idTypeFor;
    }

    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFor != null ? idFor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.idFor == null && other.idFor != null) || (this.idFor != null && !this.idFor.equals(other.idFor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idFor=" + idFor + ", nomFor=" + nomFor + ", place=" + place + ", etatFor=" + etatFor + ", descriptionFor=" + descriptionFor + ", dateFor=" + dateFor + ", imageform=" + imageform + ", idTypeFor=" + idTypeFor + ", idUser=" + idUser + '}';
    }

}
