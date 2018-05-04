/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;



/**
 *
 * @author hamdi fathallah
 */
public class Views {
    private Utilisateur idUser;
    private LinePromo idLinePromo ; 
    private int count ;

    public Views(Utilisateur idUser, LinePromo idLinePromo, int count) {
        this.idUser = idUser;
        this.idLinePromo = idLinePromo;
        this.count = count;
    }

    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    public LinePromo getIdLinePromo() {
        return idLinePromo;
    }

    public void setIdLinePromo(LinePromo idLinePromo) {
        this.idLinePromo = idLinePromo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public String toString() {
        return "Views{ idUser=" + idUser + ", idLinePromo=" + idLinePromo + ", count=" + count + '}';
    }    

  
    
}
