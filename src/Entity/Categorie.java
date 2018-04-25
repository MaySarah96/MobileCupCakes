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
public class Categorie {

    private Integer idCat;
    private String nomCat;
    private Collection<Produit> produitCollection;

    public Categorie() {
    }

    public Categorie(String nomCat) {
        this.nomCat = nomCat;
    }

    public Categorie(Integer idCat, String nomCat) {
        this.idCat = idCat;
        this.nomCat = nomCat;
    }

    public Categorie(Integer idCat) {
        this.idCat = idCat;
    }

    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCat != null ? idCat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.idCat == null && other.idCat != null) || (this.idCat != null && !this.idCat.equals(other.idCat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomCat;
    }
    
}
