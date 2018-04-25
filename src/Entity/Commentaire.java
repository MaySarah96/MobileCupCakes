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
public class Commentaire  {

    private String body;
    private String ancestors;
    private int depth;
    private Date createdAt;
    private int state;
    private Integer idCmnt;
    private Thread idRec;
    private Utilisateur idUser;

    public Commentaire() {
    }

    public Commentaire(String body, Integer idCmnt) {
        this.body = body;
        this.idCmnt = idCmnt;
    }

    public Commentaire(Integer idCmnt) {
        this.idCmnt = idCmnt;
    }

    public Commentaire(Integer idCmnt,int state) {
        this.state = state;
        this.idCmnt = idCmnt;
    }

    public Commentaire(String body, String ancestors, int depth, Thread idRec, Utilisateur idUser) {
        this.body = body;
        this.ancestors = ancestors;
        this.depth = depth;
        this.idRec = idRec;
        this.idUser = idUser;
    }

    public Commentaire(String body, int depth, int state, Thread idRec, Utilisateur idUser) {
        this.body = body;
        this.depth = depth;
        this.state = state;
        this.idRec = idRec;
        this.idUser = idUser;
    }

    public Commentaire(Integer idCmnt,String body, String ancestors, int depth, Date createdAt, int state,  Thread idRec, Utilisateur idUser) {
        this.body = body;
        this.ancestors = ancestors;
        this.depth = depth;
        this.createdAt = createdAt;
        this.state = state;
        this.idCmnt = idCmnt;
        this.idRec = idRec;
        this.idUser = idUser;
    }

    public Commentaire(Integer idCmnt, String body, String ancestors, int depth, Date createdAt, int state) {
        this.idCmnt = idCmnt;
        this.body = body;
        this.ancestors = ancestors;
        this.depth = depth;
        this.createdAt = createdAt;
        this.state = state;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Integer getIdCmnt() {
        return idCmnt;
    }

    public void setIdCmnt(Integer idCmnt) {
        this.idCmnt = idCmnt;
    }

    public Thread getIdRec() {
        return idRec;
    }

    public void setIdRec(Thread idRec) {
        this.idRec = idRec;
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
        hash += (idCmnt != null ? idCmnt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.idCmnt == null && other.idCmnt != null) || (this.idCmnt != null && !this.idCmnt.equals(other.idCmnt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "body=" + body + ", ancestors=" + ancestors + ", depth=" + depth + ", createdAt=" + createdAt + ", state=" + state + ", idCmnt=" + idCmnt + ", idRec=" + idRec + ", idUser=" + idUser + '}';
    }
    
}
