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
public class Session {

    private Integer idSes;
    private String dateDebSes;
    private String dateFinSes;
    private Integer capaciteSes;
    private String etatSes;
    private String imagesess;
    private Double prixSes;
    private Double nvPrixSes;
    private String nomSes;
    private Formation idFor;
    private String etatNotif;
    private Collection<Linepromoses> linepromosesCollection;
    private Collection<Educate> educateCollection;

    public Session() {
    }

      public Session(String dateDebSes, String dateFinSes, Integer capaciteSes, String etatSes, String imagesess, Double prixSes, Double nvPrixSes, String nomSes, Formation idFor) {
        this.dateDebSes = dateDebSes;
        this.dateFinSes = dateFinSes;
        this.capaciteSes = capaciteSes;
        this.etatSes = etatSes;
        this.imagesess = imagesess;
        this.prixSes = prixSes;
        this.nvPrixSes = nvPrixSes;
        this.nomSes = nomSes;
        this.idFor = idFor;
    }
    public Session(String dateDebSes, String dateFinSes, Integer capaciteSes,String imagesess, Double prixSes,String nomSes, Formation idFor) {
        this.dateDebSes = dateDebSes;
        this.dateFinSes = dateFinSes;
        this.capaciteSes = capaciteSes;
        this.imagesess = imagesess;
        this.prixSes = prixSes;
        this.nomSes = nomSes;
        this.idFor = idFor;
    }
 public Session(int idSes,String dateDebSes, String dateFinSes, Integer capaciteSes, String etatSes, String imagesess, Double prixSes, Double nvPrixSes, String nomSes, Formation idFor) {
        this.idSes=idSes;
        this.dateDebSes = dateDebSes;
        this.dateFinSes = dateFinSes;
        this.capaciteSes = capaciteSes;
        this.etatSes = etatSes;
        this.imagesess = imagesess;
        this.prixSes = prixSes;
        this.nvPrixSes = nvPrixSes;
        this.nomSes = nomSes;
        this.idFor = idFor;
    }
    public Session(Integer idSes) {
        this.idSes = idSes;
    }

    public String getEtatNotif() {
        return etatNotif;
    }

    public void setEtatNotif(String etatNotif) {
        this.etatNotif = etatNotif;
    }
    public Integer getIdSes() {
        return idSes;
    }

    public void setIdSes(Integer idSes) {
        this.idSes = idSes;
    }

    public String getDateDebSes() {
        return dateDebSes;
    }

    public void setDateDebSes(String dateDebSes) {
        this.dateDebSes = dateDebSes;
    }

    public String getDateFinSes() {
        return dateFinSes;
    }

    public void setDateFinSes(String dateFinSes) {
        this.dateFinSes = dateFinSes;
    }

    public Integer getCapaciteSes() {
        return capaciteSes;
    }

    public void setCapaciteSes(Integer capaciteSes) {
        this.capaciteSes = capaciteSes;
    }

    public String getEtatSes() {
        return etatSes;
    }

    public void setEtatSes(String etatSes) {
        this.etatSes = etatSes;
    }

    public String getImagesess() {
        return imagesess;
    }

    public void setImagesess(String imagesess) {
        this.imagesess = imagesess;
    }

    public Double getPrixSes() {
        return prixSes;
    }

    public void setPrixSes(Double prixSes) {
        this.prixSes = prixSes;
    }

    public Double getNvPrixSes() {
        return nvPrixSes;
    }

    public void setNvPrixSes(Double nvPrixSes) {
        this.nvPrixSes = nvPrixSes;
    }

    public String getNomSes() {
        return nomSes;
    }

    public void setNomSes(String nomSes) {
        this.nomSes = nomSes;
    }

    public Formation getIdFor() {
        return idFor;
    }

    public void setIdFor(Formation idFor) {
        this.idFor = idFor;
    }

    public Collection<Linepromoses> getLinepromosesCollection() {
        return linepromosesCollection;
    }

    public void setLinepromosesCollection(Collection<Linepromoses> linepromosesCollection) {
        this.linepromosesCollection = linepromosesCollection;
    }

    public Collection<Educate> getEducateCollection() {
        return educateCollection;
    }

    public void setEducateCollection(Collection<Educate> educateCollection) {
        this.educateCollection = educateCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSes != null ? idSes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Session)) {
            return false;
        }
        Session other = (Session) object;
        if ((this.idSes == null && other.idSes != null) || (this.idSes != null && !this.idSes.equals(other.idSes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Session[ idSes=" + idSes + " ]";
    }
    
    
}
