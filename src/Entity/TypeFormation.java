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
public class TypeFormation {

  
    private Integer idTypeFor;
    private String nomTypeFor;
    private Collection<Formation> formationCollection;

    public TypeFormation() {
    }

    public TypeFormation(int idTypeFor) {
        this.idTypeFor = idTypeFor;
    }

    public TypeFormation(int idTypeFor, String nomTypeFor) {
      this.idTypeFor = idTypeFor;
      this.nomTypeFor = nomTypeFor;
    }

    public TypeFormation( String nomTypeFor) {
 this.nomTypeFor = nomTypeFor;    }

    public Integer getIdTypeFor() {
        return idTypeFor;
    }

    public void setIdTypeFor(Integer idTypeFor) {
        this.idTypeFor = idTypeFor;
    }

    public String getNomTypeFor() {
        return nomTypeFor;
    }

    public void setNomTypeFor(String nomTypeFor) {
        this.nomTypeFor = nomTypeFor;
    }

    public Collection<Formation> getFormationCollection() {
        return formationCollection;
    }

    public void setFormationCollection(Collection<Formation> formationCollection) {
        this.formationCollection = formationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeFor != null ? idTypeFor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeFormation)) {
            return false;
        }
        TypeFormation other = (TypeFormation) object;
        if ((this.idTypeFor == null && other.idTypeFor != null) || (this.idTypeFor != null && !this.idTypeFor.equals(other.idTypeFor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TypeFormation[ idTypeFor=" + idTypeFor + " ]";
    }
    
    
}
