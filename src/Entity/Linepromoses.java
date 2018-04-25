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
public class Linepromoses  {

    private Integer idLine;
    private Date dateDeb;
    private Date dateFin;
    private Session idSes;
    private Promotion idPromo;
    private String etatLinePromosession;

    public Linepromoses() {
    }

    public Linepromoses(Integer idLine, Date dateDeb, Date dateFin, Session idSes, Promotion idPromo) {
        this.idLine = idLine;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.idSes = idSes;
        this.idPromo = idPromo;
    }
    
    public Linepromoses(Integer idLine) {
        this.idLine = idLine;
    }

    public Integer getIdLine() {
        return idLine;
    }

    public void setIdLine(Integer idLine) {
        this.idLine = idLine;
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

    public Session getIdSes() {
        return idSes;
    }

    public void setIdSes(Session idSes) {
        this.idSes = idSes;
    }

    public Promotion getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Promotion idPromo) {
        this.idPromo = idPromo;
    }

    public String getEtatLinePromosession() {
        return etatLinePromosession;
    }

    public void setEtatLinePromosession(String etatLinePromosession) {
        this.etatLinePromosession = etatLinePromosession;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLine != null ? idLine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Linepromoses)) {
            return false;
        }
        Linepromoses other = (Linepromoses) object;
        if ((this.idLine == null && other.idLine != null) || (this.idLine != null && !this.idLine.equals(other.idLine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Linepromoses[ idLine=" + idLine + " ]";
    }

    public Linepromoses(Date dateDeb, Date dateFin, Session idSes, Promotion idPromo) {
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.idSes = idSes;
        this.idPromo = idPromo;
    }
    
    
}
