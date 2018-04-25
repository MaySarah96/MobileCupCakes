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

public class Utilisateur {

    private Integer id;
    private String username;
    private String usernameCanonical;
    private String email;
    private String emailCanonical;
    private boolean enabled;
    private String salt;
    private String password;
    private Date lastLogin;
    private String confirmationToken;
    private Date passwordRequestedAt;
    private String roles;
    private String phoneNumber;
    private String addresse;
    private String nom;
    private String prenom;
    private String googleId;
    private String facebookId;
    private Collection<Note> noteCollection;
    private Collection<Produit> produitCollection;
    private Collection<Formation> formationCollection;
    private Collection<Commande> commandeCollection;
    private Collection<Recette> recetteCollection;
    private Collection<Educate> educateCollection;
    private Collection<Commentaire> commentaireCollection;
    private String imageProfil ;

    public Utilisateur() {
    }

    public Utilisateur(Integer id, String username, String email, String phoneNumber, String addresse, String nom, String prenom,String imageProfil) {
        this.id = id;
        this.username = username;
        this.password = password;                
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addresse = addresse;
        this.nom = nom;
        this.prenom = prenom;
        this.imageProfil=imageProfil;
    }
    
    public Utilisateur(String email ,Integer id)
    {
        this.id=id;
        this.email=email;
    }

    public Utilisateur(Integer id, String imageProfil) {
        this.id = id;
        this.imageProfil = imageProfil;
    }

    public Utilisateur(Integer id, String username, String email, String addresse, String nom, String prenom, String imageProfil) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.addresse = addresse;
        this.nom = nom;
        this.prenom = prenom;
        this.imageProfil = imageProfil;
    }

    public Utilisateur(Integer id) {
        this.id = id;
    }

    public Utilisateur(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String password, String roles) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
    }

    public Utilisateur(String username, String email, String password, String confirmationToken, String roles, String phoneNumber, String addresse, String nom, String prenom) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmationToken = confirmationToken;
        this.roles = roles;
        this.phoneNumber = phoneNumber;
        this.addresse = addresse;
        this.nom = nom;
        this.prenom = prenom;
    }
       

  public Utilisateur(Integer id, String email, String username, String password, String roles, String phoneNumber, String addresse, String nom, String prenom) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.phoneNumber = phoneNumber;
        this.addresse = addresse;
        this.nom = nom;
        this.prenom = prenom;
        
    }
    
     public Utilisateur(Integer id, String email, String username, String password, String roles, String phoneNumber, String addresse, String nom, String prenom, String imageProfil) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.phoneNumber = phoneNumber;
        this.addresse = addresse;
        this.nom = nom;
        this.prenom = prenom;
          this.imageProfil = imageProfil;
         
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getImageProfil() {
        return imageProfil;
    }

    public void setImageProfil(String imageProfil) {
        this.imageProfil = imageProfil;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }

    public Collection<Formation> getFormationCollection() {
        return formationCollection;
    }

    public void setFormationCollection(Collection<Formation> formationCollection) {
        this.formationCollection = formationCollection;
    }

    public Collection<Commande> getCommandeCollection() {
        return commandeCollection;
    }

    public void setCommandeCollection(Collection<Commande> commandeCollection) {
        this.commandeCollection = commandeCollection;
    }

    public Collection<Recette> getRecetteCollection() {
        return recetteCollection;
    }

    public void setRecetteCollection(Collection<Recette> recetteCollection) {
        this.recetteCollection = recetteCollection;
    }

    public Collection<Educate> getEducateCollection() {
        return educateCollection;
    }

    public void setEducateCollection(Collection<Educate> educateCollection) {
        this.educateCollection = educateCollection;
    }

    public Collection<Commentaire> getCommentaireCollection() {
        return commentaireCollection;
    }

    public void setCommentaireCollection(Collection<Commentaire> commentaireCollection) {
        this.commentaireCollection = commentaireCollection;
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Utilisateur[ id=" + id + " ]";
    }
    
}
