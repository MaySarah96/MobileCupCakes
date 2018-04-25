/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Collection;
/**
 *
 * @author escobar
 */
public class CategorieRec {

    private Integer idCatRec;
    private String nomCatRec;
    private Collection<Recette> recetteCollection;

    public CategorieRec() {
    }

    public CategorieRec(String nomCatRec, Collection<Recette> recetteCollection) {
        this.nomCatRec = nomCatRec;
        this.recetteCollection = recetteCollection;
    }

    public CategorieRec(Integer idCatRec, String nomCatRec, Collection<Recette> recetteCollection) {
        this.idCatRec = idCatRec;
        this.nomCatRec = nomCatRec;
        this.recetteCollection = recetteCollection;
    }

    public CategorieRec(String nomCatRec) {
        this.nomCatRec = nomCatRec;
    }

    public CategorieRec(Integer idCatRec, String nomCatRec) {
        this.idCatRec = idCatRec;
        this.nomCatRec = nomCatRec;
    }

    
    
    public Integer getIdCatRec() {
        return idCatRec;
    }

    public void setIdCatRec(Integer idCatRec) {
        this.idCatRec = idCatRec;
    }

    public String getNomCatRec() {
        return nomCatRec;
    }

    public void setNomCatRec(String nomCatRec) {
        this.nomCatRec = nomCatRec;
    }

    public Collection<Recette> getRecetteCollection() {
        return recetteCollection;
    }

    public void setRecetteCollection(Collection<Recette> recetteCollection) {
        this.recetteCollection = recetteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatRec != null ? idCatRec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategorieRec)) {
            return false;
        }
        CategorieRec other = (CategorieRec) object;
        if ((this.idCatRec == null && other.idCatRec != null) || (this.idCatRec != null && !this.idCatRec.equals(other.idCatRec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CategorieRec{" + "idCatRec=" + idCatRec + ", nomCatRec=" + nomCatRec + '}';
    }

    
    
}
