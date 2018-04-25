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
public class LinePromo  {

    private Integer id;
    private Double views;
    private Date dateDeb;
    private Date dateFin;
    private String etatLinePromo;
    private Promotion idPromo;
    private Produit idProd;

    
      public LinePromo() {
    }

      public LinePromo(Date dateDeb, Date dateFin) {
          this.dateDeb=dateDeb ;
          this.dateFin=dateFin ;         

    }

    public LinePromo(Promotion idPromo, Produit idProd) {
        this.idPromo = idPromo;
        this.idProd = idProd;
    }
      

    public LinePromo(Integer id, Date dateDeb, Date dateFin, Promotion idPromo, Produit idProd) {
        this.id = id;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.idPromo = idPromo;
        this.idProd = idProd;
    }
      

    public LinePromo(Date dateDeb, Date dateFin, Promotion idPromo, Produit idProd) {
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.idPromo = idPromo;
        this.idProd = idProd;
    }
      
      
    public LinePromo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getViews() {
        return views;
    }

    public void setViews(Double views) {
        this.views = views;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getEtatLinePromo() {
        return etatLinePromo;
    }

    public void setEtatLinePromo(String etatLinePromo) {
        this.etatLinePromo = etatLinePromo;
    }

    public Promotion getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Promotion idPromo) {
        this.idPromo = idPromo;
    }

    public Produit getIdProd() {
        return idProd;
    }

    public void setIdProd(Produit idProd) {
        this.idProd = idProd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LinePromo)) {
            return false;
        }
        LinePromo other = (LinePromo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LinePromo{" + "id=" + id + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + ", idPromo=" + idPromo + ", idProd=" + idProd + '}';
    }

   
    
}
