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
public class Recette  {

    private Integer idRec;
    private String nomRec;
    private Date dateRec;
    private String etatRec;
    private String descriptionRec;
    private String imageRec;
    private Collection<Note> noteCollection;
    private CategorieRec idCatRec;
    private Utilisateur idUser;

    public Recette() {
    }

    public Recette(Integer idRec) {
        this.idRec = idRec;
    }

    public Recette(String nomRec, String descriptionRec, String imageRec, CategorieRec idCatRec, Utilisateur idUser) {
        this.nomRec = nomRec;
        this.descriptionRec = descriptionRec;
        this.imageRec = imageRec;
        this.idCatRec = idCatRec;
        this.idUser = idUser;
    }
    
    public Recette(Integer idRec, String nomRec, String etatRec, String descriptionRec, String imageRec, CategorieRec idCatRec, Utilisateur idUser) {
        this.idRec = idRec;
        this.nomRec = nomRec;
        this.etatRec = etatRec;
        this.descriptionRec = descriptionRec;
        this.imageRec = imageRec;
        this.idCatRec = idCatRec;
        this.idUser = idUser;
    }

    public Recette(Integer idRec, String nomRec, Date dateRec, String etatRec, String descriptionRec, String imageRec, Collection<Note> noteCollection, CategorieRec idCatRec, Utilisateur idUser) {
        this.idRec = idRec;
        this.nomRec = nomRec;
        this.dateRec = dateRec;
        this.etatRec = etatRec;
        this.descriptionRec = descriptionRec;
        this.imageRec = imageRec;
        this.noteCollection = noteCollection;
        this.idCatRec = idCatRec;
        this.idUser = idUser;
    }

    
    
    public Integer getIdRec() {
        return idRec;
    }

    public void setIdRec(Integer idRec) {
        this.idRec = idRec;
    }

    public String getNomRec() {
        return nomRec;
    }

    public void setNomRec(String nomRec) {
        this.nomRec = nomRec;
    }

    public Date getDateRec() {
        return dateRec;
    }

    public void setDateRec(Date dateRec) {
        this.dateRec = dateRec;
    }

    public String getEtatRec() {
        return etatRec;
    }

    public void setEtatRec(String etatRec) {
        this.etatRec = etatRec;
    }

    public String getDescriptionRec() {
        return descriptionRec;
    }

    public void setDescriptionRec(String descriptionRec) {
        this.descriptionRec = descriptionRec;
    }

    public String getImageRec() {
        return imageRec;
    }

    public void setImageRec(String imageRec) {
        this.imageRec = imageRec;
    }

    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    public CategorieRec getIdCatRec() {
        return idCatRec;
    }

    public void setIdCatRec(CategorieRec idCatRec) {
        this.idCatRec = idCatRec;
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
        hash += (idRec != null ? idRec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recette)) {
            return false;
        }
        Recette other = (Recette) object;
        if ((this.idRec == null && other.idRec != null) || (this.idRec != null && !this.idRec.equals(other.idRec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Recette{" + "idRec=" + idRec + ", nomRec=" + nomRec + ", dateRec=" + dateRec + ", etatRec=" + etatRec + ", descriptionRec=" + descriptionRec + ", imageRec=" + imageRec + ", idCatRec=" + idCatRec + ", idUser=" + idUser + '}';
    }

    
    
}
